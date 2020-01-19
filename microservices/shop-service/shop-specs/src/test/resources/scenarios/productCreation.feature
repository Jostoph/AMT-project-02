Feature: Creation of products

  Background:
    Given there is a Shop server

  Scenario: successful creation of products
    Given I have a valid admin token
    Given I have a product credential
    When I POST it to the /products
    Then I receive a 201 status code

  Scenario: unauthorized creation of products cause of invalid token
    Given I have an invalid token
    Given I have a product credential
    When I POST it to the /products
    Then I receive a 403 status code