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
      When the employee searches for the schedule of the employee with initials "Huba" for the year 2024 and week 6
      Then the selected week for employee with initials "Huba" should contain the following details
        | Date       | Activity Name | Hours |
        | 2024-02-10 | New Activity  | 5     |
      And the weekday 7 for the employee with initials "Huba" should contain the following details
      | Activity Name | Hours |
      | New Activity  | 5     |

    Scenario: Employee searches for schedule of a specific employee for a week without any registered hours
        When the employee searches for the schedule of the employee with initials "Huba" for the year 2024 and week 7
        Then the selected week for employee with initials "Huba" should contain the following details
        | Date       | Activity Name | Hours |
        | | | |
        And the weekday 7 for the employee with initials "Huba" should contain the following details
        | Activity Name | Hours |
        | | |

    Scenario: User searches for schedule of an employee with no initials
        When the employee searches for the schedule of the employee with initials "" for the year 2024 and week 7
        Then an error message "Initials cannot be empty" should be given

    Scenario: User searches for schedule of an employee with wrong initials
        When the employee searches for the schedule of the employee with initials "ABCD" for the year 2024 and week 7
        Then an error message "Employee with those initials not found" should be given

    Scenario: User searches for schedule of an employee with week over 52
        When the employee searches for the schedule of the employee with initials "Huba" for the year 2024 and week 53
        Then an error message "Week value error" should be given

    Scenario: User searches for schedule of an employee with week 0
        When the employee searches for the schedule of the employee with initials "Huba" for the year 2024 and week 0
        Then an error message "Week value error" should be given

  ##Scenario: User searches for schedule of an employee with no week
  ##  When the employee searches for the schedule of the employee with initials "Huba" for the year 2024 and week ""
  ##  Then an error message "No week is given" should be given

 ## Scenario: User searches for schedule of an employee with no year
 ##   When the employee searches for the schedule of the employee with initials "Huba" for the year "" and week 7
  ##  Then an error message "No year is given" should be given