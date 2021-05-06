package ru.spb.iac.kotlin_mobile_template.architecture.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 * Created by nixbyte on 03,Февраль,2020
 */

class DBConstructor(className: Class<Database>
                    ,databaseName: String
                    ,private val callback: RoomDatabase.Callback?
                    ,private val migrations: ArrayList<Migration>
                    ,private val checkDestructiveMigration: Boolean) {

    private var databaseBuilder:  RoomDatabase.Builder<Database> = Room.databaseBuilder(App.context
                                                                                ,className
                                                                                ,databaseName)

    private var DB: Database? = null

    private fun getDatabase(): Database? {
        if (DB == null) {

            callback?.let {
                databaseBuilder.addCallback(it)
            }

            migrations.let {
                if(it.isNotEmpty())
                    databaseBuilder.addMigrations(*it.toTypedArray())
            }

            checkDestructiveMigration.apply {
                if(this)
                    databaseBuilder.fallbackToDestructiveMigration()
            }

            DB = databaseBuilder.build()
        }
        return DB
    }

    fun destroyDatabase() {
        DB = null
    }

    private constructor(builder: Builder) : this(builder.className
        ,builder.databaseName
        ,builder.callback
        ,builder.migrations
        ,builder.checkDestructiveMigration)

    class Builder(var className: Class<Database> = DB.DEFAULT_DB_CLASS
                 ,var databaseName: String = DB.DEFAULT_DB_NAME) {

        var callback: RoomDatabase.Callback? = null
            private set
        var migrations: ArrayList<Migration> = ArrayList()
            private set

        var checkDestructiveMigration: Boolean = false
            private set

        fun setCallback(callback: RoomDatabase.Callback) = apply {
            this.callback = callback
        }

        fun fallbackToDestructiveMigration() = apply {
            this.checkDestructiveMigration = true
        }

        fun addMigration(migration: Migration) = apply {
            if(!migrations.contains(migration))
                migrations.add(migration)
        }

        fun create() = DBConstructor(this).getDatabase()
    }
}