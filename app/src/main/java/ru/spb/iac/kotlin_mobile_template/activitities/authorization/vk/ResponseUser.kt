package ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk

import com.google.gson.annotations.SerializedName
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.auth.VKAccessToken

data class ResponseUser(@SerializedName("response")
                        var response: List<User> = arrayListOf()) {

    data class User(@SerializedName("id")
                    var userId: String = "",
                    @SerializedName("first_name")
                    var firstName: String = "",
                    @SerializedName("last_name")
                    var lastName: String = "",
                    @SerializedName("can_access_closed")
                    var canAccessClosed: Boolean = false,
                    @SerializedName("is_closed")
                    var isClosed: Boolean = false)
}


