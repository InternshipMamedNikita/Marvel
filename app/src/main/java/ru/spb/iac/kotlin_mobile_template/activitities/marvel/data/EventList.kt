package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils

@Entity
@TypeConverters(value = [EventList.Converter::class])
@Parcelize
data class EventList(@PrimaryKey(autoGenerate = true)
                     val id: Int = 0,
                     @SerializedName("available")
                     val available: Int?,
                     @SerializedName("returned")
                     val returned: Int?,
                     @SerializedName("collectionURI")
                     val collectionURI: String?,
                     @SerializedName("items")
                     val items: MutableList<EventSummary>?): Parcelable {
    object Converter {
        @TypeConverter
        @JvmStatic
        fun fromListToJson(list: List<EventSummary>?): String? {
            return GsonUtils.writeValue(list)
        }

        @TypeConverter
        @JvmStatic
        fun fromJsonToList(json: String?): List<EventSummary>? {
            return GsonUtils.readValues(json, EventSummary::class.java)
        }
    }
}

