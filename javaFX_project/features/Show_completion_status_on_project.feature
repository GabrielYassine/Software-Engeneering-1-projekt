Feature: Show completion status on project
  Description: This use case involves displaying the completion status of a project
  to provide insight into its progress and current status.
  Actor: Employee

  Scenario: Display project completion status
    Given the employee is logged into the project management system
    And the employee has access to view project details
    When the employee navigates to the section for viewing project information
    And selects the specific project they want to check the completion status for
    Then the system retrieves and displays the completion status of the selected project

  Scenario: Project completion status not available
    Given the employee is logged into the project management system
    And the employee has access to view project details
    And the completion status for the selected project is not available
    When the employee navigates to view the completion status of the project
    Then the system notifies the employee that the completion status is not available

  Scenario: View completion status for multiple projects
    Given the employee is logged into the project management system
    And the employee has access to view project details
    And there are multiple projects assigned to the employee
    When the employee navigates to view project completion status
    Then the system displays a list of all projects assigned to the employee
    And for each project, the system presents the completion status
    And the employee can review the completion status of each project to gain
        insights into their progress

  Scenario: No projects available to display completion status
    Given the employee is logged into the project management system
    And there are no projects assigned or available for the employee to view
    When the employee attempts to view project completion status
    Then the system notifies the employee that there are no projects available

  Scenario: Incomplete data for project completion status
    Given the employee is logged into the project management system
    And the employee has access to view project details
    And the completion status for the selected project is incomplete or unavailable
    When the employee navigates to view the completion status of the project
    Then the system notifies the employee that the completion status data is incomplete