package ru.spb.iac.kotlin_mobile_template.common.fingerprint

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 *   Created by vladislav on 2/14/20.
 */

@RequiresApi(Build.VERSION_CODES.M)
class CipherGenerator {

    companion object {
        const val KEY_STORE = "AndroidKeyStore"
        val KEY_NAME = App.context.getString(R.string.app_name)
    }

    val cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
            + KeyProperties.BLOCK_MODE_CBC + "/"
            + KeyProperties.ENCRYPTION_PADDING_PKCS7)

    fun generateSecretKey() {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_STORE)

        keyGenerator.init( KeyGenParameterSpec.Builder(KEY_NAME,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setUserAuthenticationRequired(true)
            .build())

        keyGenerator.generateKey()
    }

    fun getSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(KEY_STORE)
        keyStore.load(null)
        return keyStore.getKey(KEY_NAME, null) as SecretKey
    }

    fun encrypt(stringToCode : String) : String {
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
        return cipher.doFinal(stringToCode.toByteArray()).toString()
    }

    fun decrypt(stringToDecode : String) : String {
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey())
        return cipher.doFinal(stringToDecode.toByteArray()).toString()
    }

}