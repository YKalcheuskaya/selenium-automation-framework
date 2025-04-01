Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given User is on landing page
    When User logs in with username "user1" and password "password123"
    Then Home page is displayed

  Scenario: Failed login with invalid credentials
    Given User is on landing page
    When User logs in with username "invalid_user" and password "wrongpass"
    Then Error message is displayed

