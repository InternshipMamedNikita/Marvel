package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.MarvelCharactersModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter.MarvelCharactersPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.model.databinding.ActivityBindingProvider
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractActivity
import ru.spb.iac.kotlin_mobile_template.common.actionbar.ActionBarConstructor
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding
import kotlin.random.Random

class MarvelCharactersActivity : AbstractActivity<MarvelCharactersView, MarvelCharactersModel>() {

    private val binding: ActivityRssFeedBinding by ActivityBindingProvider(R.layout.activity_rss_feed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mToolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(mToolbar)
    }

    override fun initPresenter(): AbstractPresenter<MarvelCharactersView> {
        return MarvelCharactersPresenter(view, binding)
    }
    override fun initView(): MarvelCharactersView {
        return MarvelCharactersView(this, binding)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        presenter.onCreateOptionsMenu(menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    fun exit(v: View?) {
        getSharedPreferences("UserData", Context.MODE_PRIVATE).edit().clear().apply()
        finish()
    }
}