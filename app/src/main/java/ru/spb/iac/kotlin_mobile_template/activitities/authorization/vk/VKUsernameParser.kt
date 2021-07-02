package ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk

import android.util.Log
import com.vk.api.sdk.VKApiResponseParser
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils

class VKUsernameParser : VKApiResponseParser<ResponseUser> {
    override fun parse(response: String?): ResponseUser {
        return GsonUtils.readValue(response, ResponseUser::class.java)
    }
}