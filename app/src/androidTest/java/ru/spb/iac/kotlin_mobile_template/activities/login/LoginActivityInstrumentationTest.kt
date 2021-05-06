package ru.spb.iac.kotlin_mobile_template.activities.login

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.activitities.login.view.LoginActivity
import ru.spb.iac.kotlin_mobile_template.matchers.textinputlayout.HasTextInputLayoutErrortText
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 2/28/20.
*/

class LoginActivityInstrumentationTest {

    @Rule @JvmField
    val activityLoginRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.mail_edit_text)).perform(replaceText("test@test.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password_edit_text)).perform(replaceText("testtest"),  ViewActions.closeSoftKeyboard())
    }

    @Test
    fun validateMailAndPasswordCorrect() {
        onView(withId(R.id.submit_button)).perform(click())
        //TODO check if next activity started
    }

    @Test
    fun validateEmptyPassword() {
        onView(withId(R.id.password_edit_text)).perform(replaceText(""), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.submit_button)).perform(click())
        onView(withId(R.id.password_text_input_layout)).check(matches(
            HasTextInputLayoutErrortText(
                App.context.getString(R.string.password_invalid_error)
            )
        ))
    }

    @Test
    fun validateSmallCountOfSymbolsPassword() {
        onView(withId(R.id.password_edit_text)).perform(replaceText("test"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.submit_button)).perform(click())
        onView(withId(R.id.password_text_input_layout)).check(matches(
            HasTextInputLayoutErrortText(
                App.context.getString(R.string.password_length_error)
            )
        ))
    }

}