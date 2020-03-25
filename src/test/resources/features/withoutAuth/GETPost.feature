Feature: Verify GET without authorization

  Scenario: Verify author of the exact post
    Given I perform GET for "/posts/1"
    Then I should see the author name as "typicode"

  Scenario: Verify authors of the post
    Given I perform GET for "/posts"
    Then I should see the author names

  Scenario: Verify results after set parameters for Get
    Given I perform GET for "/posts"
    Then I should see verify Get param