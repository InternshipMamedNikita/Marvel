package ru.spb.iac.kotlin_mobile_template.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.inputmethod.InputMethodManager
import ru.spb.iac.kotlin_mobile_template.services.App
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.math.roundToInt

/**
 *   Created by vladislav on 1/16/20.
 */

object Configurations {

    val TAG = Configurations::class.java.simpleName

    fun isPreAndroidO(): Boolean {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1
    }

    fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager =
            App.context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    fun hideKeyBoard(activity: Activity) {
        if (activity.window.currentFocus != null) {
            val imm =
                App.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.currentFocus!!.windowToken, 0)
        }
    }

    fun getDisplayDensity(): Float {
        return App.context.resources.displayMetrics.density
    }

    fun getDisplayMetricsString(): String {
        Log.e(
            TAG,
            "getDisplayMetricsString: " + getDisplayDensity()
        )
        val metric = "xxxhdpi"
        if (getDisplayDensity() <= 0.75) return "ldpi"
        if (getDisplayDensity() <= 1.0) return "mdpi"
        if (getDisplayDensity() <= 1.5) return "hdpi"
        if (getDisplayDensity() <= 2.0) return "xhdpi"
        if (getDisplayDensity() <= 3.0) return "xxhdpi"
        return if (getDisplayDensity() <= 4.0) "xxxhdpi" else metric
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = App.context.resources.displayMetrics
        return (dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()).roundToInt()
    }

    fun pxToDp(px: Int): Int {
        val displayMetrics: DisplayMetrics = App.context.resources.displayMetrics
        return (px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()).roundToInt()
    }

    fun phoneEmei(): String {
        return Build.MODEL + Build.DEVICE
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }

    fun textAsBitmap(text: String?, textSize: Float, textColor: Int): Bitmap {
        val paint =
            Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.0f).toInt() // round
        val height = (baseline + paint.descent() + 0.0f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text!!, 0f, baseline, paint)
        return image
    }
}
