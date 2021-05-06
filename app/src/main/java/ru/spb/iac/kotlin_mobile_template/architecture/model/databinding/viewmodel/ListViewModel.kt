package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel

import android.util.Log.e
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.databinding.library.baseAdapters.BR.list
import androidx.recyclerview.widget.RecyclerView
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.PayloadEntity
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.AbstractViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 *   Created by vladislav on 2/10/20.
 */


class ListViewModel<T> :  AbstractViewModel<RecyclerView>(){
    @BindingAdapter("bind:model")
    fun setModel(view: RecyclerView, model: List<T>) {
        if (view.adapter is BindableAdapter<*>) {
            (view.adapter as BindableAdapter<List<T>>).setModel(model)
        }
    }
}
