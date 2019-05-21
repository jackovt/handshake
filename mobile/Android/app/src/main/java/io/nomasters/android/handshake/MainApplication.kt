package io.nomasters.android.handshake

import android.app.Application
import io.nomasters.android.handshake.di.DaggerMainComponent
import io.nomasters.android.handshake.di.MainComponent

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/15/19
 */
class MainApplication : Application() {
    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.create()
        mainComponent.inject(this)
    }
}