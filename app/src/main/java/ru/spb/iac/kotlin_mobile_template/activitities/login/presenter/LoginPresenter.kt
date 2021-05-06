package ru.spb.iac.kotlin_mobile_template.activitities.login.presenter

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di.DaggerLoginPresenterComponent
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di.LoginPresenterComponent

import ru.spb.iac.kotlin_mobile_template.activitities.login.view.LoginView
import ru.spb.iac.kotlin_mobile_template.activitities.model.source.api.AuthorizationApiSource
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityLoginBinding
import javax.inject.Inject

/**
 *   Created by vladislav on 2/26/20.
 */

class LoginPresenter(view : LoginView, val binding: ActivityLoginBinding) : AbstractPresenter<LoginView>(view) {

    @Inject lateinit var apiSource : AuthorizationApiSource

    init {

        val dependencyComponent = initDagger()
        dependencyComponent.inject(this)

        binding.submitButton.setOnClickListener {
            val email = binding.mailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (view.validate()) {
                view.showProgressBar()
                sendRequest(apiResponseHandler.prepareSubscribtion (apiSource.getAuthorization(email, password), {
                    Log.e(TAG, "Request successed")
                }))
            }
        }
    }

    override fun onStart() {
    }

    override fun onDestroyed() {
        subscription.dispose()
    }

    override fun startActivityForResult(intent: Intent?, resposeCode: Int) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean
            = view.actionBar?.onOptionsItemSelected(menuItem) ?: false


    private fun initDagger() : LoginPresenterComponent = DaggerLoginPresenterComponent.create()
}