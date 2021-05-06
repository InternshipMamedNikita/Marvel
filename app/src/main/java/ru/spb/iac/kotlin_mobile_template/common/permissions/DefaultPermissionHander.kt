package ru.spb.iac.kotlin_mobile_template.common.permissions

import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.architecture.view.Viewable
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 1/17/20.
 */

class DefaultPermissionHander(val view: Viewable, requestCode : Int = 0) : PermissionCallable(requestCode) {

    override fun onPermissionAccept(permissions: Array<out String>, result: IntArray) {

    }

    override fun onPermissionReject() {
        view.showSnackBar(App.context.getString(R.string.permission_not_granted))
    }

}