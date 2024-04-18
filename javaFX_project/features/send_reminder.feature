Feature: send reminder
  Description: the system sends an employee an important reminder
  Actor: system

  Background:
    Given there is a project with name "New Project"
    And there is an activity with name "New Activity" in the project, with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials   |
      | New Activity| 100          | 5          | 8        | Huba, Abed |

  Scenario: Employee receives a reminder for unregistered daily work
    Given that there is an employee "Huba"
    When the employee has not registered his daily work for the current day
    Then the employee should receive the notification "Register your daily work"

  Scenario: Employee exceeds the maximum activity limit
    Given that there is an employee "Huba"
    And the employee is working on 20 activities in a week
    When the employee with initials "Huba" registers 10 hours on the activity "New Activity" on the date "2021-06-01"
    Then the employee should receive the notification "You're working on too many activities"