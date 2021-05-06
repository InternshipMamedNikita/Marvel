package ru.spb.iac.kotlin_mobile_template.common.view.infoview

import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 *   Created by vladislav on 2/26/20.
 */
interface PopuInfoViewable {

    enum class Duration(val toastDuration : Int, val snackbarDuration : Int) {
        LONG(Toast.LENGTH_LONG, Snackbar.LENGTH_LONG),
        SHORT(Toast.LENGTH_SHORT, Snackbar.LENGTH_SHORT)
    }

    fun notifyAboutEvent(text : String, duration: Duration = Duration.LONG)

    fun notifyLoading(percentage : Int)

    fun stopLoading()

}