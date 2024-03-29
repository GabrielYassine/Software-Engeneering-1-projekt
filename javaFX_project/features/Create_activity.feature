Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

  Background:
    Given there exists a project with name "New Project"

  Scenario: Employee Creates an Activity
    When the company creates an activity with name "New Activity", and expected hours "100", scheduled from week "10" to week "13" with ID 24001
    Then the activity should be created successfully

  Scenario: Employee Tries to Create an Activity with No Name
    When the company tries to create an activity with name "", expected hours "100", scheduled from week "10" to week "13" with ID 24001
    Then an error message "Activity name cannot be empty" should be given

  Scenario: Employee Tries to Create an Activity with a Name that Already Exists
    Given there exists an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "13" in the project with ID 24001
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "13" with ID 24001
    Then an error message "Activity with this name already exists in the project" should be given

  Scenario: Employee Tries to Create an Activity with Negative Expected Hours
    When the company tries to create an activity with name "New Activity", expected hours "-100", scheduled from week "10" to week "13" with ID 24001
    Then an error message "Budget hours cannot be negative" should be given

  Scenario: Employee Tries to Create an Activity with Empty Budget Hours
    When the company tries to create an activity with name "New Activity", expected hours "", scheduled from week "10" to week "13" with ID 24001
    Then an error message "Budget hours value error" should be given

  Scenario: Employee Tries to Create an Activity with Empty Start Week
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "" to week "13" with ID 24001
    Then an error message "Start week value error" should be given

  Scenario: Employee Tries to Create an Activity with Empty End Week
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "" with ID 24001
    Then an error message "End week value error" should be given

  Scenario: Employee Tries to Create an Activity with Start Week Over 52
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "53" to week "13" with ID 24001
    Then an error message "Start week value error" should be given

  Scenario: Employee Tries to Create an Activity with End Week Over 52
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "53" with ID 24001
    Then an error message "End week value error" should be given

  Scenario: Employee Tries to Create an Activity with Negative Start Week
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "-1" to week "13" with ID 24001
    Then an error message "Start week value error" should be given

  Scenario: Employee Tries to Create an Activity with Negative End Week
    When the company tries to create an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "-1" with ID 24001
    Then an error message "End week value error" should be given