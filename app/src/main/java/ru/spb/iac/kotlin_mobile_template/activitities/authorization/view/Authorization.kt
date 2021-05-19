package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.DBConnection
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityAuthorizationBinding
import kotlin.concurrent.thread


class Authorization: AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorization)
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        binding.run {
            val editableFactory = Editable.Factory.getInstance()
            authLogin.text = editableFactory.newEditable(sharedPreferences.getString("login", ""))
            authPassword.text = editableFactory.newEditable(sharedPreferences.getString("password", ""))

            if (authLogin.text.toString() != "" && authPassword.text.toString() != "")
                openMarvelCharacters(null)
        }
    }

    fun openMarvelCharacters(v: View?) {
        DBConnection.database.getUserDao()
            .getUser(findViewById<EditText>(R.id.auth_login).text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {

                if (binding.authPassword.text.toString() == it?.password) {
                    sharedPreferences.edit().run {
                        putInt("id", it.id)
                        putString("name", it.name)
                        putString("login", it.login)
                        putString("password", it.password).apply()
                    }
                    startActivity(Intent(this, MarvelCharactersActivity::class.java))
                }
                else {
                    Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show()
                }

            }.doOnError {
                throw it
            }.doOnComplete {
                Toast.makeText(this, "Неверный логин", Toast.LENGTH_LONG).show()
            }.subscribe()
    }

    fun openRegistration(v: View?) {
        startActivity(Intent(this, Registration::class.java))
    }
}
