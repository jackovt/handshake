package io.nomasters.android.handshake.ui.login

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.ui.FragmentRobot

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/14/19
 */
class LoginRobot : FragmentRobot {
    override fun getFragment(): Fragment {
        return LoginFragment.newInstance()
    }

    override fun getScreenName(): String {
        return "Login Screen"
    }

    override fun <T : AppCompatActivity> isScreenShowing(rule: ActivityTestRule<T>) {
        onView(withId(R.id.fragment_login))
            .check(matches(isDisplayed()))
    }

    override fun <T : AppCompatActivity> launchFragment(rule: ActivityTestRule<T>) {
        rule.activity.findNavController(R.id.mainNavigationFragment).navigate(R.id.loginFragment)
    }

}