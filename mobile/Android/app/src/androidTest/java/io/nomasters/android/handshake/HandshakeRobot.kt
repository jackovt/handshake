package io.nomasters.android.handshake

import android.content.Context
import android.preference.PreferenceManager
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule


/**
 * @author JH431939 (Jack Hughes)
 * @since 4/14/19
 */
class HandshakeRobot {

    fun launchMainActivity(testRule: ActivityTestRule<MainActivity>) {
        testRule.launchActivity(null)
    }

    fun isMainActivityLaunched() {
        intended(hasComponent(MainActivity::class.java.name))
    }

    fun clearCache() {
        clearSharedPrefs(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    private fun clearSharedPrefs(context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }

}