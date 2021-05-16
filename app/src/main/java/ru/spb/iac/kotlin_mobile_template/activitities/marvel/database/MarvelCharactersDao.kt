package ru.spb.iac.kotlin_mobile_template.activitities.marvel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character
import java.lang.Character as Character1

@Dao
interface MarvelCharactersDao {

    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character>>

    @Query("SELECT * FROM user WHERE login LIKE :login")
    fun getUser(login: String): Maybe<ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.Character?>

    @Insert
    fun insertUser(user: User): Completable

    @Insert
    fun insert(user: List<User>): Completable

}