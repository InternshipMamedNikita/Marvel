package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import androidx.room.RoomDatabase
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.*
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.database.MarvelCharactersDao
import java.lang.Character

@androidx.room.Database(entities = [User::class],
                        version = 5)
abstract class Database: RoomDatabase() {
    abstract fun getUserDao(): UserDao
//    abstract fun getMarverCharactersDao(): MarvelCharactersDao
}

//entities = [User::class, ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character::class, ComicList::class,
//ComicSummary::class, EventList::class, EventSummary::class,
//Image::class, SeriesList::class, SeriesSummary::class,
//StoryList::class, StorySummary::class, Url::class]