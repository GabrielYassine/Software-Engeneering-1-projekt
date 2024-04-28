#Feature: send reminder
#  Description: the system sends an employee an important reminder
#  Actor: system
#
#  Scenario: User signs into email with existing employee initials
#    Given that there is an employee "Huba"
#    When the employee signs into their email using their initials
#    Then the employee is logged in
#
#  Scenario: User signs into email nonexistent employee initials
#    Given that there is an employee "Huba"
#    When the employee signs into their email using wrong initials
#    Then the employee is not logged in
#
#  Scenario: Employee receives a reminder for unregistered daily work
#    Given that there is an employee "Huba"
#    And the employee has not registered his daily work for the current day
#    When the system sends a reminder email
#    Then the email with subject "Work" and text "Register your daily work" is in the employees inbox
#
#  Scenario: Employee exceeds the maximum activity limit
#    Given that there is an employee "Huba"
#    And the employee has registered his daily work for the current day
#    And the employee is working on 21 activities in a week
#    When the system sends a reminder email
#    Then the email with subject "Work" and text "You're working on too many activities" is in the employees inbox