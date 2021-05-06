package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel

import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.AbstractViewModel

/**
 * Created by nixbyte on 06,Февраль,2020
 */
class CollapsingToolbarLayoutViewModel : AbstractViewModel<CollapsingToolbarLayout>() {
    @BindingAdapter("bind:model")
    fun setModel(view: CollapsingToolbarLayout, model: UserEntity) {
        view.title = "id = ${model.id} name - ${model.name}"
    }
}


