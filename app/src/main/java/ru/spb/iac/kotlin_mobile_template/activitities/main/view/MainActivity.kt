package ru.spb.iac.kotlin_mobile_template.activitities.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.MainModel
import ru.spb.iac.kotlin_mobile_template.activitities.main.presenter.MainPresenter
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.MarvelCharactersActivity

import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.ActivityBindingProvider
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractActivity
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityMainBinding


/**
 * Created by nixbyte on 27,Ноябрь,2019
 */

class MainActivity : AbstractActivity<MainView, MainModel>() {

    private val  binding: ActivityMainBinding by ActivityBindingProvider(R.layout.activity_authorization)

    companion object {
        val TAG = MainActivity::class.simpleName
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun initPresenter(): AbstractPresenter<MainView> {
        return MainPresenter(view, binding)
    }

    override fun initView(): MainView {
        return MainView(this, binding)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return presenter.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return presenter.onOptionsItemSelected(item)
    }
}