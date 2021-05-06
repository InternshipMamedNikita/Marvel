package ru.spb.iac.kotlin_mobile_template.architecture.model.database

import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.model.dao.UserDao
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 * Created by nixbyte on 30,Январь,2020
 */

object DB {
    val DEFAULT_DB_NAME = App.context.resources.getString(R.string.db_name)
    val DEFAULT_DB_CLASS =  Database::class.java

    val DB = DBConstructor.Builder()
        .fallbackToDestructiveMigration()

    fun getUserDao() : UserDao {
        return DB.create()?.getUserDao()!!
    }
}
