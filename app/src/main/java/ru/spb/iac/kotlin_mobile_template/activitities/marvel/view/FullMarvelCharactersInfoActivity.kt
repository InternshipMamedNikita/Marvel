package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.FullMarvelCharctersInfoModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter.FullMarvelCharactersInfoPresenter

//import ru.spb.iac.kotlin_mobile_template.activitities.rss.presenter.FullRssItemInfoPresenter

import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.ActivityBindingProvider
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractActivity
import ru.spb.iac.kotlin_mobile_template.databinding.FullRssItemInfoBinding

class FullMarvelCharactersInfoActivity: AbstractActivity<FullMarvelCharactersInfoView, FullMarvelCharctersInfoModel>() {

    private val binding: FullRssItemInfoBinding by ActivityBindingProvider(R.layout.full_rss_item_info)

    override fun initPresenter(): AbstractPresenter<FullMarvelCharactersInfoView> {
        return FullMarvelCharactersInfoPresenter(view, binding)
    }
    override fun initView(): FullMarvelCharactersInfoView {
        return FullMarvelCharactersInfoView(this, binding)
    }
}