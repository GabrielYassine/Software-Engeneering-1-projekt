Feature: Edit Project
  Description: Employee edits a project
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |

  Scenario: User edits a project successfully
    When the employee edits the projects name to "Edited Project", the project leader to "Huba", and the project members to "Huba, Abed, Dora"
    Then the projects name should be "Edited Project", the project leader should be "Huba", and the project members should be "Huba, Abed, Dora"

  Scenario: User edits a project with no name
    When the employee edits the project with name to "", the project leader to "Huba", and the project members to "Huba, Abed, Dora, Jama"
    Then an error message "Name missing" should be given

  Scenario: User edits a project with no leader
    When the employee edits the projects name to "Edited Project", the project leader to "None", and the project members to "Huba, Abed, Dora, Jama"
    Then the projects name should be "Edited Project", the project leader should be "", and the project members should be "Huba, Abed, Dora, Jama"

  Scenario: User edits a project, removing employees that are assigned activities
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials              |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed, Dora, Jama |
    When the employee edits the projects name to "Edited Project", the project leader to "Huba", and the project members to "Huba, Abed"
    Then the activity with name "New Activity" should have the following details
      | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |

  Scenario: User edits a project by removing all employees but not putting project leader to None.
    When the employee edits the projects name to "Edited Project", the project leader to "Huba", and the project members to ""
    Then the projects name should be "Edited Project", the project leader should be "", and the project members should be ""