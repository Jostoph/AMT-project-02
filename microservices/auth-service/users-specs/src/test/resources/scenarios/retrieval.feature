Feature: Retrieval of Users

  Background:
    Given there is a Users server

  Scenario: successful retrieval of the users
    Given I have a standard user credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    When I GET the /users endpoint
    Then I receive a 200 status code
    Then I receive an array of usersDTO

  Scenario: unauthorized retrieval of users attempt
    When I GET the /users endpoint with invalid token
    Then I receive a 403 status code