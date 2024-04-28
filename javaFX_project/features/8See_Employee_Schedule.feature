Feature: See Employee Schedule
  Description: Employee searches for schedule of a specific employee(s) for a specific week.
  Actor: Employee


  Background:
    Given there are employees with the following initials
      | Initials |
      | Huba     |
      | Abed     |
    And there is a project with name "New Project"
    When the user creates an activity with the following details
      | Name          | Budget Hours | Start Week | End Week | Start Year | End Year | Initials   |
      | New Activity  | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed |
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date "2024-04-24"

  Scenario: Employee searches for schedule of a specific employee for a week with registered hours
    When the user searches for the schedule of the employee with initials "Huba" for the year "2024" and week "17"
    Then the following details about the specified week should be displayed
      | Date       | Activity Name | Hours |
      | 2024-04-24 | New Activity  | 10.0  |

    Scenario: Employee searches for schedule of a specific employee for a week without any registered hours
      When the user searches for the schedule of the employee with initials "Huba" for the year "2024" and week "8"
      Then the following details about the specified week should be displayed
        | Date       | Activity Name | Hours |
        |            |               |       |

    Scenario: User searches for schedule of an employee with no initials
      When the user searches for the schedule of the employee with initials "" for the year "2024" and week "17"
      Then an error message "Name missing" should be given

    Scenario: User searches for schedule of an employee with wrong initials
      When the user searches for the schedule of the employee with initials "ABCD" for the year "2024" and week "17"
      Then an error message "Employee with those initials not found" should be given

    Scenario: User searches for schedule of an employee with week over 52
      When the user searches for the schedule of the employee with initials "Huba" for the year "2024" and week "53"
      Then an error message "Week value out of bounds" should be given

    Scenario: User searches for schedule of an employee with week 0
      When the user searches for the schedule of the employee with initials "Huba" for the year "2024" and week "0"
      Then an error message "Week value out of bounds" should be given

    Scenario: User searches for schedule of an employee with no week
      When the user searches for the schedule of the employee with initials "Huba" for the year "2024" and week ""
      Then an error message "No week given" should be given

   Scenario: User searches for schedule of an employee with no year
     When the user searches for the schedule of the employee with initials "Huba" for the year "" and week "7"
      Then an error message "No year given" should be given

  Scenario: User searches for schedule of an employee for year 0
    When the user searches for the schedule of the employee with initials "Huba" for the year "0" and week "7"
      Then an error message "Year value out of bounds" should be given