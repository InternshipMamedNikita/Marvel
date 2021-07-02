package ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk

import android.util.Log
import com.vk.api.sdk.VKApiResponseParser

class VKUsernameParser : VKApiResponseParser<String> {
    override fun parse(response: String?): String {
        Log.e("TAG", "parse: ${response}")
        return "0"
    }
}