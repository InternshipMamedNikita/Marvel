package ru.spb.iac.kotlin_mobile_template.activitities.login.view

import android.content.res.Configuration
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.architecture.view.AbstractView
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityLoginBinding
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 2/26/20.
 */


class LoginView(val activity: AppCompatActivity, val binding: ActivityLoginBinding): AbstractView(activity) {

    override val progressBar: ProgressBar?
        get() = activity.findViewById(R.id.progress_bar) ?: null

    override val snackbarPositionView: View
        get() = binding.root

    override fun onConfigurationChanged(newConfig: Configuration?) {}

    fun validate(): Boolean {
        var valid = true
        if (binding.mailEditText.text.toString().isEmpty()) {
            binding.mailTextInputLayout.error = App.context.getString(R.string.mail_invalid_error)
            valid = false
        }
        if (binding.passwordEditText.text.toString().isEmpty()) {
            binding.passwordTextInputLayout.error = App.context.getString(R.string.password_invalid_error)
            valid = false
        } else if (binding.passwordEditText.text.toString().length < 6) {
            binding.passwordTextInputLayout.error = App.context.getString(R.string.password_length_error)
        }
        return valid
    }

}