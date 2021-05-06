package ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.db

import android.view.View
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *   Created by vladislav on 2/7/20.
 */

class DbResponseHandler(subscription : CompositeDisposable, snackbarPositionView : View,
                        private var onDefaultActions : DbActionable = DefaultDbActions(subscription, snackbarPositionView)) {

    fun <T> prepareSubscribtion(observable: Maybe<T>,
                onSuccess: (T) -> Unit = { onDefaultActions.onSuccess(it) },
                onError: (Throwable) -> Unit = { throwable -> onDefaultActions.onError(throwable) },
                onComplete : () -> Unit = { onDefaultActions.onComplete() }) : Maybe<T>  {

        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError (onError)
            .doOnComplete (onComplete)
            .doOnSuccess(onSuccess)
    }


    fun <T> prepareSubscribtion(observable: Flowable<T>,
                                onSuccess : (T) -> Unit = { onDefaultActions.onSuccess(it) },
                                onError : (Throwable) -> Unit = { throwable -> onDefaultActions.onError(throwable)},
                                onComplete: () -> Unit = { onDefaultActions.onComplete() }) : Flowable<T> {

        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError(onError)
            .doOnComplete(onComplete)
            .doOnNext(onSuccess)
    }


    fun prepareSubscription(observable: Completable,
                                onSuccess : () -> Unit = { onDefaultActions.onComplete() },
                                onError : (Throwable) -> Unit = { onDefaultActions.onError(it)}) : Completable {
        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError(onError)
            .doOnComplete(onSuccess)
    }

}