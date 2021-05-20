package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils
import java.util.*

@Entity
@TypeConverters(value = [Character.Converter::class])
@Parcelize
data class Character @JvmOverloads constructor (@PrimaryKey
                                                @SerializedName("id")
                                                var id: Int? = null,
                                                @SerializedName("name")
                                                var name: String? = null,
                                                @SerializedName("description")
                                                var description: String? = null,
                                                @SerializedName("modified")
                                                var modified: Date? = null,
                                                @SerializedName("resourceURI")
                                                var resourceURI: String? = null,
                                                @Ignore
                                                @SerializedName("urls")
                                                var urls: MutableList<Url>? = null,
                                                @Ignore
                                                @SerializedName("thumbnail")
                                                var thumbnail: Image? = null,
                                                @Ignore
                                                @SerializedName("comics")
                                                var comics: ComicList? = null,
                                                @Ignore
                                                @SerializedName("stories")
                                                var stories: StoryList? = null,
                                                @Ignore
                                                @SerializedName("events")
                                                var events: EventList? = null,
                                                @Ignore
                                                @SerializedName("series")
                                                var series: SeriesList? = null): Parcelable {
    object Converter {
        @TypeConverter
        @JvmStatic
        fun fromListToJson(list: List<Url>?): String {
            return GsonUtils.writeValue(list)
        }

        @TypeConverter
        @JvmStatic
        fun fromJsonToList(json: String?): List<Url>? {
            return GsonUtils.readValues(json, Url::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromDateToJson(date: Date?): String {
            return GsonUtils.writeValue(date)
        }

        @TypeConverter
        @JvmStatic
        fun fromJsonToDate(json: String?): Date? {
            return GsonUtils.readValue(json, Date::class.java)
        }
    }
}