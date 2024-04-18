Feature: open/close Activity
  Description: Employee open or closes an activity
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

  Scenario: User marks an activity as completed
    Given an activity is open
    When the user closes the activity
    Then the activity is completed

  Scenario: User marks an activity as not completed
    Given an activity is closed
    When the user opens the activity
    Then the activity is not completed