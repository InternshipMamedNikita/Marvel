package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity

@androidx.room.Database(
        entities = [User::class],
        version = 5
)
abstract class Database:RoomDatabase() {
        abstract fun getDao():UserDao
}