Feature: send reminder
  Description: the system sends an employee an important reminder
  Actor: system

  Scenario: Employee receives a reminder for unregistered daily work
    Given that there is an employee "huba"
    When the employee has not registered his daily work for the current day
    Then the employee should receive the notification "Register your daily work"
#
#  Scenario: Employee exceeds the maximum activity limit
#    Given that there is an employee "huba"
#    And the employee is working on 20 activities in a week
#    When the employee tries to start a new activity
#    Then the employee should receive a notification "Can't work on more activities"