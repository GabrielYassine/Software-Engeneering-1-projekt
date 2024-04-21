Feature: send reminder
  Description: the system sends an employee an important reminder
  Actor: system

#  Scenario: User signs into email with existing employee initials
#    Given that there is an employee "Huba"
#    When the employee signs into their email using their initials
#    Then the employee is logged in

#  Scenario: User signs into email nonexistent employee initials
#    Given that there is an employee "Huba"
#    When the employee signs into their email using wrong initials
#    Then the employee is not logged in

  Scenario: Employee receives a reminder for unregistered daily work
    Given that there is an employee "Huba"
    When the employee has not registered his daily work for the current day
    Then the employee should receive the notification "Register your daily work"

  Scenario: Employee exceeds the maximum activity limit
    Given that there is an employee "Huba"
    And the employee is working on 20 activities in a week
    When the employee with initials "Huba" registers 10 hours on the activity "New Activity" on the date "2021-06-01"
    Then the employee should receive the notification "You're working on too many activities"