Feature: PutPost
  Test

  @smoke
  Scenario: Verify Put operation after POST
    Given I ensure to POST for "/posts" with body as
      | id | title         | author     |
      | 4  | Walk with dog | Monika Bak |
    And I Perform PUT operation for "/posts/{postid}/"
      | id | title         | author     |
      | 4  | Walk not here | Monika Bak |
    And I perform GET op for "/posts/{postid}"
      | postid |
      | 4      |
    Then I "should" see the body with title as "Walk not here"