package ru.spb.iac.kotlin_mobile_template.activitities.marvel.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataWrapper

interface MarvelApi {
    @GET("/v1/public/characters?apikey=5694b48d133f90e847d126a5812aad42&hash=0494427f075775dbebe95855b86ec59e&ts=3")
    fun getCharacters(): Observable<Response<CharacterDataWrapper>>
}