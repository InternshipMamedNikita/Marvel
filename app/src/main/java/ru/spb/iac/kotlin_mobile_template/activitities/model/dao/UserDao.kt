package ru.spb.iac.kotlin_mobile_template.activitities.model.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.database.AbstractDao


/**
 * Created by nixbyte on 30,Январь,2020
 */

@Dao
interface UserDao : AbstractDao<UserEntity> {

    @Query("SELECT * FROM users")
    override fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id IN (:ids)")
    override fun getByIds(ids: IntArray): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Int): Maybe<UserEntity?>

    @Query("SELECT * FROM users WHERE name LIKE :name")
    fun getByName(name: String?): Maybe<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(classifiers: List<UserEntity?>?): Completable

    @Update
    fun update(user: UserEntity): Completable

    @Update
    fun update(employee: List<UserEntity?>?): Completable

    @Delete
    fun delete(employee: UserEntity?): Completable

    @Delete
    fun delete(employee: List<UserEntity?>?): Completable

    @Query("DELETE FROM users")
    fun clear(): Completable
}