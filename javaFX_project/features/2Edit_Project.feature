Feature: Edit Project
  Description: Employee edits a project
  Actors: Employee

  Background:
    Given there is a project with name "New Project"
    Given there are employees with the following initials in the project
      | Initials |
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |

  Scenario: User edits a project successfully
    When the employee edits the project name to "Edited Project", the project leader to "Huba", and the project members to "Huba, Abed, Dora"
    Then the project name should be "Edited Project", the project leader should be "Huba", and the project members should be "Huba, Abed, Dora"

  Scenario: User edits a project with no name
    When the employee edits the project name to "", the project leader to "Huba", and the project members to "Huba, Abed, Dora, Jama"
    Then an error message "Name missing" should be given

  Scenario: User edits a project with no leader
    When the employee edits the project name to "Edited Project", the project leader to "", and the project members to "Huba, Abed, Dora, Jama"
    Then the project name should be "Edited Project", the project leader should be "", and the project members should be "Huba, Abed, Dora, Jama"

  Scenario: User edits a project with no members
    When the employee edits the project name to "Edited Project", the project leader to "Huba", and the project members to ""
    Then the project name should be "Edited Project", the project leader should be "", and the project members should be ""

  Scenario: User edits a project with a specific ID
    Given there are project with following details
      | ID       | Name       | Initials | ProjectLeader |
      | 24001    | Project 1  | Huba     | Huba          |
      | 24002    | Project 1  | Huba     | Huba          |
    When the employee edits the project with ID "24001"'s name to "Edited Project", the project leader to "Huba", and the project members to "Huba, Abed, Dora"
    Then the project with ID "24001"'s name should be "Edited Project", the project leader should be "Huba", and the project members should be "Huba, Abed, Dora"
    And the project with ID "24002"'s name should still be "Project 1"
