Feature: Assign Employee to Project
  Description: The company adds an employee to an existing project
  Actors: Employee

  Scenario: Assign Employee to Project
    Given there exists a project with name "New Project"
    And there is an employee with initials "Huba"
    When the company assigns the employee "Huba" to the project with ID 24001
    Then the employee "Huba" should be successfully assigned to the project

  Scenario: Assign Employee to Project with Incorrect ID
    Given there exists a project with name "New Project"
    And there is an employee with initials "Huba"
    When the company assigns the employee "Huba" to the project with ID 24002
    Then an error message "Project with ID '24002' not found" should be given

  Scenario: Assign an Employee with Incorrect Initials to a Project
    Given there exists a project with name "New Project"
    And there is an employee with initials "Huba"
    When the company assigns the employee "ABCD" to the project with ID 24001
    Then an error message "Employee with initials 'ABCD' not found" should be given

  Scenario: Assign Employee Who is Already Part of the Project
    Given there exists a project with name "New Project"
    And there is an employee with initials "Huba" assigned to the project with ID 24001
    When the company assigns the employee "Huba" to the project with ID 24001
    Then an error message "Employee already assigned to project" should be given
