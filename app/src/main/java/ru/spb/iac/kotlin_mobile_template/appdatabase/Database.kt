package ru.spb.iac.kotlin_mobile_template.appdatabase

import androidx.room.RoomDatabase
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.database.UserDao
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.*
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.database.MarvelCharactersDao
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character

@androidx.room.Database(entities = [User::class, Character::class, ComicList::class,
                                    EventList::class, SeriesList::class, StoryList::class,
                                    Url::class, Image::class], version = 6)
abstract class Database: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getMarverCharactersDao(): MarvelCharactersDao
}