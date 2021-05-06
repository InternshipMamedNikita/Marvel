package ru.spb.iac.kotlin_mobile_template.architecture.model

/**
 * Created by nixbyte on 29,Январь,2020
 */
interface ModelEntity<V> {
    fun setView(view: V)
}
