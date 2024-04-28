Feature: open/close Activity
  Description: Employee open or closes an activity
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |

  Scenario: User marks an activity as completed
    When the user switching completion state of the activity with name "New Activity"
    Then the activity with name "New Activity" is completed

  Scenario: User marks an activity as not completed
    When the user switching completion state of the activity with name "New Activity"
    When the user switching completion state of the activity with name "New Activity"
    Then the activity with name "New Activity" is not completed