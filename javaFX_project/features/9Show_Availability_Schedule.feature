Feature: show available employees
  Description: the user can view available employees
  Actor: employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    When the user creates an activity with the following details
      | Name          | Budget Hours | Start Week | End Week | Start Year | End Year | Initials   |
      | New Activity  | 100          | 7          | 10       | 2024       | 2024     | Huba, Jama |
      | New Activity2 | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |

    Scenario: User successfully views availability calendar
      When the user searches for the availability schedule for year "2024" and month "2" for the employee with initials "Huba"
      Then the system shows the following availability calendar for the specified month
        | First Week | Second Week | Third Week | Fourth Week | Fifth Week |
        | 1          | 1           | 2          | 2           | 1          |

    Scenario: User writes no month
      When the user searches for the availability schedule for year "2024" and month "" for the employee with initials "Huba"
      Then an error message "No month given" should be given

  Scenario: User writes month 0
    When the user searches for the availability schedule for year "2024" and month "0" for the employee with initials "Huba"
    Then an error message "Month value out of bounds" should be given

  Scenario: User writes month above 12
    When the user searches for the availability schedule for year "2025" and month "13" for the employee with initials "Huba"
    Then an error message "Month value out of bounds" should be given

    Scenario: User checks for year 0
     When the user searches for the availability schedule for year "" and month "february" for the employee with initials "Huba"
     Then an error message "No year given" should be given

    Scenario: User writes no year
      When the user searches for the availability schedule for year "" and month "february" for the employee with initials "Huba"
      Then an error message "No year given" should be given


  Scenario: User successfully views availability calendar, but no activities active
    When the user searches for the availability schedule for year "2025" and month "2" for the employee with initials "Huba"
    Then the system shows the following availability calendar for the specified month
      | First Week | Second Week | Third Week | Fourth Week | Fifth Week |
      | 0          | 0           | 0          | 0           | 0          |