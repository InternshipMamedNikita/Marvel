package ru.spb.iac.kotlin_mobile_template.architecture.model.database

import io.reactivex.Flowable

/**
 * Created by nixbyte on 30,Январь,2020
 */
interface AbstractDao<T> {
    fun getAll(): Flowable<List<T>>
    fun getByIds(ids: IntArray): Flowable<List<T>>
}