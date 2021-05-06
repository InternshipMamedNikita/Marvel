package ru.spb.iac.kotlin_mobile_template.activitities.login.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.LoginPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractActivity
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityLoginBinding

/**
 *   Created by vladislav on 2/26/20.
 */

class LoginActivity : AbstractActivity<LoginView, Nullable?>() {

    val TAG = LoginActivity::class.java.name

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        super.onCreate(savedInstanceState)
    }


    override fun initPresenter(): AbstractPresenter<LoginView> {
        return LoginPresenter(view, binding)
    }

    override fun initView(): LoginView {
        return LoginView(this, binding)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return presenter.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return presenter.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}