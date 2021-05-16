package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding

class MarvelCharactersActivity : AbstractActivity<MarvelCharactersView, MarvelCharactersModel>() {
    private val binding: ActivityRssFeedBinding by ActivityBindingProvider(R.layout.activity_rss_feed)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawe_layout)
        findViewById<ImageView>(R.id.view_menu).setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.itemIconTintList = null
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