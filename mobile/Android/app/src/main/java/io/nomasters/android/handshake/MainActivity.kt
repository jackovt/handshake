package io.nomasters.android.handshake

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.nomasters.android.handshake.ui.intro.IntroFragment
import io.nomasters.android.handshake.ui.splash.SplashFragment
import java.util.*

class MainActivity : AppCompatActivity(),
    SplashFragment.OnFragmentInteractionListener,
    IntroFragment.OnFragmentInteractionListener {

    private val PREF_APP_FIRST_LAUNCH: String = "PREF_APP_FIRST_LAUNCH"

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val firstFragment = getFirstFragmentToShow()
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, firstFragment, SplashFragment::class.java.simpleName)
            .commitNow()

    }

    private fun getFirstFragmentToShow(): Fragment {
        if (isAppFirstLaunch()) {
            preferences.edit()
                .putLong(PREF_APP_FIRST_LAUNCH, Calendar.getInstance().time.time)
                .apply()
            return SplashFragment.newInstance()
        }
        return IntroFragment.newInstance()
    }

    private fun isAppFirstLaunch() = !preferences.contains(PREF_APP_FIRST_LAUNCH)
}
