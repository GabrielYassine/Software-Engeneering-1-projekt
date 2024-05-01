Feature: send reminder
  Description: the system sends an employee an important reminder
  Actor: system

  Scenario: Employee receives a reminder for unregistered daily work
    Given there are employees with the following initials
      | Huba |
    And the employee "Huba" has not registered his daily work for the current day
    And 1 day has passed
    When the employee "Huba" is not doing a fixed activity
    Then the system sends a reminder email to employee "Huba"
    And the email with subject "Work" and text "Register your daily work" is in the employees "Huba" inbox

  Scenario: Employee doesn't receive a reminder for unregistered daily work
    Given there are employees with the following initials
      | Huba |
    And the employee "Huba" has not registered his daily work for the current day
    And 1 day has passed
    When the employee selects the employee with initials "Huba" to view their fixed activities
    When the employee creates a fixed activity with the following details
      | Name    | Start Week | End Week | Start Year | End Year |
      | Holiday | 3          | 32       | 2024       | 2024     |
    Then the system does not send a reminder email to "Huba"
    And the email with subject "Work" and text "Register your daily work" is not in the employees "Huba" inbox