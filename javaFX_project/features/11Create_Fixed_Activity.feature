Feature: Create Fixed Activity
  Description: Employee creates a fixed activity
  Actors: Employee

  Background:
    Given there are employees with the following initials
      | Huba     |

  Scenario: User creates a fixed activity
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 8        | 2024       | 2024     |
    Then the employee should have a fixed activity with the following details
      | Name     | Start Week | End Week | Start Year | End Year |
      | Holiday  | 5          | 8        | 2024       | 2024     |

  Scenario: User creates a fixed activity with no name
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      |              | 5          | 8        | 2024       | 2024     |
    Then an error message "Name missing" should be given

  Scenario: User creates a fixed activity with no start week
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      |            | 8        | 2024       | 2024     |
    Then an error message "No week given" should be given

  Scenario: User creates a fixed activity with start week above 52
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 53         | 8        | 2024       | 2024     |
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates a fixed activity with start week 0
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 0          | 8        | 2024       | 2024     |
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates a fixed activity with no end week
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          |          | 2024       | 2024     |
    Then an error message "No week given" should be given

  Scenario: User creates a fixed activity with end week above 52
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 53       | 2024       | 2024     |
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates a fixed activity with end week 0
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 0        | 2024       | 2024     |
    Then an error message "Week value out of bounds" should be given

  Scenario: User creates a fixed activity with no start year
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 8        |            | 2024     |
    Then an error message "No year given" should be given

  Scenario: User creates a fixed activity with no end year
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 8        | 2024       |          |
    Then an error message "No year given" should be given

  Scenario: User creates a fixed activity with end year before start year
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 8        | 2025       | 2024     |
    Then an error message "Start year is after end year" should be given

  Scenario: User creates a fixed activity with end week before start week
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
    | Name         | Start Week | End Week | Start Year | End Year |
    | Holiday      | 8          | 5        | 2024       | 2024     |
    Then an error message "Start week is after end week" should be given

  Scenario: Employee tries to access the fixed activities of another employee with no initials
    When the employee creates a fixed activity with the following details
      | Name         | Start Week | End Week | Start Year | End Year |
      | Holiday      | 5          | 8        | 2025       | 2024     |
    Then an error message "Employee not selected" should be given



