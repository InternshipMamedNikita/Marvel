package ru.spb.iac.kotlin_mobile_template.appdatabase

import androidx.room.Room
import ru.spb.iac.kotlin_mobile_template.services.App

object DBConnection {
    val database: Database = Room.databaseBuilder(App.context, Database::class.java, "UsersInfo")
        .fallbackToDestructiveMigration()
        .build()
}