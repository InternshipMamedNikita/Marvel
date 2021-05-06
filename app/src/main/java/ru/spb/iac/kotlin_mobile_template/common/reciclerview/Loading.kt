package ru.spb.iac.kotlin_mobile_template.common.reciclerview

import io.reactivex.Observable

/**
 * Created by nixbyte on 20,Декабрь,2019
 */
open interface Loading<T> {
    fun getLoadingObservable(offsetAndLimit: OffsetAndLimit?): Observable<MutableList<T>?>?
}