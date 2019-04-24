package io.nomasters.android.handshake.ui.introprofile

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
class IntroProfileRobot : FragmentRobot {

    override fun getFragment(): Fragment {
        return IntroProfileFragment.newInstance()
    }

    override fun getScreenName(): String {
        return "Profile Creation Screen"
    }

    override fun <T : AppCompatActivity> isScreenShowing(rule: ActivityTestRule<T>) {
        onView(withId(R.id.fragment_intro_profile))
            .check(matches(isDisplayed()))
    }

    override fun <T : AppCompatActivity> launchFragment(rule: ActivityTestRule<T>) {
        rule.activity.findNavController(R.id.mainNavigationFragment).navigate(R.id.introProfileFragment)
    }

}