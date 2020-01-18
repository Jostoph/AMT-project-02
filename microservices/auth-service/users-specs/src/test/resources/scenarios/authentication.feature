Feature: Authentication of Users

  Background:
    Given there is a Users server

  Scenario: successful authentication of existing admin user
    Given  I have an admin credentials payload
    When I POST it to the /connection endpoint
    Then I receive a 200 status code
    Then I receive a token