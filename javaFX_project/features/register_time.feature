Feature: Register time on activity
  Description: Record the amount of time spent on an activity.
  Actor: Employee

#  Background: The app has a project, and a couple of employees and an activity
#    Given there exists a project with name "New Project"
#    And the following employees are assigned to the project with ID 24001
#      | initials |
#      | Huba     |
#      | Abcd     |
#      | Efgh     |
#      | Hijk     |
#    And there exists an activity with the following details
#      | 24001 | New Activity | 100         | 1         | 4      |

#  Scenario: The user registers the time spent on the activity
#    When the employee "Huba" registers 5 hours on the activity "New Activity"
#    Then the total registered time for the activity "New Activity" should be 5 hours

#  Scenario: No input is provided
#    When the employee "Huba" tries to register time on the activity "New Activity" without providing any input
#    Then an error message "No input provided" should be given