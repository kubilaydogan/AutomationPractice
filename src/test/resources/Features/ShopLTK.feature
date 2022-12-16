@login @ltk @LTK-1234 @regression
Feature: LTK-1234 New User SignUp and login functionality

  As a new user, I need to sign up to be able to redirected to discover home page.

  Background:
    Given user is on main page

  @DE3344 
  Scenario: New user should be able to signup and redirected to home page
    When user signup a new user
      | email    | randomEmail |
      | password | pass1234    |
    Then verify user is redirected to Discover home page
    And user logs out successfully

  @negatif
  Scenario Outline: Existing customer shouldn't be able to re-sign up
    When user signup a new user
      | email    | deneme1@gmail.com |
      | password | pass1234         |
    Then verify user is notified with "<warning>" message

    Examples:
    | warning |
    | Oops! The email you've entered is already associated with an LTK account.|


