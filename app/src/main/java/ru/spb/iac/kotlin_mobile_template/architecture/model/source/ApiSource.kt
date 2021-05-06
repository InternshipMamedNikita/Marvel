package ru.spb.iac.kotlin_mobile_template.architecture.model.source

import android.os.Parcelable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.spb.iac.kotlin_mobile_template.architecture.model.source.ApiSource.ContentType.JSON
import ru.spb.iac.kotlin_mobile_template.utils.GsonUtils
import java.io.File

/**
 *   Created by vladislav on 1/30/20.
 */

abstract class ApiSource {

    object ContentType {
        const val JSON = "application/json"
        const val FORM_DATA = "multipart/form-data"
        const val VIDEO_DATA = "video/mp4"
        const val IMAGE_DATA = "image/jpeg"
    }

    protected fun <V : Parcelable> createPartFromParcelable(name : String, data : V) : MultipartBody.Part {
        val requestBody: RequestBody =
            GsonUtils.writeValue(data).toRequestBody(JSON.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, GsonUtils.writeValue(data), requestBody)
    }

    protected fun createPartFromFile(name : String, file : File, contentType : String) : MultipartBody.Part {
        val requestFile = file.asRequestBody(contentType.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, file.name, requestFile)
    }

    protected fun createRequestMap(key : String, value : String) : Map<String, RequestBody>  {
        val map = HashMap <String, RequestBody> ()
        map[key] = value.toRequestBody(JSON.toMediaTypeOrNull())
        return map
    }
}