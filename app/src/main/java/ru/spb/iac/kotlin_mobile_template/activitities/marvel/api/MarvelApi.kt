package ru.spb.iac.kotlin_mobile_template.activitities.marvel.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataWrapper

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacters(@Query("apikey") apikey: String = "5694b48d133f90e847d126a5812aad42",
                      @Query("hash") hash: String = "0494427f075775dbebe95855b86ec59e",
                      @Query("ts") ts: String = "3"): Single<Response<CharacterDataWrapper>>
}