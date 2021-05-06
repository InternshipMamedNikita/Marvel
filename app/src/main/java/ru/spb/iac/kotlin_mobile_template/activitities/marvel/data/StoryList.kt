package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryList(
    @SerializedName ("available") val available: Int?,
    @SerializedName ("returned") val returned: Int?,
    @SerializedName ("collectionURI") val collectionURI: String?,
    @SerializedName ("items") val items: MutableList<ComicSummary>?
): Parcelable