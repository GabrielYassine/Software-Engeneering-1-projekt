Feature: show available employees
  Description: the user can view available employees
  Actor: employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Initials |
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    And there is an activity with name "New Activity" in the project, with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      | New Activity| 100          | 6          | 11       | Abed, Dora, Jama |
    And there is an activity with name "New Activity" in the project, with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials               |
      | New Activity| 100          | 5          | 7        | Huba, Abed, Dora, Jama |

    Scenario: User successfully views availability calendar
      Given the employee with initials "Huba" is selected
      When you search after year "2024" and the month "february"
      Then the system shows the following availability calendar for the month "february" in the year 2024
        | Initials | First week | Second week | Third week | Fourth week | Fifth week |
        | Huba     | 1          | 1           | 1          | 0           | 0          |
        | Abed     | 1          | 2           | 2          | 1           | 1          |
        | Dora     | 1          | 2           | 2          | 1           | 1          |
        | Jama     | 1          | 2           | 2          | 1           | 1          |

    Scenario: User writes an invalid month
        Given the employee with initials "Huba" is selected
        When you search after year "2024" and the month "februa"
        Then an error message "Month value error" should be given

     Scenario: User checks for year 0
        Given the employee with initials "Huba" is selected
        When you search after year "0" and the month "february"
        Then an error message "Start year value error" should be given

    Scenario: User writes no month
        Given the employee with initials "Huba" is selected
        When you search after year "2024" and the month ""
        Then an error message "Month value error" should be given

    Scenario: User writes no year
       Given the employee with initials "Huba" is selected
       When you search after year "" and the month "february"
       Then an error message "Year value error" should be given