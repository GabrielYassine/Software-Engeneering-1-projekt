Feature: show available employees
  Description: the user can view available employees
  Actor: employee

  Scenario: An employee is available
    Given that there is an employee "huba"
    When the employee doesn't have an acitivity within the timeframe
    And the employee is working on less than 20 activities in a week
    Then the employee is available

  Scenario: Multiple employees are available
    Given that there are employees "huba", "ilas" and "sdas"
    And each employee doesn't have any activities within the timeframe
    And each employee is working on less than 20 activities in a week
    Then the employees "huba", "ilas" and "sdas" are available

  Scenario: An employee is sick
    Given that there is an employee "huba"
    When the employee has called in sick
    Then the employee is not available

  Scenario: An employee is on vacation
    Given that there is an employee "huba"
    When the employee is on vacation
    Then the employee is not available

