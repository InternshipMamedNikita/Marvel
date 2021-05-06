package ru.spb.iac.kotlin_mobile_template.architecture.presenter

import android.content.Intent
import android.view.MenuItem
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersView
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.ApiResponseHandler
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.api.DefaultApiActions
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractView
import ru.spb.iac.kotlin_mobile_template.common.permissions.DefaultPermissionHander
import ru.spb.iac.kotlin_mobile_template.common.permissions.PermissionCallable

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

abstract class AbstractPresenter<V : AbstractView>(val view: AbstractView): Presenter<V> {

    val TAG = this::class.java.simpleName

    protected val permissionHandler : PermissionCallable
    protected val subscription : CompositeDisposable
    protected val apiResponseHandler : ApiResponseHandler

    init {
        permissionHandler = initPermissionHandler()
        subscription = CompositeDisposable()
        apiResponseHandler = ApiResponseHandler(DefaultApiActions(subscription, view))
    }

    abstract override fun onStart()
    abstract override fun onDestroyed()
    abstract override fun startActivityForResult(intent: Intent?, responseCode: Int)
    abstract override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    override fun onMenuItemClick(menuItem: MenuItem?): Boolean {
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray?) {
        permissionHandler.onRequestPermissionResult(requestCode, permissions, grantResults)
    }

    protected fun <T> sendRequest(observable : Observable<T>) {
        subscription.add(observable.subscribe())
    }

    fun initPermissionHandler() : PermissionCallable = DefaultPermissionHander(view)

}