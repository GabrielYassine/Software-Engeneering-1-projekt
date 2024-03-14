Feature: Assign Employee to Project
  Description: The company adds an employee to an existing project
  Actors: Company

  Scenario: Assign Employee to Project
    Given the company is logged in
    And there is an existing project
    And there is an employee with initials "Huba" who is accessible
    When the company assigns the employee "Huba" to the project
    Then the employee "Huba" should be successfully assigned to the project

  Scenario: Assign Employee to Project when Company is Not Logged In
    Given the company is not logged in
    When the company tries to assign an employee to a project
    Then the system should prompt the company to log in

  Scenario: Assign Multiple Employees to Project
    Given the company is logged in
    And there is an existing project
    And there are employees with initials "Huba", "Aha", and "Ekki" who are all available
    When the company assigns employees "Huba", "Aha", and "Ekki" to the project
    Then all employees should be successfully assigned to the project

  Scenario: Assign Employee to Project with Incorrect ID
    Given the company is logged in
    And there is an existing project
    When the company tries to assign an employee with an incorrect ID to the project
    Then an error message should be displayed indicating the invalid employee ID

  Scenario: Assign an Employee with Incorrect Initials to a Project
    Given the company is logged in
    And there is an existing project
    When the company tries to assign an employee with incorrect initials to the project
    Then an error message should be displayed indicating the incorrect initials of the employee

  Scenario: Assign Employee Who is Already Part of the Project
    Given the company is logged in
    And there is an existing project
    And the employee "Huba" is already assigned to the project
    When the company tries to assign the employee "Huba" to the project again
    Then an error message should be displayed indicating that the employee is already part of the project
