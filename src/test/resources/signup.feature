Feature: Sign Up
  I should be able to sign up with a unique user name and a password.

  Scenario: Successful sign up
    Given I am at the sign up page
    When I enter a name
    And I enter a password
    And I enter a password verification
    And I submit
    Then I should be at the registered page