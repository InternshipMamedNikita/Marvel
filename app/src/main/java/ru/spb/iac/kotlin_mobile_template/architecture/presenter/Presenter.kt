package ru.spb.iac.kotlin_mobile_template.architecture.presenter

import android.content.Intent
import ru.spb.iac.kotlin_mobile_template.architecture.view.Viewable

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */
open interface Presenter<V : Viewable> : MenuActions {
    fun onStart()
    fun onDestroyed()
    fun startActivityForResult(intent: Intent?, responseCode: Int)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray?) {}
}