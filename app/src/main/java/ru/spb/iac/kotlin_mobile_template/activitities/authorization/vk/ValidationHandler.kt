package ru.spb.iac.kotlin_mobile_template.activitities.authorization.vk

import android.util.Log
import com.vk.api.sdk.VKApiValidationHandler

class ValidationHandler : VKApiValidationHandler {
    override fun handleCaptcha(img: String, cb: VKApiValidationHandler.Callback<String>) {
        Log.e("Captcha", "handleCaptcha: $img")
    }

    override fun handleConfirm(confirmationText: String, cb: VKApiValidationHandler.Callback<Boolean>) {
        Log.e("Confirmation", "handleConfirm: $confirmationText")
    }

    override fun handleValidation(validationUrl: String, cb: VKApiValidationHandler.Callback<VKApiValidationHandler.Credentials>) {
        Log.e("Validation", "handleValidation: $validationUrl")
    }
}