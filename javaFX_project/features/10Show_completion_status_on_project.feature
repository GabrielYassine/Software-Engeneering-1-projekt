Feature: Show completion status on project
  Description: This use case involves displaying the completion status of a project
  to provide insight into its progress and current status.
  Actor: Employee

Scenario: Employee shows a projects completion status
  Given there is a project with name "New Project"
  When the user checks the completion status
  Then the user gets the number of activities completed and the total number of activities