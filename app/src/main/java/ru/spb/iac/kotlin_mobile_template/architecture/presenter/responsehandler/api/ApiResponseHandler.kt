package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 *   Created by vladislav on 2/6/20.
 */

class ApiResponseHandler(var defaultResponseActions : ApiActionable) {

    companion object {
        const val SUCCESS_CODE = 200
        const val SERVER_ERROR_400 = 400
        const val SERVER_ERROR_401 = 401
    }

    fun <T> prepareSubscribtion(observableResponse: Observable<Response<T>>,
                  successAction: (Response<T>) -> Unit,
                  error400Action: (Response<T>) -> Unit = { defaultResponseActions.on400Error(it) },
                  othersErrorAction: (Response<T>) -> Unit = { defaultResponseActions.onOtherErrors(it) },
                  throwableAction: (Throwable) -> Unit = { throwable -> defaultResponseActions.onThrow(throwable) }) : Observable<Response<T>> {

        return observableResponse
            .subscribeOn (Schedulers.newThread())
            .observeOn (AndroidSchedulers.mainThread())
            .doOnError(throwableAction)
            .doOnNext { response ->
                when(response.code()) {
                    SUCCESS_CODE -> successAction(response)
                    SERVER_ERROR_400, SERVER_ERROR_401 -> error400Action(response)
                    else -> othersErrorAction(response)
                }
            }
    }
}