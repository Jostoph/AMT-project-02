Feature: Retrieval of products

  Background:
    Given there is a Shop server

  Scenario: successful retrieval of products
    Given I have a valid token
    When I GET it to the /products endpoint with 2 pages and 3 size
    Then I receive a 200 status code
    Then I receive a array list

  Scenario: unauthorized retrieval of products cause of invalid pages
    Given I have a valid token
    When  I GET it to the /products endpoint with 0 pages and 3 size
    Then I receive a 400 status code

  Scenario: unauthorized retrieval of products cause of invalid  size
    Given I have a valid token
    When  I GET it to the /products endpoint with 2 pages and 0 size
    Then I receive a 400 status code

  Scenario: unauthorized retrieval of products cause of invalid token
    Given I have an invalid token
    When I GET it to the /products endpoint
    Then I receive a 403 status code