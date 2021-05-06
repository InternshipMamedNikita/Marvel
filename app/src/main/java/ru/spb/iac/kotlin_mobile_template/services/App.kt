package ru.spb.iac.kotlin_mobile_template.services

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log.e
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.database.DB

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

class App : MultiDexApplication() {

    companion object {
        lateinit var context: Context
    }

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        val users = ArrayList<UserEntity>()
        users.add(UserEntity(1,"Alan"))
        users.add(UserEntity(2,"Sophi"))
        users.add(UserEntity(3,"Greg"))
        users.add(UserEntity(4,"Thirteen"))

        DB.getUserDao()?.insert(users)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {

                DB.getUserDao()?.getAll()?.subscribe({
                e("APP","DB Insert complete Users count ${it.size}")
            }, {
                it.printStackTrace()
            }, {
                e("APP", "onComplete")
            })
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onTerminate() {
        super.onTerminate()
        e("TAG", "Terminate")
    }

}