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
import io.nomasters.android.handshake.ui.introduress.IntroDuressProfileRobot
import io.nomasters.android.handshake.ui.login.LoginRobot
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
    private val introDuressProfileRobot = IntroDuressProfileRobot()
    private val loginRobot = LoginRobot()

    @Before
    fun before(scenario: Scenario) {
        activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
        activityTestRule.activity.finish()
    }

    @Given("^the app cannot detect it has been opened before")
    fun given_the_app_cannot_detect_it_has_been_opened_before() {
        handshakeRobot.clearCache()
    }

    @Given("^the app has been opened at least once before")
    fun given_the_app_has_been_opened_at_least_once_before() {
        handshakeRobot.clearCache()
        handshakeRobot.setAppFirstLaunchedPreference()
    }

    @Given("^the user has not yet created a profile")
    fun given_the_user_has_not_yet_created_a_profile() {
        handshakeRobot.clearProfileCreatedPreference()
    }

    @Given("^the user has created a profile")
    fun given_the_user_has_created_a_profile() {
        handshakeRobot.setProfileCreatedPreference()
    }

    @Given("^the user has created a duress profile")
    fun given_the_user_has_created_a_duress_profile() {
        handshakeRobot.setDuressProfileCreatedPreference()
    }

    @Given("^the user has not yet created a duress profile")
    fun given_the_user_has_not_yet_created_a_duress_profile() {
        handshakeRobot.clearDuressProfileCreatedPreference()
    }

    @When("^the launcher activity is started")
    fun when_the_launcher_activity_is_started() {
        handshakeRobot.launchMainActivity(activityTestRule)
        handshakeRobot.isMainActivityLaunched()
    }

    @Then("^the user should see the splash screen")
    fun then_the_user_should_see_the_splash_screen() {
        splashRobot.isSplashScreenShowing(activityTestRule)
    }

    @Then("^after 2 seconds the splash screen should disappear")
    fun then_after_2_seconds_splash_screen_should_disappear() {
        splashRobot.isSplashScreenGone(activityTestRule)
    }

    @Then("^the user should see the intro screen")
    fun then_the_user_should_see_the_intro_screen() {
        introRobot.isIntroScreenShowing(activityTestRule)
    }

    @Then("^the user should see the duress profile creation screen")
    fun then_the_user_should_see_the_duress_profile_creation_screen() {
        introDuressProfileRobot.isIntroDuressProfileScreenShowing(activityTestRule)
    }

    @Then("^the user should see the login screen")
    fun then_the_user_should_see_the_login_screen() {
        loginRobot.isLoginScreenShowing(activityTestRule)
    }
}
