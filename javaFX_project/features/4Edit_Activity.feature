Feature: Edit Activity
  Description: Employee edits an activity
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Initials |
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    And there is an activity with name "New Activity" in the project, with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      | New Activity| 100          | 5          | 8        | Huba, Abed, Dora, Jama |

  Scenario: User edits an activity successfully
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          | 5          | 8        | Huba, Abed, Dora, Jama |
    Then the activity should have the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          | 5          | 8        | Huba, Abed, Dora, Jama |

  Scenario: User edits an activity with no name
    When the user edits the activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      |             | 100          | 5          | 8        | Huba, Abed, Dora, Jama |
    And an error message "Name missing" should be given

  Scenario: User edits an activity with no budget hours
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity|              | 5          | 8        | Huba, Abed, Dora, Jama |
    And an error message "Budget hours missing" should be given

  Scenario: User edits an activity with no start week
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          |            | 8        | Huba, Abed, Dora, Jama |
    And an error message "Start week value error" should be given

  Scenario: User edits an activity with start week over 52
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          | 53         | 8        | Huba, Abed, Dora, Jama |
    And an error message "Start week value error" should be given

  Scenario: User edits an activity with start week 0
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          | 0          | 8        | Huba, Abed, Dora, Jama |
    And an error message "Start week value error" should be given

  Scenario: User edits an activity with no end week
    When the user edits the activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 200          | 5          |          | Huba, Abed, Dora, Jama |
    And an error message "End week value error" should be given

  Scenario: User edits an activity with end week over 52
    When the user edits the activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      | New Activity| 200          | 5         | 53        | Huba, Abed, Dora, Jama |
    And an error message "End week value error" should be given

  Scenario: User edits an activity with end week 0
    When the user edits the activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      | New Activity| 200          | 5          | 0        | Huba, Abed, Dora, Jama |
    And an error message "End week value error" should be given

  Scenario: User edits an activity with no initials
      When the user edits the activity with the following details
        | Name        | Budget Hours | Start Week | End Week | Initials |
        | New Activity| 200          | 5          | 8        |          |
    Then the activity should have the following details
    | Name        | Budget Hours | Start Week | End Week | Initials |
    | New Activity| 200          | 5          | 8        |          |


