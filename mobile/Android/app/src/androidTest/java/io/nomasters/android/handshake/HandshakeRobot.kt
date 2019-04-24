package io.nomasters.android.handshake

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import java.util.*


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

    fun setAppFirstLaunchedPreference() {
        setNowBasedPreference(MainActivity.PREF_APP_FIRST_LAUNCH)
    }

    fun setProfileCreatedPreference() {
        setNowBasedPreference(MainActivity.PREF_PROFILE_CREATED)
    }

    fun setDuressProfileCreatedPreference() {
        setNowBasedPreference(MainActivity.PREF_DURESS_PROFILE_CREATED)
    }

    fun clearProfileCreatedPreference() {
        getSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext)
            .edit()
            .remove(MainActivity.PREF_PROFILE_CREATED)
            .apply()
    }

    fun clearDuressProfileCreatedPreference() {
        getSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext)
            .edit()
            .remove(MainActivity.PREF_DURESS_PROFILE_CREATED)
            .apply()
    }

    private fun clearSharedPrefs(context: Context) {
        val prefs = getSharedPreferences(context)
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }

    private fun getSharedPreferences(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context)


    private fun setNowBasedPreference(preferenceKey: String) {
        getSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext)
            .edit()
            .putLong(preferenceKey, Calendar.getInstance().time.time)
            .apply()
    }

}