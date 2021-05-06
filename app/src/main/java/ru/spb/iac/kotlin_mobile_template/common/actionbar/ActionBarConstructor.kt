package ru.spb.iac.kotlin_mobile_template.common.actionbar

import android.graphics.PorterDuff
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBar
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.common.actionbar.actions.Action
import ru.spb.iac.kotlin_mobile_template.common.actionbar.actions.GoBackAction
import ru.spb.iac.kotlin_mobile_template.services.App
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.MenuActions

/**
 * Created by nixbyte on 19,Декабрь,2019
 */
class ActionBarConstructor(private val activity: AppCompatActivity
                           , var title: String?
                           , var titleColor: Int
                           , var icon: Int?
                           , var bottomMenuHomeIcon:Int?
                           , var bottomFabIcon: Int
                           , var topMenuId: Int
                           , var bottomMenuId: Int
                           , private val homeButtonBehavior: Action?
                           , private val bottomHomeButtonBehavior: Action?
                           , private val fabButtonBehavior: Action?
                           , var onTop: Boolean
                           , var onBottom: Boolean
                           , var showFab: Boolean
                           , var fabAligmentMode: Int
                           , var menuActions: MenuActions?) : AbstractActionBar(activity) {

    private constructor(builder: Builder) : this(builder.activity
        ,builder.title
        ,builder.titleColor
        ,builder.icon
        ,builder.bottomMenuHomeIcon
        ,builder.bottomFabIcon
        ,builder.topMenuId
        ,builder.bottomMenuId
        ,builder.homeBehavior
        ,builder.bottomHomeBehavior
        ,builder.fabBehavior
        ,builder.onTop
        ,builder.onBottom
        ,builder.showFab
        ,builder.fabAligmentMode
        ,builder.menuActions) {
        setHomeButtonBehavior(homeButtonBehavior)
        setBottomHomeButtonBehavior(bottomHomeButtonBehavior)
        if (showFab) {
            setFabButtonBehavior(fabButtonBehavior)
        }
    }

    companion object {
        val TAG: String? = ActionBarConstructor::class.java.name
    }

    override fun setActionBar() {
        try {
            topAppBar.setNavigationIcon(icon!!)
        } catch (e: NullPointerException) {
        }
        topAppBar.title = title
        topAppBar.setTitleTextColor(activity.resources.getColor(titleColor))
        topAppBar.contentInsetStartWithNavigation = 0
        bottomAppBar.replaceMenu(bottomMenuId)
        bottomAppBar.fabAlignmentMode = fabAligmentMode
        bottomAppBar.setOnMenuItemClickListener(menuActions)
        bottomFab.setImageResource(bottomFabIcon)
        bottomFab.setColorFilter(ContextCompat.getColor(App.context, R.color.colorAccent),
            PorterDuff.Mode.SRC_ATOP)
        setBars()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        activity.menuInflater.inflate(topMenuId, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        Log.e(TAG, "onOptionsItemSelected: CONSTR$menuItem")
        when (menuItem.itemId) {
            else -> super.onOptionsItemSelected(menuItem)
        }
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Log.e(TAG, "onMenuItemClick: $item")
        when (item?.itemId) {
            R.id.action_goback -> activity.onBackPressed()
        }
        return true
    }

    private fun setBars() {
        if (onTop && !onBottom) {
            topLayout.visibility = View.VISIBLE
            bottomAppBar.visibility = View.GONE
            if (showFab) {
                bottomAppBar.visibility = View.INVISIBLE
                bottomAppBar.hideOnScroll = false
                bottomFab.show()
            } else {
                bottomFab.hide()
            }
            activity.setSupportActionBar(topAppBar)
        } else if(onBottom && !onTop){
            bottomAppBar.visibility = View.VISIBLE
            topLayout.visibility = View.GONE

            if (showFab) {
                bottomFab.show()
            } else {
                bottomFab.hide()
            }
            val p = bottomFab.layoutParams as CoordinatorLayout.LayoutParams
            p.anchorId = R.id.bottom_appbar
            bottomFab.layoutParams = p

            topAppBar.navigationIcon = null
            topAppBar.inflateMenu(R.menu.empty)

            if (bottomMenuHomeIcon != null) bottomAppBar.setNavigationIcon(bottomMenuHomeIcon!!)

            bottomAppBar.setNavigationOnClickListener { bottomHomeButtonBehavior?.run() }

            activity.setSupportActionBar(topAppBar)
        } else if(onBottom && onTop) {
            topLayout.visibility = View.VISIBLE
            bottomAppBar.visibility = View.VISIBLE

            if (showFab) {
                bottomFab.show()
            } else {
                bottomFab.hide()
            }
            val p = bottomFab.layoutParams as CoordinatorLayout.LayoutParams
            p.anchorId = R.id.bottom_appbar
            bottomFab.layoutParams = p


            if (bottomMenuHomeIcon != null) bottomAppBar.setNavigationIcon(bottomMenuHomeIcon!!)

            bottomAppBar.setNavigationOnClickListener { bottomHomeButtonBehavior?.run() }
            activity.setSupportActionBar(topAppBar)
        }
    }

    class Builder(val activity: AppCompatActivity) {
        var title: String = ""
            private set
        var titleColor = R.color.white
            private set
        var topMenuId = R.menu.empty
            private set
        var bottomMenuId = R.menu.empty
            private set
        var icon: Int = R.drawable.ic_arrow_left_white_24dp
            private set
        var bottomMenuHomeIcon: Int? = null
            private set
        var bottomFabIcon = R.drawable.common_full_open_on_phone
            private set
        var homeBehavior: Action = GoBackAction(activity)
        private set
        var fabBehavior: Action = GoBackAction(activity)
            private set
        var bottomHomeBehavior: Action = GoBackAction(activity)
            private set
        var menuActions: MenuActions? = null
            private set
        var onTop = true
            private set
        var onBottom = false
            private set
        var showFab = true
            private set
        var fabAligmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            private set

        fun setTitle(title: String) = apply { this.title = title }
        fun setTitleColor(titleColor: Int) = apply { this.titleColor = titleColor }
        fun setTopMenu(topMenuId: Int) = apply { this.topMenuId = topMenuId }
        fun setBottomMenu(bottomMenuId: Int) = apply { this.bottomMenuId = bottomMenuId }
        fun setIcon(icon: Int) = apply { this.icon = icon }
        fun setBottomMenuHomeIcon(icon: Int) = apply { this.bottomMenuHomeIcon = icon }
        fun setBottomFabIcon(icon: Int) = apply { this.bottomFabIcon = icon }
        fun setFabAligmentMode(mode: Int) = apply { this.fabAligmentMode = mode }
        fun setHomeButtonAction(action: Action) = apply { this.homeBehavior = action }
        fun setBottomHomeButtonAction(action: Action) = apply { this.bottomHomeBehavior = action }
        fun menuActions(menuActions: MenuActions?) = apply { this.menuActions = menuActions }
        fun hideFab() = apply { this.showFab = false }
        fun onTop() = apply {
            onTop = true
            onBottom = false
        }
        fun onBottom() = apply {
            onBottom = true
            onTop = false
        }
        fun onTopAndBottom() = apply {
            onTop = true
            onBottom = true
        }
        fun build() = ActionBarConstructor(this)
    }
}