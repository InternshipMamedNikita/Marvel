package ru.spb.iac.kotlin_mobile_template.activitities.authorization.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launch_screen.*
import ru.spb.iac.kotlin_mobile_template.R

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        dollar.alpha = 0f
        dollar.animate().setDuration(700).alpha(1f).withEndAction{
            val intent = Intent(this,Authorization::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}