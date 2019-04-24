@NavigationFlow
Feature: User navigates through the app

  In order to use the app,
  As a user I want to be able to navigate from screen to screen,
  So that I can view all available parts of the app

  Scenario: The user navigates from the intro screen to the profile creation screen
    Given the app is started
    And the splash screen is done showing
    And the Intro Screen is showing
    When the user presses "Let's Get Started" on the Intro Screen
    Then the user should see the Profile Creation Screen

  Scenario: The user navigates from the profile creation screen to the duress profile creation screen
    Given the app is started
    And the splash screen is done showing
    And the Profile Creation Screen is showing
    When the user presses "Submit" on the Profile Creation Screen
    Then the user should see the Duress Profile Creation Screen