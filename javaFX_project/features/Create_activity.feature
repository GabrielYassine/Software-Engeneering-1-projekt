Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

Scenario: Employee creates an activity
  Given there exists a project with name "New Project"
  When the company creates an activity with name "New Activity", and expected hours 100, scheduled from week 10 to week 13 with ID 24001
  Then the activity should be created successfully

#Scenario: Employees tries to create an activity without filling information
#  Given a project exists in the system
#  And there is an activity without a name, beginning, end or expected hours
#  When the employee creates the activity
#  Then the error message "Can't create activity: not enough information given" is given

#Scenario: Employee tries to create an activity with wrong datatype
#  Given a project exists in the system
#  And there is an activity with wrong datatype
#  When the employee creates the activity
#  Then the error message "Can't create activity: Wrong data given" is given