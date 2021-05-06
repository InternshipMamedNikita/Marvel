package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventSummary(
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?
):Parcelable