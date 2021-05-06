package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import ru.spb.iac.kotlin_mobile_template.services.App

object DBConnection {
    val database:Database = Room.databaseBuilder(App.context,
        Database::class.java,
        "UsersInfo")
        .fallbackToDestructiveMigration()
        .build()

}