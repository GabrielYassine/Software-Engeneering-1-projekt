Feature: show available employees
  Description: the user can view available employees within a period of time
  Actor: employee

  Scenario: Assigning an Activity to Available Employees
    Given there is a project "Project X" with an activity to be assigned
    When user searches for available employees for the activity in "Project X"
    Then user should see a list of suitable employees
    And the number of assigned activities for each employee should not exceed 10

  Scenario: No Available Employees for the Activity
    Given there is a project "Project X" with an activity to be assigned
    And all employees are currently occupied with 10 activities each
    When user searches for available employees for the activity in "Project A"
    Then user should receive a notification that no available employees are found
    And user should be prompted to adjust the workload or hire additional staff

  Scenario: Assigning Activity to Employee with Least Activities
    Given there is a project "Project B" with an activity to be assigned
    And there are multiple available employees with varying numbers of assigned activities
    When I search for available employees for the activity in "Project B"
    Then I should see a list of suitable employees
    And the employee with the least number of assigned activities should be prioritized
    And the activity should be assigned to the employee with the least number of assigned activities
