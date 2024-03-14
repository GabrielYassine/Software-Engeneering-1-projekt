Feature: Edit Activity
  Description: Employee edits an activity
  Actors: Employee

Scenario: Employee edits an activity
  Given a project exists in the system
  And there is an activity with name "Testing", beginning "25-03-2024", end "05-04-2024", expected hours "100"
  When the employee edits the activity
  Then the activity is edited

Scenario: Employee edits an activity with wrong datatype
  Given a project exists in the system
  And there exists an activity in that project
  And the employee tries to edit with wrong datatype
  When the employee edits the activity
  Then the error message "Can't edit activity: Wrong data given" is given

Scenario: Employee changes name of activity to an already existing one
  Given a project exists in the system
  And there exists an activity in that project
  When the employee edits an activity's name to one already in use
  Then the error message "Can't edit activity: Activity name already in use" is given