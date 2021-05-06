package ru.spb.iac.kotlin_mobile_template.activitities.model.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.AuthorizationData
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.AuthorizationApiEntity

/**
 *   Created by vladislav on 1/27/20.
 */
interface AuthorizationApi {
    @POST("/authorization/login")
    fun login(@Body authorizationData: AuthorizationData) : Observable<Response<AuthorizationApiEntity>>
}