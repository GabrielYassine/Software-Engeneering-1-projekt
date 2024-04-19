Feature: See Employee Schedule
  Description: Employee searches for schedule of a specific employee(s) for a specific week.
  Actor: Employee


  Background:
    Given there are employees with the following initials
      | Initials |
      | Huba     |
      | Abed     |
    And there is a project with name "New Project"
    And there is an activity with name "New Activity" in the project, with the following details
      | Name        | Budget Hours | Start Week | End Week | Initials   |
      | New Activity| 100          | 5          | 8        | Huba, Abed |
    And the employee with initials "Huba" has registered "5" hours for the activity "New Activity" on the date "2024-02-10"

    Scenario: Employee searches for schedule of a specific employee for a specific week
      When the employee searches for the schedule of the employee(s) with initials "Huba" for the year 2024 and week 6
      Then the selected week for employee with initials "Huba" should contain the following details
        | Date      | Activity Name | Hours |
        | 2024-02-10| New Activity  | 5     |
    ##      And the day "Saturday" should contain the following details
    ##      | Activity Name | Hours |
    ##      | New Activity  | 5     |

    ##    Scenario: Employee searches for schedule of a specific employee for a week without any registered hours

    ##   Scenario: User searches for schedule of an employee with no initials

    ##  Scenario: User searches for schedule of an employee with wrong initials

    ##  Scenario: User searches for schedule of an employee with week over 52

    ##  Scenario: User searches for schedule of an employee with week 0

