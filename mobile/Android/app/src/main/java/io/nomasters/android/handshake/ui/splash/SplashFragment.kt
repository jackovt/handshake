package io.nomasters.android.handshake.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.R
import java.util.*
import kotlin.concurrent.schedule

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SplashFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SplashFragment : Fragment() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }

    override fun onResume() {
        super.onResume()

        val firstFragment = getFirstFragmentToShow()
        Timer("AfterSplashScreen", false).schedule(SplashFragment.SPLASH_SCREEN_DELAY) {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                navigateToFragment(firstFragment)
            }
        }
    }

    private fun getFirstFragmentToShow(): Int {
        return if (isAppFirstLaunch()) {
            preferences.edit()
                .putLong(MainActivity.PREF_APP_FIRST_LAUNCH, Calendar.getInstance().time.time)
                .apply()
            R.id.introFragment
        } else {
            if (userHasCreatedProfile()) {
                if (userHasCreatedDuressProfile()) R.id.loginFragment
                else R.id.introDuressProfileFragment
            } else {
                R.id.introFragment
            }
        }
    }

    fun navigateToFragment(firstFragmentId: Int) {
        val navController = findNavController()
        navController.navigate(firstFragmentId)
    }

    private fun isAppFirstLaunch() = !preferences.contains(MainActivity.PREF_APP_FIRST_LAUNCH)

    private fun userHasCreatedProfile() = preferences.contains(MainActivity.PREF_PROFILE_CREATED)

    private fun userHasCreatedDuressProfile() = preferences.contains(MainActivity.PREF_DURESS_PROFILE_CREATED)

    companion object {
        val SPLASH_SCREEN_DELAY: Long = 2000
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SplashFragment.
         */
        @JvmStatic
        fun newInstance() =
            SplashFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
