package ru.spb.iac.kotlin_mobile_template.utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Created by nixbyte on 20,Декабрь,2019
 */

object GsonUtils {
    private val GSON = GsonBuilder()
        .create()

    fun <T> readValues(json: String?, clazz: Class<T>): List<T>? {
        return toList(json, clazz)
    }

    fun <T> readValue(json: String?, clazz: Class<T>?): T {
        return GSON.fromJson(json, clazz)
    }

    fun <T> writeValue(obj: T): String {
        return GSON.toJson(obj)
    }

    private fun <T> toList(json: String?, clazz: Class<T>): List<T>? {
        return if (null == json) {
            null
        } else GSON.fromJson(json, object : TypeToken<T>() {}.type)
    }
}