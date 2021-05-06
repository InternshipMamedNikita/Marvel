package ru.spb.iac.kotlin_mobile_template.activitities.main.view

import android.content.res.Configuration
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractView
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityMainBinding

/**
 * Created by nixbyte on 28,Ноябрь,2019
 */

class MainView(private val activity: AppCompatActivity, private val binding: ActivityMainBinding) : AbstractView(activity){

    override val progressBar: ProgressBar
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val snackbarPositionView: View
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    companion object {
        val TAG = MainView::class.java.simpleName
    }

    init {
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
    }
}