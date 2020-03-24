Feature:
  Verify GET

  Scenario: Verify author of the post
    Given I perform GET for "/posts/1"
    Then I should see the author name as "typicode"

  Scenario: Verify authors of the post
    Given I perform GET for "/posts"
    Then I should see the author names

  Scenario: Verify results after set parameters for Get
    Given I perform GET for "/posts"
    Then I should see verify Get param

  Scenario: Verify street of complex data
    Given I perform GET for address "/location/"
      | id |
      | 1  |
    Then I should see the street name as "Baraniaka" for the "primary" address

  Scenario: Verify GET with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email          | password |
      | mbak@gmail.com | pass123  |
    Given I perform GET for "/posts/1"
    Then I should see the author name as "Monika Bak"

  Scenario: Verify GET with bearer authentication token for json schema
    Given I perform authentication operation for "/auth/login" with body
      | email          | password |
      | mbak@gmail.com | pass123  |
    Given I perform GET for "/posts/1"
    Then I should see the author name as "Monika Bak" with json validation