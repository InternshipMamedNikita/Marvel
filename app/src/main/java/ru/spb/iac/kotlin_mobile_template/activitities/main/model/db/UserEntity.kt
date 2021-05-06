package ru.spb.iac.kotlin_mobile_template.activitities.main.model.db

import android.os.Parcelable
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.architecture.model.ModelEntity

/**
 * Created by nixbyte on 30,Январь,2020
 */
@Entity(
    tableName = "users",
    indices = [Index("id"), Index(value = ["name"], unique = true)]
)
@Parcelize
data class UserEntity(@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "id")
                      private var _id: Int = 0
                     ,@ColumnInfo(name = "name")
                      private var _name: String = "") : BaseObservable(), Parcelable {

    @IgnoredOnParcel
    @get:Bindable
    var name get() = _name; set(value) {
        _name = value
        notifyPropertyChanged(BR.name)
    }
    @IgnoredOnParcel
    @get:Bindable
    var id get() = _id; set(value) {
        _id = value
        notifyPropertyChanged(BR.id)
    }

}