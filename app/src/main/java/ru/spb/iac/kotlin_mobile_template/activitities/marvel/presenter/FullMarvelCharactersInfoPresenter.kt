package ru.spb.iac.kotlin_mobile_template.activitities.marvel.presenter

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.model.FullMarvelCharctersInfoModel
import ru.spb.iac.kotlin_mobile_template.activitities.marvel.view.FullMarvelCharactersInfoView
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.databinding.FullRssItemInfoBinding

class FullMarvelCharactersInfoPresenter(view:FullMarvelCharactersInfoView, private val binding:FullRssItemInfoBinding)
    :AbstractPresenter<FullMarvelCharactersInfoView>(view) {
     override fun onStart() {
         binding.model = FullMarvelCharctersInfoModel((binding.root.context as AppCompatActivity).intent.getParcelableExtra("character"))
     }
     override fun onDestroyed() {
     }
     override fun startActivityForResult(intent: Intent?, responseCode: Int) {
     }
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
     }
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         return this.view.actionBar?.onCreateOptionsMenu(menu) ?: false
     }
     override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
         return this.view.actionBar?.onOptionsItemSelected(menuItem) ?: false
     }
 }