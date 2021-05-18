package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.ProfileModel
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.DBConnection
import ru.spb.iac.kotlin_mobile_template.databinding.ProfileBinding
import ru.spb.iac.kotlin_mobile_template.databinding.ProfileBindingImpl

class Profile: AppCompatActivity(){

    lateinit var binding: ProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.profile)
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        binding.model = ProfileModel(sharedPreferences.getString("name", "")!!,
                                     sharedPreferences.getString("login", "")!!,
                                     sharedPreferences.getString("password", "")!!)
    }

    fun saveProfileData(v: View?) {
        DBConnection.database.getUserDao()
            .updateUser(User(0, binding.regName.text.toString(), binding.regLogin.text.toString(), binding.regPassword.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
            }
        finish()
    }
}