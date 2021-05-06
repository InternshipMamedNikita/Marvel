package ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di

import dagger.Module
import dagger.Provides
import ru.spb.iac.kotlin_mobile_template.activitities.model.source.api.AuthorizationApiSource

/**
 *   Created by vladislav on 2/27/20.
 */

@Module
class LoginPresenterModule {

    @Provides
    fun provideApiSource() : AuthorizationApiSource {
        return AuthorizationApiSource()
    }

}