Feature: Register time on activity
  Description: Record the amount of time spent on an activity.
  Actor: Employee

  Background: The app has a project, and a couple of employees and an activity
    Given there exists a project with name "New Project"
    And there is an employee with initials "Huba" assigned to the project with ID 24001
    And there exists an activity with the following details
      | projectID | name         | budgetHours | startWeek | endWeek |
      | 24001     | New Activity | 100         | 1         | 4       |

  Scenario: The user registers the time spent on the activity on a specific date
    When the employee "Huba" registers "5" hours on the activity "New Activity" on "2023-03-01"
    Then the employee "Huba"'s time spent on "2023-03-01" on activity "New Activity" should be 5 hours

  Scenario: The user tries to register a negative number of hours on the activity
    When the employee "Huba" registers "-5" hours on the activity "New Activity" on "2023-03-01"
    Then an error message "Registered hours cannot be negative" should be given

  Scenario: The user tries to register as an employee that is not assigned to the project
    When the employee "Abcd" registers "5" hours on the activity "New Activity" on "2023-03-01"
    Then an error message "Employee with initials 'Abcd' not found" should be given

  Scenario: The user tries to register time with no date
    When the employee "Huba" registers "5" hours on the activity "New Activity" on ""
    Then an error message "Date not selected" should be given
