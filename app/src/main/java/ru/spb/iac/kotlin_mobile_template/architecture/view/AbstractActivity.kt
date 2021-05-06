package ru.spb.iac.kotlin_mobile_template.architecture.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter

/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

abstract class AbstractActivity<V: AbstractView,T> : AppCompatActivity() {
    companion object {
        val TAG = AbstractActivity::class.java.simpleName
    }
    protected lateinit var presenter: AbstractPresenter<V>
    lateinit var view: V
    protected abstract fun initPresenter(): AbstractPresenter<V>
    protected abstract fun initView(): V
    //---------------Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = initView()
        presenter = initPresenter()
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroyed()
        super.onDestroy()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        presenter.startActivityForResult(intent, requestCode)
        super.startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        view.onConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}