package ru.spb.iac.kotlin_mobile_template.activitities.main.presenter

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.MainModel
import ru.spb.iac.kotlin_mobile_template.activitities.main.view.MainView
import ru.spb.iac.kotlin_mobile_template.activitities.model.source.api.AuthorizationApiSource
import ru.spb.iac.kotlin_mobile_template.architecture.model.database.DB

import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.responsehandler.db.DbResponseHandler
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

/**
 * Created by nixbyte on 28,Ноябрь,2019
 */

class MainPresenter(view : MainView, private val binding: ActivityMainBinding) : AbstractPresenter<MainView>(view) {

//    var data: MainModel = MainModel()
//
//    private val apiSource = AuthorizationApiSource()
//    private val dbResponseHandler = DbResponseHandler(subscription,binding.root)
//
//    init {
//        binding.model = data
//        binding.recyclerview.setAdapter(UserListAdapter(data.list))
//        subscription.add(dbResponseHandler.prepareSubscribtion(DB.getUserDao().getAll(),{
//          data.list = ArrayList(it)
//        }).subscribe())
//    }


//    override fun onViewAttached(view: MainView) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val biometricScanner = BiometricScanner(activity)
//            biometricScanner.cipherManager.generateSecretKey()
//        }
//        this.view = view
//        this.view.onAttach(data)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val cipherGenerator = CipherGenerator()
//            cipherGenerator.generateSecretKey()
//        }
//    }
//
//    override fun onViewDetached() {
//        view.onDetach(data)
//    }

    override fun onDestroyed() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return this.view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return this.view.actionBar?.onOptionsItemSelected(menuItem) ?: false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return this.view.actionBar?.onMenuItemClick(item) ?: false
    }

    override fun startActivityForResult(intent: Intent?, resposeCode: Int) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onStart() {
    }

}