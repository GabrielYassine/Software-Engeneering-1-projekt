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
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |

  Scenario: User edits an activity successfully
    When the user edits the activity with name "New Activity" with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And the activity with name "New Activity" should have the following details
      | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      | 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |

  Scenario: User edits an activity with no name
    When the user edits the activity with name "New Activity" with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      |             | 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "Name missing" should be given

  Scenario: User edits an activity with no budget hours
    When the user edits the activity with name "New Activity" with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year  | Initials               |
    | New Activity|              | 5          | 8        | 2024       | 2024      |Huba, Abed, Dora, Jama |
    And an error message "Hours missing" should be given

  Scenario: User edits an activity with no start week
    When the user edits the activity with name "New Activity" with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 200          |            | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "No week given" should be given

  Scenario: User edits an activity with start week over 52
    When the user edits the activity with name "New Activity" with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 200          | 53         | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "Week value out of bounds" should be given

  Scenario: User edits an activity with start week 0
    When the user edits the activity with name "New Activity" with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 200          | 0          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "Week value out of bounds" should be given

  Scenario: User edits an activity with no end week
    When the user edits the activity with name "New Activity" with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 200          | 5          |          | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "No week given" should be given

  Scenario: User edits an activity with end week over 52
    When the user edits the activity with name "New Activity" with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
      | New Activity| 200          | 5         | 53        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "Week value out of bounds" should be given

  Scenario: User edits an activity with end week 0
    When the user edits the activity with name "New Activity" with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
      | New Activity| 200          | 5          | 0        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    And an error message "Week value out of bounds" should be given

  Scenario: User edits an activity with no initials
    When the user edits the activity with name "New Activity" with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year  |Initials |
      | New Activity| 200          | 5          | 8        | 2024       | 2024      |         |
    And the activity with name "New Activity" should have the following details
      | Budget Hours | Start Week | End Week | Start Year | End Year | Initials |
      | 200          | 5          | 8        | 2024       | 2024     |          |


