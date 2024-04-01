Feature: Edit Project
  Description: Employee edits a project
  Actors: Employee

  Background:
    Given there exists a project with name "New Project"
    And these employees are assigned to a project with ID 24001
      | Huba |
      | Abcd |
      | Efgh |
      | Hijk |

  Scenario: Employee Edits a Project Successfully
#    When the employee edits the project with name "New Project" to "New Project 2"
#    When the employee edits the Project with name "New Project", changing its name to "Updated Project", its project leader to "Huba" and its members to "Huba" and "Abcd"
#    Then the project with name "Updated Project" exists

#  Scenario: Employee edits a project with no name


