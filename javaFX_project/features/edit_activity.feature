Feature: Edit Activity
  Description: Employee edits an activity
  Actors: Employee

  Background:
    Given there exists a project with name "New Project"
    And there exists an activity with name "New Activity", expected hours "100", scheduled from week "10" to week "13" in the project with ID 24001

  Scenario: Employee Edits an Activity Successfully
    When the employee edits the activity with name "New Activity", changing its name to "Updated Activity", expected hours to 120, and rescheduling from week 11 to week 14
    Then the activity "Updated Activity" should have expected hours as 120 and scheduled from week 11 to week 14
#
#  Scenario: Employee Tries to Edit an Activity with Insufficient or Incorrect Information
#    When the employee tries to edit the activity with name "New Activity", providing no name, expected hours as -10, and scheduling from week -1 to week 0
#    Then an error message "Can't edit activity: Insufficient or incorrect information given" should be given
#
#  Scenario: Employee Tries to Edit an Activity with a Name that Already Exists
#    Given there exists an activity with name "New Activity Two", expected hours "100", scheduled from week "10" to week "13" in the project with ID 24001
#    When the employee tries to edit the activity with name "New Activity", changing its name to "New Activity Two"
#    Then an error message "Activity with this name already exists in the project" should be given
#
#  Scenario: Employee Tries to Edit a Non-Existent Activity
#    When the employee tries to edit the activity with name "Non-Existent Activity", changing expected hours to 120, and rescheduling from week 11 to week 14
#    Then an error message "Activity with this name does not exist in the project" should be given