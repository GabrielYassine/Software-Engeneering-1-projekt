Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
      | Rosa     |

  Scenario: User creates an activity
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
    | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should be created
    And the activity with name "New Activity" should have the following details
    | Budget Hours | Start Week | End Week | Start Year | End Year | Initials               |
    | 100          | 5          | 8        | 2024       | 2024     | Huba, Abed, Dora, Jama |

    Scenario: User creates an activity with no employees
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials |
    | New Activity| 100          | 5          | 8        | 2024       | 2024     |          |
    Then the activity should be created
    And the activity with name "New Activity" should have the following details
    | Budget Hours | Start Week | End Week | Start Year | End Year | Initials |
    | 100          | 5          | 8        | 2024       | 2024     |          |

  Scenario: User creates an activity with no name
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    |             | 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Name missing" should be given

  Scenario: User creates an activity with no budget hours
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity|              | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Hours missing" should be given

  Scenario: User creates an activity with no start week
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          |            | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "No week given" should be given

  Scenario: User creates an activity with Start week above 52
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 53         | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates an activity with start week 0
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 0          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates an activity with no end week
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 5          |          | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "No week given" should be given

  Scenario: User creates an activity with End week above 52
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 5          | 53       | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates an activity with end week 0
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 5          | 0        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates an activity with no initials
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials |
    | New Activity| 100          | 5          | 8        | 2024       | 2024     |         |
    Then the activity should be created
    And the activity with name "New Activity" should have the following details
    | Budget Hours | Start Week | End Week | Start Year | End Year | Initials |
    | 100          | 5          | 8        | 2024       | 2024     |          |

  Scenario: User creates an employee that is already in 20 active activities
    When the employee with initials "Rosa" is working on too many activities
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials   |
    | New Activity| 100          | 5          | 8        | 2024       | 2024     |Rosa       |
    And an error message "Employee is already working on 20 activities this week" should be given

  Scenario: Employee creates an activity with startWeek after endWeek
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
    | New Activity| 100          | 8          | 5        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Start week is after end week" should be given

  Scenario: Employee creates an activity with startYear after endYear
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year |Initials               |
      | New Activity| 100          | 5          | 8        | 2024       | 2023     |Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Start year is after end year" should be given