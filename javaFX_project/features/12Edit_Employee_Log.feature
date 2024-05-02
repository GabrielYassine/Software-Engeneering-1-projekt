Feature: Edit Employee Schedule
  Description: Employee edits hours registered on an activity on a specific day
  Actor: Employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Huba     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     | Huba      |
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date "2021-06-01"

  Scenario: Employee edits hours registered on an activity on a specific day
    When the employee with initials "Huba" selects log of the activity "New Activity" on the date "2021-06-01" in the project with ID "24001"
    And the employee edits the hours to "15"
    Then the activity "New Activity" should have "15" hours registered by "Huba" on the date "2021-06-01"
    And the activity "New Activity" should have "15" hours registered in total
