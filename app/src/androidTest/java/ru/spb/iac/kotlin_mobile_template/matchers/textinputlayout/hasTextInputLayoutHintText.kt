package ru.spb.iac.kotlin_mobile_template.matchers.textinputlayout

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 *   Created by vladislav on 2/28/20.
 */

class HasTextInputLayoutErrortText(private val expectedErrorString : String) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
    }

    override fun matchesSafely(view: View?): Boolean {
        if (view is TextInputLayout) {
            view.error?.toString()?.let {
                return it == expectedErrorString
            }
        }
        return false
    }
}