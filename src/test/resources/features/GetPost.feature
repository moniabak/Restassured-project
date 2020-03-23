Feature:
  Verify GET

  Scenario: Verify author of the post
    Given I perform GET for "/post"
    And I perform GET for the post number "1"
    Then I should see the author name as "Monika Bąk"

  Scenario: Verify author of the post
    Given I perform GET for "/post"
    Then I should see the author names

  Scenario: Verify Parameter of Get
    Given I perform GET for "/post"
    Then I should see verify Get param