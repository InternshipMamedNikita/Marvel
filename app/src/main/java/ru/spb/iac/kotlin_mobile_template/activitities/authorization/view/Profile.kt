package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.ProfileModel
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.DBConnection
import ru.spb.iac.kotlin_mobile_template.databinding.ProfileBinding
import kotlin.concurrent.thread


class Profile: AppCompatActivity(){

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
        DBConnection.database.getUserDao()
            .updateUser(User(sharedPreferences.getInt("id", Int.MAX_VALUE),
                binding.regName.text.toString(),
                binding.regLogin.text.toString(),
                binding.regPassword.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                val edit = sharedPreferences.edit()
                edit.putString("name", binding.regName.text.toString())
                edit.putString("login", binding.regLogin.text.toString())
                edit.putString("password", binding.regPassword.text.toString()).apply()
            }
            .subscribe()

        onBackPressed()
    }

    fun onBackPressed(v: View) {
        super.onBackPressed()
    }
}