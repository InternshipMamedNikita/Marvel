package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel

/**
 *   Created by vladislav on 2/10/20.
 */

interface BindableAdapter<T : List<*>> {
    fun setModel(data: T)
}

