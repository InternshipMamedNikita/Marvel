package ru.spb.iac.kotlin_mobile_template.activitities.marvel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character

@Dao
interface MarvelCharactersDao {

    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<Character>>

    @Query("SELECT * FROM user WHERE login LIKE :login")
    fun getUser(login: String): Maybe<Character?>

    @Insert
    fun insertUser(user: User): Completable

    @Insert
    fun insert(user: List<User>): Completable

}