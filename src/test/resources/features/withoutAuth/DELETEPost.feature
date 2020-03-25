Feature: DeletePosts
  Test deletation

  @smoke
  Scenario: Verify Delete operation after POST
    Given I ensure to POST for "/posts" with body as
      | id | title         | author     |
      | 4  | Walk with dog | Monika Bak |
    And I Perform DELETE operation for "/posts/{postid}/"
      | postid |
      | 4      |
    And I perform GET op for "/posts/{postid}"
      | postid |
      | 4      |
    Then I "should not" see the body with title as "Walk with dog"