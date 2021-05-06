package ru.spb.iac.kotlin_mobile_template.activitities.main.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.PayloadEntity
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.db.UserEntity
import ru.spb.iac.kotlin_mobile_template.activitities.main.view.MainView

/**
 * Created by nixbyte on 29,Январь,2020
 */
@Parcelize
data class MainModel(var _user: UserEntity? = UserEntity()
                    ,var _list: ArrayList<UserEntity> = ArrayList()
                    ,var _authorization: PayloadEntity? = PayloadEntity()) : BaseObservable(), Parcelable {
   @IgnoredOnParcel
   @get:Bindable
   var user get() = _user; set(value) {
       _user = value
       notifyPropertyChanged(BR.user)
   }

   @IgnoredOnParcel
   @get:Bindable
    var list get() = _list; set(value) {
        _list = value
        notifyPropertyChanged(BR.list)
    }

    @IgnoredOnParcel
    @get:Bindable
    var authorization get() = _authorization; set(value) {
        _authorization = value
        notifyPropertyChanged(BR.authorization)
    }
}