Feature: send reminder
  Description: the system sends an employee an important reminder
  Actor: system

  Scenario: Employee receives a reminder for unregistered daily work
    Given there is an employee "John"
    And "John" has not registered his daily work for the current day
    When the system checks for unregistered daily work
    Then "John" should receive a notification reminding him to register his daily work

  Scenario: Employee exceeds the maximum activity limit
    Given there is an employee "Alice"
    And "Alice" is already working on 10 activities
    When the employee tries to start a new activity
    Then the employee should receive a notification informing her that she has reached the maximum limit of 10 activities

  Scenario: Project leader receives a notification for activity budget exceedance
    Given there is a project "Project X"
    Given there is a project leader "Emily" assigned to "Project X"
    And there is an activity "Task 1" in "Project ABC" with a budgeted number of hours
    And "Task 1" has exceeded the budgeted number of hours
    When the system detects the exceedance of budgeted hours for "Task 1"
    Then "Emily" should receive a notification informing themg about the budget exceedance of "Task 1" in "Project ABC"