package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StorySummary(@SerializedName("resourceURI")
                        val resourceURI: String?,
                        @SerializedName("name")
                        val name: String?,
                        @SerializedName("type")
                        val type: String?): Parcelable