package ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di

import dagger.Component
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.LoginPresenter
import ru.spb.iac.kotlin_mobile_template.common.dagger.DaggerComponent

/**
 *   Created by vladislav on 2/27/20.
 */

@Component(modules = [LoginPresenterModule::class])
interface LoginPresenterComponent : DaggerComponent{
    fun inject(presenter : LoginPresenter)
}