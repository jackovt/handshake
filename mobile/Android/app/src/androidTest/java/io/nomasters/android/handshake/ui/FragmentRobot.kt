package io.nomasters.android.handshake.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule

interface FragmentRobot {

    fun getScreenName(): String
    fun getFragment(): Fragment
    fun <T : AppCompatActivity> isScreenShowing(rule: ActivityTestRule<T>)
    fun <T : AppCompatActivity> launchFragment(rule: ActivityTestRule<T>)
    fun pressButtonWithText(buttonText: String) {
        Espresso.onView(withText(buttonText))
            .perform(click())
    }

}
