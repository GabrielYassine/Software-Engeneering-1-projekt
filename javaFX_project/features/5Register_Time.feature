Feature: Register time on activity
  Description: Record the amount of time spent on an activity.
  Actor: Employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Initials |
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |

  Scenario: User registers time on activity successfully
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then the activity "New Activity" should have "10" hours registered by "Huba" on the date "2021-06-01"
    And the activity "New Activity" should have "10" hours registered in total

  Scenario: User registers time on activity with initials not in the activity
    When the employee with initials "Dora" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then the activity "New Activity" should have "10" hours registered by "Dora" on the date "2021-06-01"
    And the activity "New Activity" should have "10" hours registered in total

  Scenario: User registers time on activity with initials not in the project
    When the employee with initials "Zama" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then an error message "Employee with those initials not found" should be given
#
  Scenario: User registers time on activity with no initials
    When the employee with initials "" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then an error message "Name missing" should be given

  Scenario: User registers time on activity with no hours
    When the employee with initials "Huba" registers "" hours on the activity "New Activity" on the date "2021-06-01"
    Then an error message "Error registering hours" should be given

  Scenario: User registers time on activity with no date
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date ""
    Then an error message "Error registering hours" should be given

  Scenario: User registers time on activity with hours that is not modulo 0.5
    When the employee with initials "Huba" registers "10.3" hours on the activity "New Activity" on the date "2021-06-01"
    Then the activity "New Activity" should have "10.5" hours registered by "Huba" on the date "2021-06-01"
    And the activity "New Activity" should have "10.5" hours registered in total

  Scenario: User registers time on activity with hours that is not modulo 0.5
    When the employee with initials "Huba" registers "10.2" hours on the activity "New Activity" on the date "2021-06-01"
    Then the activity "New Activity" should have "10.0" hours registered by "Huba" on the date "2021-06-01"
    And the activity "New Activity" should have "10.0" hours registered in total