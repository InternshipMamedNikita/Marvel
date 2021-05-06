package ru.spb.iac.kotlin_mobile_template.activitities.model.source.api

import io.reactivex.Observable
import retrofit2.Response
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.AuthorizationData
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.AuthorizationApiEntity
import ru.spb.iac.kotlin_mobile_template.architecture.model.api.API
import ru.spb.iac.kotlin_mobile_template.architecture.model.source.ApiSource

/**
 *   Created by vladislav on 1/29/20.
 */

class AuthorizationApiSource : ApiSource() {

    fun getAuthorization(email : String, password : String) : Observable<Response<AuthorizationApiEntity>>{
        return API.getAuthorizationApi().login(AuthorizationData(email, password))
    }
}