package ru.spb.iac.kotlin_mobile_template.activitities.marvel.view

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractView
import ru.spb.iac.kotlin_mobile_template.databinding.FullRssItemInfoBinding

class FullMarvelCharactersInfoView (private val activity: AppCompatActivity,
                                    private val binding: FullRssItemInfoBinding): AbstractView(activity) {
    override val progressBar: ProgressBar? = null
    override val snackbarPositionView: View = View(activity)
    override fun onConfigurationChanged(newConfig: android.content.res.Configuration?) {}
}