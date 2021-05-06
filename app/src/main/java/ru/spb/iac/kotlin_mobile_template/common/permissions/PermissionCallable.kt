package ru.spb.iac.kotlin_mobile_template.common.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 3/2/20.
 */

abstract class PermissionCallable(var requestCode: Int) {

    abstract fun onPermissionAccept(permissions : Array<out String>, result : IntArray)
    abstract fun onPermissionReject()

    private fun checkSelfPermission(permission : String) : Boolean  =
        ContextCompat.checkSelfPermission(App.context, permission) == PackageManager.PERMISSION_GRANTED

    fun requestPermissions(activity: Activity, requestCode: Int, vararg  permissions : String) : Boolean {
        this.requestCode = requestCode
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
        for (i in permissions.indices) {
            if (!checkSelfPermission(permissions[i])) {
                return false
            }
        }
        return true
    }

    fun onRequestPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray?) {
        if (requestCode == this.requestCode) {
            grantResults?.let { results ->
                for (result in results) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        onPermissionReject()
                        return
                    }
                }
                onPermissionAccept(permissions, results)
            }
        }
    }

}