Feature: User opens app for the first time

  In order to use the app,
  As a user I want to discover what the app is about and create credentials to use it,
  So that I can start messaging people securely

  Scenario: The app has never been opened before
    Given the app cannot detect it has been opened before
    When the launcher activity is started
    Then the user should see the splash screen
    And after 2 seconds splash screen should disappear
    And the user should see the intro screen

  Scenario: The app has had all app storage and cache wiped
    Given the app cannot detect it has been opened before
    When the launcher activity is started
    Then the user should see the splash screen
    And after 2 seconds splash screen should disappear
    And the user should see the intro screen

  Scenario: The app has been opened at least once before, but profile creation has not completed
    Given the app has been opened at least once before
    And the user has not yet created a profile
    When the launcher activity is started
    Then the user should see the splash screen
    And after 2 seconds splash screen should disappear
    And the user should see the intro screen

  Scenario: The app has been opened at least once before, but duress profile creation has not completed
    Given the app has been opened at least once before
    And the user has created a profile
    And the user has not yet created a duress profile
    When the launcher activity is started
    Then the user should see the splash screen
    And after 2 seconds splash screen should disappear
    And the user should see the duress profile creation screen

  Scenario: The app has been opened at least once before, and duress profile has been created or skipped
    Given the app has been opened at least once before
    And the user has created a profile
    And the user has created a duress profile
    When the launcher activity is started
    Then the user should see the splash screen
    And after 2 seconds splash screen should disappear
    And the user should see the login screen