Feature: show available employees
  Description: the user can view available employees
  Actor: employee

  Scenario: An employee is available
    Given that there is an employee "huba"
    And the employee is working on less than 20 activities in a week
    When the system checks if the employee is available
    Then the employee is available

  Scenario: Multiple employees are available
    Given that there are 10 employees
    And each employee is working on less than 20 activities in a week
    When the system checks if the employees are available
    Then the employees are available

  Scenario: An employee is not available
    Given that there is an employee "huba"
    And the employee is working on 20 activities in a week
    When the system checks if the employee is available
    Then the employee is not available

  Scenario: Multiple employees are not available
    Given that there are 10 employees
    And each employee is working on 20 activities in a week
    When the system checks if the employees are available
    Then the employees are not available

  Scenario: An employee is sick
    Given that there is an employee "huba"
    And the employee has a sick day
    When the system checks if the employee is available
    Then the employee is not available

  Scenario: An employee is on vacation
    Given that there is an employee "huba"
    And the employee is on vacation
    When the system checks if the employee is available
    Then the employee is not available
