Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

Scenario: Employee creates an activity
  Given a project exist in the system
  And there is an activity with name "Testing", beginning "25-03-2024", end "05-04-2024", expected hours 100
  And no activity exists with that name
  When the employee creates the activity
  Then the activity is created
  
Scenario: Employees tries to create an activity without filling information
  Given a project exists in the system
  And there is an activity without a name, beginning, end or expected hours
  When the employee creates the activity
  Then the error message "Can't create activity: not enough information given" is given

Scenario: Employee tries to create an activity with wrong datatype
  Given a project exists in the system
  And there is an activity with wrong datatype
  When the employee creates the activity
  Then the error message "Can't create activity: Wrong data given" is given