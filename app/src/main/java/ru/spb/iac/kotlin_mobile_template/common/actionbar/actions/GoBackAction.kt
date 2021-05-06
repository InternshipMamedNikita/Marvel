package ru.spb.iac.kotlin_mobile_template.common.actionbar.actions

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by nixbyte on 19,Декабрь,2019
 */

class GoBackAction(val activity: AppCompatActivity) :
    Action {
    override fun run() {
        activity.onBackPressed()
    }
}