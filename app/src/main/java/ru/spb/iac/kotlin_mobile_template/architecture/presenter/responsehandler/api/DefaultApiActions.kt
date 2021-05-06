package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.architecture.view.Viewable
import ru.spb.iac.kotlin_mobile_template.services.App
import java.io.IOException

/**
 *   Created by vladislav on 2/6/20.
 */

class DefaultApiActions(private val subscriptions : CompositeDisposable, val view : Viewable) : ApiActionable {

    override fun <T> on400Error(response: Response<T>) {
        clearViewAndSubscription()
        response.errorBody()?.string().let {
            try {
                view.showSnackBar("Error")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun <T> onOtherErrors(response: Response<T>) {
        view.showSnackBar(App.context.getString(R.string.error_backend_not_response))
        clearViewAndSubscription()
    }

    override fun onThrow(throwable: Throwable) {
        throwable.printStackTrace()
        clearViewAndSubscription()
    }

    private fun clearViewAndSubscription() {
        subscriptions.clear()
        view.hideProgressBar()
    }

}