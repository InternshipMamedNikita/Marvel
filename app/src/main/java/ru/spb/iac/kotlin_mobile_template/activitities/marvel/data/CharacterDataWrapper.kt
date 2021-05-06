package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDataWrapper(
     @SerializedName ("code")val code: Int?,
     @SerializedName ("status") val status: String?,
     @SerializedName ("copyright") val copyright: String?,
     @SerializedName ("attributionText") val attributionText: String?,
     @SerializedName ("attributionHTML") val attributionHTML: String?,
     @SerializedName ("data") val data: CharacterDataContainer?,
     @SerializedName ("etag") val etag: String?
): Parcelable