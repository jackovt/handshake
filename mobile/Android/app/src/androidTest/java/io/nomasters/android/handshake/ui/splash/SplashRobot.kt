package io.nomasters.android.handshake.ui.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.util.EspressoUtils.Companion.waitFor
import org.junit.Assert.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/14/19
 */
class SplashRobot {

    fun isSplashScreenShowing(rule: ActivityTestRule<MainActivity>) {
        val splashFragment =
            rule.activity.supportFragmentManager.findFragmentByTag(SplashFragment::class.java.simpleName)
        assertNotNull(splashFragment)
        assertTrue(splashFragment!!.isVisible)
        onView(withId(R.id.fragment_splash))
            .check(matches(isDisplayed()))
    }

    fun isSplashScreenGone(rule: ActivityTestRule<MainActivity>) {
        onView(isRoot()).perform(waitFor(SplashFragment.SPLASH_SCREEN_DELAY))
        val splashFragment =
            rule.activity.supportFragmentManager.findFragmentByTag(SplashFragment::class.java.simpleName)
        assertNull(splashFragment)
        onView(withId(R.id.fragment_splash))
            .check(doesNotExist())
    }

}