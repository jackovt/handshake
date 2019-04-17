package io.nomasters.android.handshake.test

import androidx.test.espresso.intent.Intents
import androidx.test.rule.ActivityTestRule
import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.nomasters.android.handshake.HandshakeRobot
import io.nomasters.android.handshake.MainActivity
import io.nomasters.android.handshake.ui.intro.IntroRobot
import io.nomasters.android.handshake.ui.splash.SplashRobot

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/15/19
 */
class NavigationStepDefinitions {

    private lateinit var activityTestRule: ActivityTestRule<MainActivity>
    private val handshakeRobot = HandshakeRobot()
    private val splashRobot = SplashRobot()
    private val introRobot = IntroRobot()

    @Before
    fun before(scenario: Scenario) {
        activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    @Given("^the app cannot detect it has been opened before")
    fun given_the_app_cannot_detect_it_has_been_opened_before() {
        handshakeRobot.clearCache()
        handshakeRobot.launchMainActivity(activityTestRule)
    }

    @When("^the launcher activity is started")
    fun when_the_launcher_activity_is_started() {
        handshakeRobot.isMainActivityLaunched()
    }

    @Then("^the user should see the splash screen")
    fun then_the_user_should_see_the_splash_screen() {
        splashRobot.isSplashScreenShowing(activityTestRule)
    }

    @Then("^after 2 seconds splash screen should disappear")
    fun then_after_2_seconds_splash_screen_should_disappear() {
        splashRobot.isSplashScreenGone(activityTestRule)
    }

    @Then("^the user should see the intro screen")
    fun then_the_user_should_see_the_intro_screen() {
        introRobot.isIntroScreenShowing(activityTestRule)
    }
}
