package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Url(@PrimaryKey(autoGenerate = true)
               val id: Int = 0,
               @SerializedName("type")
               val type: String?,
               @SerializedName("url")
               val url: String?): Parcelable