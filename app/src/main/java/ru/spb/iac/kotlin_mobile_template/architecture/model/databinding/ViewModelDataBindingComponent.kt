package ru.spb.iac.kotlin_mobile_template.architecture.model.databinding

import android.widget.TextView
import androidx.databinding.DataBindingComponent
import ru.spb.iac.kotlin_mobile_template.architecture.model.ModelEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.CollapsingToolbarLayoutViewModel
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.ListViewModel

import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.viewmodel.TextViewViewModel
import ru.spb.iac.kotlin_mobile_template.utils.PicassoClient

/**
 * Created by nixbyte on 28,Ноябрь,2019
 */
class ViewModelDataBindingComponent : DataBindingComponent {
    companion object {
        val TAG = ViewModelDataBindingComponent::class.java.simpleName
    }

    override fun getCollapsingToolbarLayoutViewModel(): CollapsingToolbarLayoutViewModel {
        return CollapsingToolbarLayoutViewModel()
    }

    override fun getTextViewViewModel(): TextViewViewModel {
        return TextViewViewModel()
    }

    override fun getPicassoClient(): PicassoClient {
        return PicassoClient
    }

    override fun getListViewModel(): ListViewModel<*> {
        return ListViewModel<Any>()
    }
}
