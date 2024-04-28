Feature: Show completion status on project
  Description: This use case involves displaying the completion status of a project
  to provide insight into its progress and current status.
  Actor: Employee


  Background:
    Given there is a project with name "New Project"

  Scenario: Employee views a projects completion status with no activities
    Then the completion status should be "0/0"

  Scenario: Employee views a projects completion status with no completed activities
    When the user creates an activity with the following details
      | Name          | Budget Hours | Start Week | End Week | Start Year | End Year | Initials   |
      | New Activity  | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
      | New Activity2 | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
      | New Activity3 | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
    Then the completion status should be "0/3"

  Scenario: Employee views a projects completion status with all completed activities
    When the user creates an activity with the following details
      | Name          | Budget Hours | Start Week | End Week | Start Year | End Year | Initials   |
      | New Activity  | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
      | New Activity2 | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
      | New Activity3 | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
    When the user switching completion state of the activity with name "New Activity"
    When the user switching completion state of the activity with name "New Activity2"
    When the user switching completion state of the activity with name "New Activity3"
    Then the completion status should be "3/3"

