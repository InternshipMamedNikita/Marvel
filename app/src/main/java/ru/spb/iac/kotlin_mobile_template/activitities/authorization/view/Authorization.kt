package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.DBConnection
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity


class Authorization: AppCompatActivity()
{
    lateinit var login: TextInputEditText
    lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        login = findViewById(R.id.auth_login)
        password = findViewById(R.id.auth_password)
        val edit = Editable.Factory.getInstance()
        login.text = edit.newEditable(getSharedPreferences("UserData", Context.MODE_PRIVATE)
            .getString("login", ""))
        password.text = edit.newEditable(getSharedPreferences("UserData", Context.MODE_PRIVATE)
            .getString("password", ""))
        if (login.text.toString() != "" && password.text.toString() != "")
            openMarvelCharacters(null)

    }

    fun openMarvelCharacters(v: View?) {
        DBConnection.database.getDao()
            .getUser(findViewById<EditText>(R.id.auth_login).text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val edit = getSharedPreferences("UserData", Context.MODE_PRIVATE).edit()
                edit.putString("login", login.text.toString())
                edit.putString("password", password.text.toString()).apply()
                val password = findViewById<EditText>(R.id.auth_password)
                if (password.text.toString() == it?.password) {
                    startActivity(Intent(this, MarvelCharactersActivity::class.java))
                } else {
                    Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show()
                }
            }.doOnError {
                throw it
            }.doOnComplete {
                Toast.makeText(this, "Неверный логин", Toast.LENGTH_LONG).show()
            }.subscribe()
    }
    fun openRegistration(v:View?)
    {
        startActivity(Intent(this,Registration::class.java))
    }
}
