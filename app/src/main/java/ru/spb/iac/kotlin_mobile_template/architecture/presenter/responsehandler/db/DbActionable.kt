package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.db

/**
 *   Created by vladislav on 2/7/20.
 */

interface DbActionable {

    fun <T> onSuccess(response : T)
    fun onError(throwable: Throwable)
    fun onComplete()

}