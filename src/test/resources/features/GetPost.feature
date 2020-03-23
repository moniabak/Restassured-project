Feature:
  Verify GET

  Scenario: Verify author of the post
    Given I perform GET for "/posts"
    Then I should see the author name as "typicode"

  Scenario: Verify author of the post
    Given I perform GET for "/posts"
    Then I should see the author names

  Scenario: Verify Parameter of Get
    Given I perform GET for "/posts"
    Then I should see verify Get param

  Scenario: Verify POST operation
    Given I perform POST for "/posts"

  Scenario: Verify Post operation for Profile
    Given I perform POST for "/posts/{profileNo}/profile" with body
      | name   | profile |
      | Monika | 2       |
    Then I should see the body has name as "Monia"
