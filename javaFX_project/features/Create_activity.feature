Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

  Background:
    Given there exists a project with name "New Project"

  Scenario: Employee Creates an Activity
    When the company creates an activity with name "New Activity", and expected hours 100, scheduled from week 10 to week 13 with ID 24001
    Then the activity should be created successfully

  Scenario: Employee Tries to Create an Activity with Insufficient or Incorrect Information
    When the employee creates an activity with insufficient or incorrect information
    Then an error message "Can't create activity: Insufficient or incorrect information given" should be given