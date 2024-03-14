Feature: See registered time
  Description:
  Company can see the registered time.
  Actor:
  Company, Employee

  Scenario: Employee has registered the time spent on the activity
    Given Employee has registered the time spent on the activity
    When Company checks the amount of time spent on the activity
    Then Company can see how much time has been spent on the activity

  Scenario Outline: Employee has not entered the time
    Given Employee has not entered the time
    When Company checks the employee's registration status
    Then Company can see that the employee has not registered

    Examples:
      | Scenario  | Employee has not entered the time |
      |           |                                   |


Feature: Register time on activity
  Description: Record the amount of time spent on an activity.
  Actor: Employee, Company

  Scenario: The user registers the time spent on the activity
    Given The user registers the time spent on the activity
    When Company checks the amount of time spent on the activity
    Then Company can see how much time has been spent on the activity

  Scenario Outline: No input is provided
    Given No input is provided
    When Employee submits the time without entering any input
    Then Employee does not get credited for their hours

    Examples:
      | Scenario  | No input is provided        |
      |           |                             |
