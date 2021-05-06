package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.content.Context
import android.os.Bundle
import android.view.View
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.MarvelCharactersModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter.MarvelCharactersPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.ActivityBindingProvider
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractActivity
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding

class MarvelCharactersActivity : AbstractActivity<MarvelCharactersView, MarvelCharactersModel>() {
    private val binding: ActivityRssFeedBinding by ActivityBindingProvider(R.layout.activity_rss_feed)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun exit(v: View?)
    {
        val edit = getSharedPreferences("UserData", Context.MODE_PRIVATE).edit()
        edit.clear().commit()
        finish()
    }
    override fun initPresenter(): AbstractPresenter<MarvelCharactersView> {
        return MarvelCharactersPresenter(view, binding)
    }
    override fun initView(): MarvelCharactersView {
        return MarvelCharactersView(this, binding)
    }
}