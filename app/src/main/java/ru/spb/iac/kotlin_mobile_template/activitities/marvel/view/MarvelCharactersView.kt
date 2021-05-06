package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.content.res.Configuration
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractView
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityRssFeedBinding

class MarvelCharactersView (activity: AppCompatActivity,
                            private val binding: ActivityRssFeedBinding
): AbstractView(activity) {
    override val progressBar: ProgressBar?
        get() = null
    override val snackbarPositionView: View
        get() = binding.root
    init {
        binding.recycler.layoutManager = LinearLayoutManager(activity)
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {}
}