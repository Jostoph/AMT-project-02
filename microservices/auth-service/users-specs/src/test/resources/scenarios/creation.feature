Feature: Creation of Users

  Background:
    Given there is a Users server

  Scenario: successful creation of a user
    Given I have an admin credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    Given I have a user payload
    When I POST it to the /users endpoint
    Then I receive a 201 status code

  Scenario: creation attempt of an existing user
    Given I have an admin credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    Given I have an existing user payload
    When I POST it to the /users endpoint
    Then I receive a 409 status code

  Scenario: creation attempt by an unauthorized user
    Given I have a standard user credentials payload
    When I POST it to the /connection endpoint
    Then I receive a token
    Given I have a user payload
    When I POST it to the /users endpoint
    Then I receive a 403 status code