package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.appdatabase.DBConnection
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRegistrationBinding

class Registration: AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
    }

    fun register(v: View?) {
        DBConnection.database.getUserDao()
            .getUser(binding.regLogin.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Toast.makeText(this,"Пользователь с таким логином существует", Toast.LENGTH_LONG).show()
            }.doOnComplete {
                binding.run {
                    DBConnection.database.getUserDao().insertUser(User(0,
                        regName.text.toString(),
                        regLogin.text.toString(),
                        regPassword.text.toString()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }
                finish()
            }.subscribe()
    }

}