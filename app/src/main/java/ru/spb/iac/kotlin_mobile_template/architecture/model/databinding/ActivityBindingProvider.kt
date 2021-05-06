package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.CollapsingToolbarLayoutViewModel
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.ListViewModel
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.TextViewViewModel
import ru.spb.iac.kotlin_mobile_template.utils.PicassoClient
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by nixbyte on 17,Декабрь,2019
 */

class ActivityBindingProvider<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int) : ReadOnlyProperty<Activity, T> {

    private var binding : T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(thisRef).also{ binding = it }
    }

    private fun createBinding(activity: Activity):T {

        return DataBindingUtil.setContentView(activity, layoutRes, ViewModelDataBindingComponent())
    }



}

