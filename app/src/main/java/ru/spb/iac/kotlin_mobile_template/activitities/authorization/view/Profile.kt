package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.ProfileModel
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.appdatabase.DBConnection
import ru.spb.iac.kotlin_mobile_template.databinding.ProfileBinding


class Profile: AppCompatActivity() {

    lateinit var binding: ProfileBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.profile)
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        binding.model = ProfileModel(sharedPreferences.getString("name", "")!!,
                                     sharedPreferences.getString("login", "")!!,
                                     sharedPreferences.getString("password", "")!!)
    }

    fun saveProfileData(v: View?) {
        binding.run {
            DBConnection.database.getUserDao()
                .updateUser(User(sharedPreferences.getInt("id", Int.MAX_VALUE),
                    regName.text.toString(),
                    regLogin.text.toString(),
                    regPassword.text.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    sharedPreferences.edit().run {
                        putString("name", regName.text.toString())
                        putString("login", regLogin.text.toString())
                        putString("password", regPassword.text.toString()).apply()
                    }
                }.subscribe()
        }
        onBackPressed()
    }

    fun onBackPressed(v: View) {
        super.onBackPressed()
    }
}