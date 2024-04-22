Feature: show available employees
  Description: the user can view available employees
  Actor: employee

  Background:
    Given there are employees with the following initials
      | Initials |
      | Huba     |
      | Abed     |
    And there is a project with name "New Project" that contains an employee "huba".
    When you search after year 2024 and month 10
    Then the system will show display the following details
      | Date       | Activity Name | Hours | Activities |
      | 2024-02-10 | New Activity  | 5     | 0/20       |


#    Scenario: An employee is available
#      Given that there is an employee "huba"
#      And the employee is working on less than 20 activities in a week
#      When the system checks if the employee is available
#      Then the employee is available

#  Scenario: Multiple employees are available
#    Given that there are 10 employees
#    And some employees are working on less than 20 activities in a week
#    When the system checks if the employees are available
#    Then some of the employees are available
#
#  Scenario: All employees are available
#    Given that there are 10 employees
#    And each employee are working on less than 20 activities in a week
#    When the system checks if the employees are available
#    Then all the employees are available
#
#  Scenario: An employee is not available
#    Given that there is an employee "huba"
#    And the employee is working on 20 activities in a week
#    When the system checks if the employee is available
#    Then the employee is not available
#    And the error message "Employee is not available" is shown
#
#  Scenario: No employees are available
#    Given that there are 10 employees
#    And each employee is working on 20 activities in a week
#    When the system checks if the employees are available
#    Then the employees are not available
#    And the error message "No employees available" is shown
#
#  Scenario: An employee is sick
#    Given that there is an employee "huba"
#    And the employee has a sick day
#    When the system checks if the employee is available
#    Then the employee is not available
#    And the error message "Employee is sick" is shown
#
#  Scenario: An employee is on vacation
#    Given that there is an employee "huba"
#    And the employee is on vacation
#    When the system checks if the employee is available
#    Then the employee is not available
#    And the error message "Employee is on vacation" is shown
