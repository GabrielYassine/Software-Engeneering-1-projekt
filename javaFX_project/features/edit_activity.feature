Feature: Edit Activity
  Description: Employee edits an activity
  Actors: Employee

  Background:
    Given there exists a project with name "New Project"
    And there exists an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "13" in the project with ID 24001

  Scenario: Employee Edits an Activity Successfully
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "11" to week "14"
    Then the activity "Updated Activity" should have expected hours as 120 and scheduled from week 11 to week 14

  Scenario: Employee edits an activity with no name
    When the employee edits the activity with name "New Activity", changing its name to "", expected hours to "120", and rescheduling from week "11" to week "14"
    Then an error message "Activity name cannot be empty" should be given

  Scenario: The user edits an activity with negative expected hours
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "-120", and rescheduling from week "11" to week "14"
    Then an error message "Budget hours cannot be negative" should be given

  Scenario: The user edits an activity with empty expected hours
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "", and rescheduling from week "11" to week "14"
    Then an error message "Budget hours cannot be empty" should be given

  Scenario: The user edits an activity with empty start week
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "" to week "14"
    Then an error message "Start week value error" should be given

  Scenario: The user edits an activity with empty end week
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "11" to week ""
    Then an error message "End week value error" should be given

  Scenario: the user edits an activity with negative start week
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "-11" to week "14"
    Then an error message "Start week value error" should be given

  Scenario: the user edits an activity with negative end week
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "11" to week "-14"
    Then an error message "End week value error" should be given

  Scenario: The user edits an Activity with Start Week Over 52
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "53" to week "14"
    Then an error message "Start week value error" should be given

  Scenario: The user edits an Activity with End Week Over 52
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to "120", and rescheduling from week "11" to week "53"
    Then an error message "End week value error" should be given




