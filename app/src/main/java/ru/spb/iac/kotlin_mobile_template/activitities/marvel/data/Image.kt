package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(indices = [Index("id")])
@Parcelize
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?
): Parcelable