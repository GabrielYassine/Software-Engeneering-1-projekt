Feature: Assign project leader
  Description: This use case involves displaying the completion status of a project
  to provide insight into its progress and current status.
  Actor: Employee

  Background: The app has a project, and a couple of employees
    Given there exists a project with name "New Project"
    And these employees are assigned to a project with ID 24001
      | Huba |
      | Abcd |
      | Efgh |
      | Hijk |

  Scenario: Assign project leader to a project
    When an employee assigns the employee "Huba" to be project leader of the project with ID 24001
    Then the employee "Huba" should be successfully appointed as the project leader for the project with ID 24001

  Scenario: Change of project leader
    When an employee assigns the employee "Abcd" to be project leader of the project with ID 24001
    And an employee assigns the employee "Huba" to be project leader of the project with ID 24001
    Then the employee "Huba" should be successfully appointed as the project leader for the project with ID 24001

  Scenario: Attempt to assign leader to non-existent project
    And an employee assigns the employee "Huba" to be project leader of the project with ID 24002
    Then an error message "Project with ID '24002' not found" should be given