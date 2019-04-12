Feature: User opens app for the first time

  In order to use the app,
  As a user I want to discover what the app is about and create credentials to use it,
  So that I can start messaging people securely

  Scenario: The app has never been opened before
    Given the app cannot detect it has been opened before
    When the launcher activity is started
    Then the user should see the splash screen
    And and after 3 seconds the user should see the
    Then the user should see the intro screen

  Scenario: The app has had all app storage and cache wiped
    Given the app cannot detect it has been opened before
    When the launcher activity is started
    Then the user should see the splash screen
    And and after 3 seconds the user should see the
    Then the user should see the intro screen