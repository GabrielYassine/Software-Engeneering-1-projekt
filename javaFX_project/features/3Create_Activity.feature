Feature: Create Activity
  Description: Employee creates an activity
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Initials |
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |

  Scenario: User creates an activity
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 5          | 8        | Huba, Abed, Dora, Jama |
    Then the activity should be created
    And the activity should have the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 5          | 8        | Huba, Abed, Dora, Jama |

  Scenario: User creates an activity with no name
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    |             | 100          | 5          | 8        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Name missing" should be given

  Scenario: User creates an activity with no budget hours
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity|              | 5          | 8        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Budget hours missing" should be given

  Scenario: User creates an activity with no start week
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          |            | 8        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Start week value error" should be given

  Scenario: User creates an activity with Start week above 52
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 53         | 8        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Start week value error" should be given

  Scenario: User creates an activity with start week 0
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 0          | 8        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "Start week value error" should be given

  Scenario: User creates an activity with no end week
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 5          |          | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "End week value error" should be given

  Scenario: User creates an activity with End week above 52
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 5          | 53       | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "End week value error" should be given

  Scenario: User creates an activity with end week 0
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials               |
    | New Activity| 100          | 5          | 0        | Huba, Abed, Dora, Jama |
    Then the activity should not be created
    Then an error message "End week value error" should be given

  Scenario: User creates an activity with no initials
    When the user creates an activity with the following details
    | Name        | Budget Hours | Start Week | End Week | Initials |
    | New Activity| 100          | 5          | 8        |          |
    Then the activity should be created
    And the activity should have the following details
    | Name        | Budget Hours | Start Week | End Week | Initials |
    | New Activity| 100          | 5          | 8        |          |

