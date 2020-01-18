Feature: Authentication of Users

  Background:
    Given there is a Users server

  Scenario: successful authentication of existing user
    Given I have a credentials payload
    When I POST it to the /connection endpoint
    Then I receive a 200 status code