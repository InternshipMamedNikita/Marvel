package ru.spb.iac.kotlin_mobile_template.activitities.authorization.database

import androidx.room.*
import io.reactivex.*
import ru.spb.iac.kotlin_mobile_template.activitities.authorization.data.User
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers():Observable<List<User>>

    @Query("SELECT * FROM user WHERE login LIKE :login")
    fun getUser(login:String): Maybe<User?>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertUser(user:User):Completable

    @Insert()
    fun insert(user: List<User>): Completable

}