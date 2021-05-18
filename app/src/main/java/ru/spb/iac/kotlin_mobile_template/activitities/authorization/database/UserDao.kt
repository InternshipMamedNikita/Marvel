package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import androidx.room.*
import io.reactivex.*
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE login LIKE :login")
    fun getUser(login: String): Maybe<User?>

    @Insert
    fun insertUser(user: User): Completable

    @Insert
    fun insert(user: List<User>): Completable

    @Update
//    @Query("UPDATE user SET name = :, login =  WHERE a = 1;")
    fun updateUser(user: User): Completable

}