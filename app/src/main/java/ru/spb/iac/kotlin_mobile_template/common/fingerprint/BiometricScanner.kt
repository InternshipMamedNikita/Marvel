package ru.spb.iac.kotlin_mobile_template.common.fingerprint

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 2/17/20.
 */


@RequiresApi(Build.VERSION_CODES.M)
class BiometricScanner(val activity : AppCompatActivity) {

    val cipherManager = CipherGenerator()

    fun callFingerAuthorization(stringToDecode : String) {
        val exececutor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, exececutor, getAuthCallbacks(stringToDecode))
        biometricPrompt.authenticate(createPromptInfo(), BiometricPrompt.CryptoObject(cipherManager.cipher))
    }

    private fun createPromptInfo() : BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(App.context.getString(R.string.biometric_login_title))
            .setSubtitle(App.context.getString(R.string.biometric_login_subtitle))
            .setDeviceCredentialAllowed(true)
            .build()
    }

    private fun getAuthCallbacks(stringToDecode : String) : BiometricPrompt.AuthenticationCallback {
        return object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                result.cryptoObject?.cipher?.let { cipher ->
                    Log.e("Test", cipher.doFinal(stringToDecode.toByteArray()).toString())
                }
            }
        }
    }

}