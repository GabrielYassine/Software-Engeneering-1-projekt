Feature: Create Project
  Description: The user creates an instance of the project class
  Actors: Company

  Scenario: Create a project
    Given the company is logged in
    And there is an employee with initials "Huba"
    When the company creates a project with the employee "Huba"
    Then the project should be created successfully
    And the project number should be assigned by the system in the format "YYNNN"
    And the project should have a name

  Scenario: Create a project when company is not logged in
    Given the company is not logged in
    When the company tries to create a project
    Then an error message "Company not logged in" should be displayed

  Scenario: Create a project without an employee with incorrect initials
    Given the company is logged in
    And there exists an employee with initials "ABC"
    When the company tries to create a project with incorrect employee initials "XYZ"
    Then an error message "Employee initials 'XYZ' not found" should be displayed
    And the project should be created without including the employee with initials "XYZ"
    
  Scenario: Create a project without providing a name
    Given the company is logged in
    And there is an employee with initials "Huba"
    And the employee "Huba" is available
    When the company tries to create a project with the employee "Huba" without providing a projectname
    Then an error message "No project name given" should be displayed

  Scenario: Create a project with multiple employees
    Given the company is logged in
    And there are employees with initials "Huba", "Aha", and "Ekki"
    When the company creates a project with employees "Huba", "Aha", and "Ekki"
    Then the project should be created successfully
    And the project should be assigned a project number
    And the project should have a name
    And the project should be associated with employees "Huba", "Aha", and "Ekki"
