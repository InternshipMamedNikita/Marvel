package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.db

import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 2/7/20.
 */


class DefaultDbActions(private val subscriptions : CompositeDisposable, val snackBarPositionView : View) : DbActionable {

    override fun <T> onSuccess(response: T) {
        subscriptions.clear()
        Snackbar.make(snackBarPositionView, App.context.getText(R.string.save_to_db_successed), Snackbar.LENGTH_LONG).show()
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        Snackbar.make(snackBarPositionView, App.context.getText(R.string.save_to_db_failed), Snackbar.LENGTH_LONG).show()
    }

    override fun onComplete() {

    }
}