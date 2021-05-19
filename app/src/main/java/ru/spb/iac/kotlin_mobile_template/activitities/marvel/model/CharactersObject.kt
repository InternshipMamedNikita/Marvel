package ru.spb.iac.kotlin_mobile_template.activitities.marvel.model

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.data.CharacterDataWrapper
import ru.spb.iac.kotlin_mobile_template.architecture.model.api.API

object CharactersObject {
    var characterDataWrapper: CharacterDataWrapper? = null
}