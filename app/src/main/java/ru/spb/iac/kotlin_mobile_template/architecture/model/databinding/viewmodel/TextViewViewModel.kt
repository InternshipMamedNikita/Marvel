package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.PayloadEntity
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.AbstractViewModel

/**
 * Created by nixbyte on 28,Ноябрь,2019
 */

class TextViewViewModel : AbstractViewModel<TextView>() {

    @BindingAdapter("bind:model")
    fun setModel(view: TextView, model: PayloadEntity) {
        view.text = "${model.firstName} ${model.lastName} ${model.middleName}"
    }

    @BindingAdapter("bind:model")
    fun setModel(view: TextView, model: UserEntity) {
        view.text = "${model.id} ${model.name}"
    }

    @BindingAdapter("bind:mainModel")
    fun setMainModel(view: TextView, model: UserEntity) {
    }
}
