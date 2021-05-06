package ru.spb.iac.kotlin_mobile_template.architecture.view

import android.content.res.Configuration
import com.google.android.material.snackbar.Snackbar
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

interface Viewable {

    fun showSnackBar(text : String, duration : Int = Snackbar.LENGTH_LONG)

    fun showProgressBar(text : String = App.context.getString(R.string.loading), progress : Double = 0.0)

    fun hideProgressBar()

    fun onConfigurationChanged(newConfig: Configuration?)
}