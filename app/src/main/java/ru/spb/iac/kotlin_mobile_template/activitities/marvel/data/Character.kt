package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*
@Parcelize
data class Character(
    @SerializedName("id") val id: Int?,
    @SerializedName ("name")val name: String?,
    @SerializedName ("description") val description: String?,
    @SerializedName ("modified") val modified: Date?,
    @SerializedName ("resourceURI") val resourceURI: String?,
    @SerializedName ("urls") val urls:@RawValue MutableList<Url>?,
    @SerializedName ("thumbnail") val thumbnail:@RawValue Image?,
    @SerializedName ("comics") val comics:@RawValue ComicList?,
    @SerializedName ("stories") val stories:@RawValue StoryList?,
    @SerializedName ("events") val events:@RawValue EventList?,
    @SerializedName ("series") val series:@RawValue SeriesList?
):Parcelable