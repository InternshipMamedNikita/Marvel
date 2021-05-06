package ru.spb.iac.kotlin_mobile_template.architecture.view

import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.spb.iac.kotlin_mobile_template.common.actionbar.ActionBarConstructor

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

abstract class AbstractView(activity: AppCompatActivity) : Viewable {

    val TAG = this::class.java.simpleName

    val actionBarBuilder = ActionBarConstructor.Builder(activity)
    var actionBar: ActionBarConstructor? = null

    abstract val progressBar : ProgressBar?
    abstract val snackbarPositionView : View

    override fun showSnackBar(text: String, duration: Int) {
        Snackbar.make(snackbarPositionView, text, duration).show()
    }

    override fun showProgressBar(text: String, progress: Double) {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    abstract override fun onConfigurationChanged(newConfig: Configuration?)

    open fun setActionBar() {
        actionBar = actionBarBuilder.build()
        actionBar?.setActionBar()
    }

}