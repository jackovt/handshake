package io.nomasters.android.handshake.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import io.nomasters.android.handshake.MainApplication

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/15/19
 */
class DaggerOverridesTestRunner : AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        val testAppClassName = MainApplication::class.java.canonicalName
        return super.newApplication(cl, testAppClassName, context)
    }
}