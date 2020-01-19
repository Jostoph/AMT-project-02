Feature: Creation of order

  Background:
    Given there is a Shop server

  Scenario: successful creation of order
    Given I have a valid token
    Given I have a order credential
    When I POST it to the /shop endpoint
    Then I receive a 204 status code

  Scenario: unauthorized creation of order
    Given I have an invalid token
    Given I have a order credential
    When  I POST it to the /shop endpoint
    Then I receive a 403 status code

  Scenario: invalid creation of order
    Given I have a valid token
    Given I have a invalid order credential
    When I POST it to the /shop endpoint
    Then I receive a 400 status code