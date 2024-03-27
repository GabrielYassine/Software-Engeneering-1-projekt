Feature: Create Project
  Description: The user wants to create a new project
  Actors: Employee

  Background:
    Given there is an employee with initials "Huba"

  Scenario: Create a project
    When the company creates a project with the employee "Huba" and the project name "New Project"
    Then the project should be created successfully

  Scenario: Create a project without an employee with incorrect initials
    When the company creates a project with the employee "ABCD" and the project name "New Project"
    Then the project should be created successfully
    And an error message "Employee with initials 'ABCD' not found" should be given

  Scenario: Create a project without providing a name
    When the company creates a project with the employee "Huba" and no name
    Then an error message "No project name given" should be given