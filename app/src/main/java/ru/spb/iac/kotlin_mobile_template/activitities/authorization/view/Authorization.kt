package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

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
import ru.spb.iac.kotlin_mobile_template.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorization)
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        binding.run {
            val editableFactory = Editable.Factory.getInstance()
            authLogin.text = editableFactory.newEditable(sharedPreferences.getString("login", ""))
            authPassword.text =
                editableFactory.newEditable(sharedPreferences.getString("password", ""))

            if (authLogin.text.toString() != "" && authPassword.text.toString() != "")
                openMarvelCharacters(null)
        }

        VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS))
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
                override fun onLogin(token: VKAccessToken) {

                    thread {
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

                        Log.e("TAG", "onLogin: ${apiManager.execute(vkMethodCall, VKUsernameParser())}")
//                        VKApiManager(VKApiConfig(this@Authorization, resources.getInteger(R.integer.com_vk_sdk_AppId), ValidationHandler(), accessToken = lazy { token.accessToken })).execute(,
//                            VKUsernameParser())
                    }


                }

                override fun onLoginFailed(errorCode: Int) {
                    TODO("Not yet implemented")
                }

            })) {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
