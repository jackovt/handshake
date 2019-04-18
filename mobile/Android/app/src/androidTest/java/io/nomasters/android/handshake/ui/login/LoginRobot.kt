package io.nomasters.android.handshake.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.R
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/14/19
 */
class LoginRobot {

    fun isLoginScreenShowing(rule: ActivityTestRule<MainActivity>) {
        val fragment =
            rule.activity.supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)
        assertNotNull(fragment)
        assertTrue(fragment!!.isVisible)
        onView(withId(R.id.fragment_login))
            .check(matches(isDisplayed()))
    }

}