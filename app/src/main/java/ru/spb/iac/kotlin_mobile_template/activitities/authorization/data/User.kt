package ru.spb.iac.kotlin_mobile_template.activitities.authorization.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    indices = [Index("id"), Index(value = ["login"], unique = true)]
)
@Parcelize
data class User(@PrimaryKey(autoGenerate = true) val id:Int = 0,
                val name:String = "",
                val login:String = "",
                val password:String = ""):Parcelable {
}