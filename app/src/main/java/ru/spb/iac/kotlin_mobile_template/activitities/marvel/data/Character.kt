package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils
import java.util.*

@Entity(indices = [Index("id")])
@Parcelize
data class Character(@PrimaryKey @SerializedName("id") val id: Int?,
                     @SerializedName ("name") val name: String?,
                     @SerializedName ("description") val description: String?,
                     @SerializedName ("modified") val modified: Date?,
                     @SerializedName ("resourceURI") val resourceURI: String?,
                     @SerializedName ("urls") val urls: @RawValue MutableList<Url>?,
                     @SerializedName ("thumbnail") val thumbnail:@RawValue Image?,
                     @SerializedName ("comics") val comics:@RawValue ComicList?,
                     @SerializedName ("stories") val stories:@RawValue StoryList?,
                     @SerializedName ("events") val events:@RawValue EventList?,
                     @SerializedName ("series") val series:@RawValue SeriesList?): Parcelable {
    object Converter {
        @TypeConverter
        fun toJsonUrls(urls: List<Url>?): String {
            return GsonUtils.writeValue(urls.toString())
        }
        @TypeConverter
        fun fromJsonUrls(json: String): List<Url>? {
            return GsonUtils.readValues(json, Url::class.java)
        }
        @TypeConverter
        fun toJsonDate(date: Date): String {
            return GsonUtils.writeValue(date)
        }
        @TypeConverter
        fun fromJsonDate(json: String): Date {
            return GsonUtils.readValue(json, Date::class.java)
        }
    }
}