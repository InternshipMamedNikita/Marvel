package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api

import retrofit2.Response

/**
 *   Created by vladislav on 2/6/20.
 */
interface ApiActionable {

    fun <T> on400Error(response : Response<T>)
    fun <T> onOtherErrors(response : Response<T>)
    fun onThrow(throwable: Throwable)

}