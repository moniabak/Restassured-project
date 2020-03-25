Feature: Post operations

  Scenario: Verify POST operation
    Given I perform POST for "/posts"

  Scenario: Verify Post operation for Profile
    Given I perform POST for "/posts/{profileNo}/profile" with body
      | name   | profile |
      | Monika | 2       |
    Then I should see the body has name as "Monia"