package ru.spb.iac.kotlin_mobile_template.activitities.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *   Created by vladislav on 1/30/20.
 */

@Parcelize
data class AuthorizationData(@SerializedName("email") val email : String,
                             @SerializedName("password") val password : String,
                             @SerializedName("audience") val audience : String = "web",
                             @SerializedName("userAgent") val userAgent : String ="test") : Parcelable