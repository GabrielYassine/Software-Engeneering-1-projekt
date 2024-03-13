Feature: Assign employee to project
  Description: Company adds an employee to an existing project
  Actors: Company

  Background: The company has some employees
    Given that the company is logged in
    And these employees work for the company
      | Huba |
      | Aha  |
      | Ekki |
      | Zara |
    And the Company logs out

  Scenario: Assign employee to project
    Given that the company is logged in
    And there is an existing project
    And there is an employee with initials "Huba"
    And the employee is accessible
    When the company assigns the employee "Huba" to the project
    Then the employee "Huba" should be successfully assigned to the project

  Scenario: Assign employee to non-existent project
    Given that the company is logged in
    And there is an employee with initials "Huba"
    And the employee is accessible
    When the company tries to assign the employee "Huba" to a non-existent project
    Then an error message "Project not found" should be displayed

  Scenario: Assign non-existent employee to project
    Given that the company is logged in
    And there is an existing project
    When the company tries to assign a non-existent employee to the project
    Then an error message "Employee not found" should be displayed

  Scenario: Assign inaccessible employee to project
    Given that the company is logged in
    And there is an existing project
    And there is an employee with initials "Huba"
    But the employee "Huba" is not accessible at the moment
    When the company tries to assign the employee "Huba" to the project
    Then an error message "Employee 'Huba' is not accessible at the moment" should be displayed

  Scenario: Assign employee to project when company is not logged in
    Given that the company is not logged in
    And there is an existing project
    And there is an employee with initials "Huba"
    And the employee is accessible
    When the company tries to assign the employee "Huba" to the project
    Then an error message "Company not logged in" should be displayed

  Scenario: Assign employee who is already part of the project
    Given that the company is logged in
    And there is an existing project
    And there is an employee with initials "Huba"
    And the employee "Huba" is already assigned to the project
    When the company tries to assign the employee "Huba" to the project again
    Then an error message "Employee 'Huba' is already assigned to the project" should be displayed