Feature: Create Project
    Description: Company creates an instance of the project
    Actors: Company

  Background: The company has some employees
    Given that the company is logged in
    And these employees work for the company
      | Huba |
      | Aha  |
      | Ekki |
      | Zara |
    And the Company logs out

  Scenario: Create a project
    Given that the company is logged in
    And there is an employee with initials "Huba"
    And the employee is accessible
    When the company creates a project with the employee "Huba"
    Then the project should be created successfully
    And the project number should be assigned by the system in the format "YYNNN" where YY is the last two digits of the year and NNN is a running number
    And the project should have a name

  Scenario: Create a project when company is not logged in
    Given that the company is not logged in
    When the company tries to create a project
    Then an error message "Company not logged in" is given

  Scenario: Create a project with a non-existent employee
    Given that the company is logged in
    When the company tries to create a project with an employee "NonExistent"
    Then an error message "Employee 'NonExistent' not found" should be displayed

  Scenario: Create a project with an inaccessible employee
    Given that the company is logged in
    And there is an employee with initials "Huba"
    But the employee "Huba" is not accessible at the moment
    When the company tries to create a project with the employee "Huba"
    Then an error message "Employee 'Huba' is not accessible at the moment" should be displayed

  Scenario: Create a project with multiple employees
    Given that the company is logged in
    And there are employees with initials "Huba", "Aha", and "Ekki"
    And all employees are accessible
    When the company creates a project with employees "Huba", "Aha", and "Ekki"
    Then the project should be created successfully
    And the project should be assigned a project number
    And the project should have a name
    And the project should be associated with employees "Huba", "Aha", and "Ekki"