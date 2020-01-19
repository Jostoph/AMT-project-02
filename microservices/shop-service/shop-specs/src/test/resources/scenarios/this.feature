Feature: Retrievial of products

  Background:
    Given there is a Shop server

  Scenario: successful retrievial of products
    Given I have a valid token
    When I POST it to the /products endpoint
    Then I receive a 200 status code
    Then I receive a array list

  Scenario: unauthorized retrievial of products cause of invalid page size
    Given I have a valid token
    When I POST it to the /products endpoint with a -1 page size
    Then I receive a 400 status code

  Scenario: unauthorized retrievial of products cause of invalid token
    Given I have an invalid token
    When I POST it to the /products endpoint
    Then I receive a 403 status code