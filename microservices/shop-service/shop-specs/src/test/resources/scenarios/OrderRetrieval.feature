Feature:Retrieval of order

  Background:
    Given there is a Shop server

  Scenario: retrieval of order
    Given I have a valid token
    When I GET it to the /shop endpoint
    Then I receive a 200 status code
    Then I receive an array list

  Scenario: unauthorized retrieval of order
    Given I have an invalid token
    When I GET it to the /shop endpoint
    Then I receive a 403 status code