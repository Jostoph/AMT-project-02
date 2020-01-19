Feature: Deletion of a User

  Background:
    Given there is a Users server

  Scenario: successful deletion of a user
    Given I have an admin credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    When I POST it to the /users/bob@mail.com endpoint
    Then I receive a 204 status code

  Scenario: deletion attempt of none existing user
    Given I have an admin credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    When I POST it to the /users/fake@mail.com endpoint
    Then I receive a 404 status code

  Scenario: unauthorized attempt of user deletion
    Given I have a standard user credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    When I POST it to the /users/bob@mail.com endpoint
    Then I receive a 403 status code
