package io.nomasters.android.handshake

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.nomasters.android.handshake.ui.intro.IntroFragment
import io.nomasters.android.handshake.ui.introduress.IntroDuressProfileFragment
import io.nomasters.android.handshake.ui.introprofile.IntroProfileFragment
import io.nomasters.android.handshake.ui.login.LoginFragment
import io.nomasters.android.handshake.ui.splash.SplashFragment
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(),
    SplashFragment.OnFragmentInteractionListener,
    IntroFragment.OnFragmentInteractionListener,
    IntroProfileFragment.OnFragmentInteractionListener,
    IntroDuressProfileFragment.OnFragmentInteractionListener,
    LoginFragment.OnFragmentInteractionListener {

    private val PREF_APP_FIRST_LAUNCH: String = "PREF_APP_FIRST_LAUNCH"
    private val PREF_PROFILE_CREATED: String = "PREF_PROFILE_CREATED"
    private val PREF_DURESS_PROFILE_CREATED: String = "PREF_DURESS_PROFILE_CREATED"

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_main,
                SplashFragment.newInstance(),
                SplashFragment::class.java.simpleName
            ).commitNow()
        val firstFragment = getFirstFragmentToShow()
        Timer("AfterSplashScreen", false).schedule(SplashFragment.SPLASH_SCREEN_DELAY) {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                navigateToFragment(firstFragment)
            }
        }

    }

    private fun navigateToFragment(firstFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.container_main,
            firstFragment,
            firstFragment::class.java.simpleName
        ).commitNow()
    }

    override fun navigateToProfileCreation() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container_main,
            IntroProfileFragment.newInstance(),
            IntroProfileFragment::class.java.simpleName
        ).commitNow()
    }

    override fun navigateToDuressProfileCreation() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container_main,
            IntroDuressProfileFragment.newInstance(),
            IntroDuressProfileFragment::class.java.simpleName
        ).commitNow()
    }

    override fun navigateToLogin() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container_main,
            LoginFragment.newInstance(),
            LoginFragment::class.java.simpleName
        ).commitNow()
    }

    override fun login() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getFirstFragmentToShow(): Fragment {
        return if (isAppFirstLaunch()) {
            preferences.edit()
                .putLong(PREF_APP_FIRST_LAUNCH, Calendar.getInstance().time.time)
                .apply()
            IntroFragment.newInstance()
        } else {
            if (userHasCreatedProfile()) {
                if (userHasCreatedDuressProfile()) LoginFragment.newInstance()
                else IntroDuressProfileFragment.newInstance()
            } else {
                IntroProfileFragment.newInstance()
            }
        }
    }

    private fun isAppFirstLaunch() = !preferences.contains(PREF_APP_FIRST_LAUNCH)

    private fun userHasCreatedProfile() = preferences.contains(PREF_PROFILE_CREATED)

    private fun userHasCreatedDuressProfile() = preferences.contains(PREF_DURESS_PROFILE_CREATED)

}
