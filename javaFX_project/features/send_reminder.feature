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
