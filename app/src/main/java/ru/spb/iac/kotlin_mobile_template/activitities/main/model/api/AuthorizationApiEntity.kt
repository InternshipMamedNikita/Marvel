package ru.spb.iac.kotlin_mobile_template.activitities.main.model.api

import android.os.Parcelable
import android.widget.TextView
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import ru.spb.iac.kotlin_mobile_template.architecture.model.ModelEntity

/**
 * Created by nixbyte on 05,Февраль,2020
 */
@Parcelize
data class AuthorizationApiEntity(@SerializedName("status") val status: String?
                                 ,@SerializedName("code") val code: Int?
                                 ,@SerializedName("message") val message: String?
                                 ,@SerializedName("errors") val errors: ArrayList<String>?
                                 ,@SerializedName("response") val response: ResponseEntity?) : Parcelable

@Parcelize
data class ResponseEntity(@SerializedName("access_token") val access_token: String?
                                  ,@SerializedName("header") val code: HeaderEntity?
                                  ,@SerializedName("payload") val message: PayloadEntity?) : Parcelable

@Parcelize
data class HeaderEntity(@SerializedName("alg") val alg: String?
                       ,@SerializedName("typ") val typ: String?
                       ,@SerializedName("cty") val cty: String?
                       ,@SerializedName("kid") val kid: String?) : Parcelable
@Parcelize
data class PayloadEntity(@SerializedName("iss") val iss: String? = ""
                        ,@SerializedName("sub")val sub: String? = ""
                        ,@SerializedName("aud") val aud: String? = ""
                        ,@SerializedName("email") val email: String? = ""
                        ,@SerializedName("email_verified") val emailVerified: String? = ""
                        ,@SerializedName("phone_number") val phoneNumber: String? = ""
                        ,@SerializedName("phone_number_verified") val phoneNumberVerified: Boolean? = false
                        ,@SerializedName("first_name") val firstName: String? = ""
                        ,@SerializedName("last_name") val lastName: String? = ""
                        ,@SerializedName("middle_name") val middleName: String? = ""
                        ,@SerializedName("photo") val photo: String? = ""
                        ,@SerializedName("background") val background: String? = ""
                        ,@SerializedName("about") val about: String? = ""
                        ,@SerializedName("user_id") val userId: Int? = 0
                        ,@SerializedName("specialization") val specialization: String? = ""
                        ,@SerializedName("job_position") val jobPosition: String? = ""
                        ,@SerializedName("organization_name") val organizatioName: String? = ""
                        ,@SerializedName("organization_address") val organizationAddress: String? = ""
                        ,@SerializedName("organization_phone") val organizationPhone: String? = ""
                        ,@SerializedName("exp") val exp: Double? = 0.0
                        ,@SerializedName("iat") val iat: Double? = 0.0
                        ,@SerializedName("jti") val jti: String? = ""
                        ,@SerializedName("account_create_time") val accountCreateTime: Double? = 0.0
                        ,@SerializedName("account_password_reset") val accountPasswordReset: Double? = 0.0
                        ,@SerializedName("account_expired") val accountExpired: Boolean? = false
                        ,@SerializedName("account_locked") val accountLocked: Boolean? = false
                        ,@SerializedName("account_enabled") val accountEnabled: Boolean? = false
) : Parcelable
