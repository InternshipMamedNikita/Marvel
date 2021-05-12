package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.DBConnection

class Registration: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    fun registrate(v: View?) {
        DBConnection.database.getDao()
            .getUser(findViewById<EditText>(R.id.reg_login).text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Toast.makeText(this,"Пользователь с таким логином существует", Toast.LENGTH_LONG).show()
            }.doOnError {
                throw it
            }.doOnComplete {
                DBConnection.database.getDao().insertUser(User(0,
                    findViewById<EditText>(R.id.reg_name).text.toString(),
                    findViewById<EditText>(R.id.reg_login).text.toString(),
                    findViewById<EditText>(R.id.reg_password).text.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                finish()
            }.subscribe()
    }
}