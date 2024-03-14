Feature: Assign project leader
  Description: This use case involves displaying the completion status of a project
  to provide insight into its progress and current status.
  Actor: Employee

  Scenario: Assign project leader to a project
    Given a project exists in the project management system
    And the employee has the necessary permissions to assign a project leader
    When the employee selects the project to assign a leader to
    And the employee chooses a staff member to be the project leader
    And the employee confirms the selection
    Then the project is updated with the assigned project leader
    And the system notifies the employee of the successful assignment

  Scenario: No project leaders available to assign
    Given a project exists in the project management system
    And there are no available project leaders to assign
    When the employee attempts to assign a leader to the project
    Then the system notifies the employee that there are no project leaders available

  Scenario: Attempt to assign leader to non-existent project
    Given the employee attempts to assign a project leader
    And the project does not exist in the project management system
    Then the system notifies the employee that the project does not exist
    And prompts the employee to create the project before assigning a leader

  Scenario: Project leader already assigned
    Given a project exists in the project management system
    And a project leader is already assigned to the project
    When the employee attempts to assign a leader to the same project
    Then the system replaces the current project leader with the new one

  Scenario: Project leader assignment canceled
    Given the employee is in the process of assigning a project leader
    When the employee decides to cancel the assignment
    Then the system cancels the assignment process
    And returns the employee to the previous state without making any changes