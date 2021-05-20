package ru.spb.iac.kotlin_mobile_template.activitities.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDataContainer(@SerializedName("offset")
                                  val offset: Int?,
                                  @SerializedName("limit")
                                  val limit: Int?,
                                  @SerializedName("total")
                                  val total: Int?,
                                  @SerializedName("count")
                                  val count: Int?,
                                  @SerializedName("results")
                                  val results: MutableList<Character>?): Parcelable