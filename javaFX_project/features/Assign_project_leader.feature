#Feature: Assign project leader
#  Description: This use case involves displaying the completion status of a project
#  to provide insight into its progress and current status.
#  Actor: Employee
#
#  Scenario: Assign project leader to a project
#    Given a project exists in the project management system
#    And An employee selects the project to assign a leader to
#    When The employee chooses an employee to be the project leader
#    And the employee confirms the selection
#    Then the project is updated with the assigned project leader
#    And the system notifies the employee of the successful assignment
#
#  Scenario: No project leaders available to assign
#    Given a project exists in the project management system
#    And there are no available project leaders to assign
#    When the employee attempts to assign a leader to the project
#    Then the system notifies the employee that there are no project leaders available
#
#  Scenario: Change of project leader
#    Given a project exists in the project management system
#    And a project leader is already assigned to the project
#    When the employee attempts to assign the new leader to the same project
#    Then the system replaces the current project leader with the new one
#
#  Scenario: Attempt to assign leader to non-existent project
#    Given a project does not exist in the project management system
#    And the employee attempts to assign a project leader
#    Then the system notifies the employee that the project does not exist
