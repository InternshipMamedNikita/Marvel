package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.google.android.material.navigationrail.NavigationRailMenuView
import com.google.android.material.navigationrail.NavigationRailView
import com.google.gson.annotations.SerializedName
import com.vk.api.sdk.*
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKAuthResult
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk.VKUsernameParser
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk.ValidationHandler
import ru.spb.iac.kotlin_mobile_template.appdatabase.DBConnection
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityAuthorizationBinding
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils
import kotlin.concurrent.thread

// Swipe Refresh, VK Autorization, Observable

class Authorization: AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editableFactory: Editable.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorization)
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        editableFactory = Editable.Factory.getInstance()

        binding.run {
            authLogin.text = editableFactory.newEditable(sharedPreferences.getString("login", ""))
            authPassword.text =
                editableFactory.newEditable(sharedPreferences.getString("password", ""))

            if (authLogin.text.toString() != "" && authPassword.text.toString() != "")
                openMarvelCharacters(null)
        }
    }

    fun openMarvelCharacters(v: View?) {
        DBConnection.database.getUserDao()
            .getUser(binding.authLogin.text.toString())
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

    fun registerWithVK(v: View?) {
        VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS, VKScope.EMAIL))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
                @SuppressLint("CheckResult")
                override fun onLogin(token: VKAccessToken) {
                    io.reactivex.Single.fromCallable {
                        val vkMethodCall = VKMethodCall.Builder()
                                                    .method("users.get")
                                                    .skipValidation(true)
                                                    .version("5.131")
                                                    .build()

                        val vkConfig = VKApiConfig(this@Authorization,
                                                          resources.getInteger(R.integer.com_vk_sdk_AppId),
                                                          ValidationHandler(),
                                                          accessToken = lazy { token.accessToken })

                        val apiManager = VKApiManager(vkConfig)

                        return@fromCallable apiManager.execute(vkMethodCall, VKUsernameParser())



                        }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess {
                            Log.e("TAG", "onLogin: ${token.email}")
                            if (token.email == null)
                                onLoginFailed(1337228666)
                            else {
                                DBConnection.database.getUserDao().insertUser(User(0, it.response[0].firstName, token.email!!))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe ({
                                        binding.authLogin.text =
                                            editableFactory.newEditable(token.email!!)
                                        openMarvelCharacters(null)
                                    }, {
                                        binding.authLogin.text =
                                            editableFactory.newEditable(token.email!!)
                                        openMarvelCharacters(null)
                                    })
                            }
                        }.subscribe()
                    }

                override fun onLoginFailed(errorCode: Int) {
                    Toast.makeText(this@Authorization, "Авторизация через ВКонтакте не прошла. Код ошибки $errorCode", Toast.LENGTH_SHORT).show()
                }

            })) {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
