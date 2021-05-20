package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils

@Entity
@TypeConverters(value = [SeriesList.Converter::class])
@Parcelize
data class SeriesList(@PrimaryKey(autoGenerate = true)
                      val id: Int = 0,
                      @SerializedName("available")
                      val available: Int?,
                      @SerializedName("returned")
                      val returned: Int?,
                      @SerializedName("collectionURI")
                      val collectionURI: String?,
                      @SerializedName("items")
                      val items: MutableList<SeriesSummary>?): Parcelable {
     object Converter {
          @TypeConverter
          @JvmStatic
          fun fromListToJson(list: List<SeriesSummary>?): String? {
              return GsonUtils.writeValue(list)
          }

          @TypeConverter
          @JvmStatic
          fun fromJsonToList(json: String?): List<SeriesSummary>? {
              return GsonUtils.readValues(json, SeriesSummary::class.java)
          }
     }
}
