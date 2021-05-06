package ru.spb.iac.kotlin_mobile_template.common.actionbar

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.common.actionbar.actions.Action
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.MenuActions

/**
 * Created by nixbyte on 17,Декабрь,2019
 */

abstract class AbstractActionBar (activity: AppCompatActivity) : MenuActions {
    protected var topAppBar: Toolbar = activity.window.decorView.rootView.findViewById(R.id.action_bar)
    protected var bottomAppBar: BottomAppBar = activity.window.decorView.rootView.findViewById(R.id.bottom_appbar)
    protected var bottomFab: FloatingActionButton = activity.window.decorView.rootView.findViewById(R.id.bottom_fab)
    protected var topLayout: AppBarLayout = activity.window.decorView.rootView.findViewById(R.id.appbar)

    private var homeButtonAction: ButtonAction = ButtonAction()
    private var bottomHomeButtonAction: ButtonAction = ButtonAction()
    private var fabButtonAction: ButtonAction = ButtonAction()

    abstract fun setActionBar()
    abstract override fun onCreateOptionsMenu(menu: Menu?): Boolean

    protected open fun setHomeButtonBehavior(behavior: Action?) {
        homeButtonAction.setBehavior(behavior)
    }

    protected open fun setFabButtonBehavior(behavior: Action?) {
        fabButtonAction.setBehavior(behavior)
        bottomFab.setOnClickListener { fabButtonAction.run() }
    }

    protected open fun setBottomHomeButtonBehavior(behavior: Action?) {
        bottomHomeButtonAction.setBehavior(behavior)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> homeButtonAction.run()
            else -> return true
        }
        return true
    }
}