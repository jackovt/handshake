package io.nomasters.android.handshake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity() {

    companion object {
        val PREF_APP_FIRST_LAUNCH: String = "PREF_APP_FIRST_LAUNCH"
        val PREF_PROFILE_CREATED: String = "PREF_PROFILE_CREATED"
        val PREF_DURESS_PROFILE_CREATED: String = "PREF_DURESS_PROFILE_CREATED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.mainNavigationFragment).navigateUp()

}
