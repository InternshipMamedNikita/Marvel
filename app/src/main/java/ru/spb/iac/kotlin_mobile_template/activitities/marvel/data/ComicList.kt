package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils

@Entity
@TypeConverters(value = [ComicList.Converter::class])
@Parcelize
data class ComicList(@PrimaryKey(autoGenerate = true)
                     val id: Int = 0,
                     @SerializedName("available")
                     val available: Int?,
                     @SerializedName("returned")
                     val returned: Int?,
                     @SerializedName("collectionURI")
                     val collectionURI: String?,
                     @SerializedName("items")
                     val items: MutableList<ComicSummary>?): Parcelable {
    object Converter {
        @TypeConverter
        @JvmStatic
        fun fromListToJson(list: List<ComicSummary>?): String {
            return GsonUtils.writeValue(list)
        }

        @TypeConverter
        @JvmStatic
        fun fromJsonToList(json: String?): List<ComicSummary>? {
            return GsonUtils.readValues(json, ComicSummary::class.java)
        }
    }
}


