package io.nomasters.android.handshake.util

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher


/**
 * @author JH431939 (Jack Hughes)
 * @since 4/16/19
 */
class EspressoUtils {
    companion object {
        /**
         * Perform action of waiting for a specific time.
         */
        fun waitFor(millis: Long): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun getDescription(): String {
                    return "Wait for $millis milliseconds."
                }

                override fun perform(uiController: UiController, view: View) {
                    uiController.loopMainThreadForAtLeast(millis)
                }
            }
        }
    }
}