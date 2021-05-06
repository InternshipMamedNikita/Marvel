package ru.spb.iac.kotlin_mobile_template.common.view.infoview

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 2/17/20.
 */

class InfoView(private val snackBarPositionView: View, private val progressView : View) : PopuInfoViewable {

    override fun notifyAboutEvent(text: String, duration: PopuInfoViewable.Duration) {
        Snackbar.make(snackBarPositionView, text, duration.snackbarDuration).show()
    }

    override fun notifyLoading(percentage : Int) {
        progressView.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        progressView.visibility = View.GONE
    }
}