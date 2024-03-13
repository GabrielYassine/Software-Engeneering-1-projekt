Feature: Show Project Status

  Scenario: Compare Planned vs Actual Time for Activities
    Given a project with planned activities
    When comparing planned vs actual time for each activity
    Then the system should identify discrepancies for adjustment

  Scenario: Analyze Time Spent on Activities
    Given a project with recorded time for activities
    When analyzing the time spent on individual activities
    Then the system should provide insights into time distribution

  Scenario: Highlight Discrepancies for Adjustment
    Given a project with planned vs actual time discrepancies
    When identifying areas needing adjustment
    Then the system should highlight those for further action
