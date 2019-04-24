package io.nomasters.android.handshake.ui.splash

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.ui.FragmentRobot
import io.nomasters.android.handshake.util.EspressoUtils.Companion.waitFor
import org.junit.Assert.assertNull

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/14/19
 */
class SplashRobot : FragmentRobot {

    private var scenario: FragmentScenario<SplashFragment>? = null

    override fun getFragment(): Fragment {
        return SplashFragment.newInstance()
    }

    override fun getScreenName(): String {
        return "Splash Screen"
    }

    override fun <T : AppCompatActivity> isScreenShowing(rule: ActivityTestRule<T>) {
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

    fun waitForSplashScreen() {
        onView(isRoot()).perform(waitFor(SplashFragment.SPLASH_SCREEN_DELAY))
        onView(withId(R.id.fragment_splash))
            .check(doesNotExist())
    }

    override fun <T : AppCompatActivity> launchFragment(rule: ActivityTestRule<T>) {
        rule.activity.findNavController(R.id.mainNavigationFragment).navigate(R.id.splashFragment)
    }

}