Feature: Verify GET

  Scenario: Verify GET with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email          | password |
      | mbak@gmail.com | pass123  |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "typicode" with auth

  Scenario: Verify street of complex data
    Given I perform authentication operation for "/auth/login" with body
      | email          | password |
      | mbak@gmail.com | pass123  |
    And I perform GET for address "/location/"
      | id |
      | 1  |
    Then I should see the street name as "Baraniaka" for the "primary" address

  Scenario: Verify GET with bearer authentication token for json schema
    Given I perform authentication operation for "/auth/login" with body
      | email          | password |
      | mbak@gmail.com | pass123  |
    And I perform GET operation for "/posts/1"
    Then I should see response body as json validation