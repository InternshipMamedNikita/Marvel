package ru.spb.iac.kotlin_mobile_template.architecture.model.database

import androidx.room.RoomDatabase
import ru.spb.iac.kotlin_mobile_template.activitities.model.dao.UserDao
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity

/**
 * Created by nixbyte on 30,Январь,2020
 */

@androidx.room.Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}