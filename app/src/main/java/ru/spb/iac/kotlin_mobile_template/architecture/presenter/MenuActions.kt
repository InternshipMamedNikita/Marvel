package ru.spb.iac.kotlin_mobile_template.architecture.presenter

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

/**
 * Created by nixbyte on 19,Декабрь,2019
 */
open interface MenuActions : Toolbar.OnMenuItemClickListener {
    fun onCreateOptionsMenu(menu: Menu?): Boolean
    fun onOptionsItemSelected(menuItem: MenuItem): Boolean
}