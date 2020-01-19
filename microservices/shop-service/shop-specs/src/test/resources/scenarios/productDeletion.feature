Feature: deletion of products

  Background:
    Given there is a Shop server

  Scenario: successful deletion of product
    Given I have a valid token
    Given I create a product and POST it to /products
    When I DELETE it to the /products endpoint with 2 id
    Then I receive a 204 status code

  Scenario: deletion of not found product
    Given I have a valid token
    ## invalid id
    When I DELETE it to the /products endpoint with 0 id
    Then I receive a 404 status code

  Scenario: unauthorized deletion of product
    Given I have an invalid token
    When I DELETE it to the /products endpoint with 2 id
    Then I receive a 403 status code