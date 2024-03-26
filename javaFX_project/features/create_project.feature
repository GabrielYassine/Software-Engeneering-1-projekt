Feature: Create Project
  Description: The user creates an instance of the project class
  Actors: Employee

  Scenario: Create a project
    Given there is an employee with initials "Huba"
    When the company creates a project with the employee "Huba" and the project name "New Project"
    Then the project should be created successfully

  Scenario: Create a project without an employee with incorrect initials
    Given there is an employee with initials "Huba"
    When the company creates a project with the employee "ABCD" and the project name "New Project"
    Then the project should be created successfully
    And the project does not have employee "ABCD" assigned
    And an error message "Employee with initials 'ABCD' not found" should be given

  Scenario: Create a project without providing a name
    Given there is an employee with initials "Huba"
    When the company creates a project with the employee "Huba" and no name
    Then an error message "No project name given" should be given

