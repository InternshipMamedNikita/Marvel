package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import androidx.room.RoomDatabase
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User

@androidx.room.Database(entities = [User::class],
                        version = 5)
abstract class Database: RoomDatabase() {
        abstract fun getDao(): UserDao
}