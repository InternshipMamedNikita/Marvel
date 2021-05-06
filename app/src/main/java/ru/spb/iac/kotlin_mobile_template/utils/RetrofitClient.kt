package ru.spb.iac.kotlin_mobile_template.utils

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.api.MarvelApi

//object RetrofitClient {
//    fun getRssFeed(baseUrl: String, pathToRss: String){
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//}
