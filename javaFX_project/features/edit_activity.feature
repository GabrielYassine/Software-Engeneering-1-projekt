#Feature: Edit Activity
#  Description: Employee edits an activity
#  Actors: Employee
#
#Scenario: Employee edits an activity
#  Given a project exists in the system
#  And there exists an activity in that project
#  When the employee edits the activity
#  Then the activity is edited
#
#Scenario: Employee edits an activity with wrong datatype
#  Given a project exists in the system
#  And there exists an activity in that project
#  And the employee tries to edit the activity with a wrong datatype
#  When the employee edits the activity
#  Then the error message "Can't edit activity: Wrong data given" is given
#
#Scenario: Employee changes name of activity to an already existing one
#  Given a project exists in the system
#  And there exists an activity in that project
#  And the employee tries to edit an activity's name to one already in use
#  When the employee edits the activity
#  Then the error message "Can't edit activity: Activity name already in use" is given
#
#Scenario: Employee edits an activity with empty variables
#  Given a project exist in the system
#  And there exists an activity in that project
#  And the employee tries to edit an activity with no new name, beginning, end or expected hours given
#  When the employee edits the activity
#  Then the attribute of the activity is not edited