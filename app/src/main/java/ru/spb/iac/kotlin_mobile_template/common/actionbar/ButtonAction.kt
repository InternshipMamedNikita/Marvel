package ru.spb.iac.kotlin_mobile_template.common.actionbar

import ru.spb.iac.kotlin_mobile_template.common.actionbar.actions.Action

/**
 * Created by nixbyte on 17,Декабрь,2019
 */
class ButtonAction : Action {
    private var behavior: Action? = null
    fun setBehavior(behavior: Action?) {
        this.behavior = behavior
    }

    override fun run() {
        behavior?.run()
    }
}