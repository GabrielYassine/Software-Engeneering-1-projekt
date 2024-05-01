Feature: Includes steps for GUI methods
  Description: Employee does or views various elements on the GUI
  Actor: Employee


  Scenario: Employee views all the employees in the app.
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |
    Then the employee should see the following employees in the app
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |


  Scenario: Employee views all the projects in the app.
    Given there are employees with the following initials
      | Huba     |
      | Dora     |
    Given there are projects with following details
      | Name     | Initials   | ProjectLeader |
      | Proj     | Huba, Dora | Huba          |
      | Project  | Huba       | Huba          |
    Then the size of the projects should be 2


  Scenario: Employee views all activities in a project
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activit | 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the employee should see the following activities in the selected project
      | New Activity |
      | New Activit  |

  Scenario: Employee views all employees in a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    Then the employee should see the following employees in the project
      | Huba     |
      | Abed     |

  Scenario: Employee views all employees in an activity
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the employee should see the following employees in the activity
      | Huba     |
      | Abed     |

  Scenario: Employee views all dates for a selected year and week
    When the user searches for the year "2024" and week "5" they should see the following dates
      | 2024-01-29 |
      | 2024-01-30 |
      | 2024-01-31 |
      | 2024-02-01 |
      | 2024-02-02 |
      | 2024-02-03 |
      | 2024-02-04 |

  Scenario: Employee views the activityLog of an employee for a specific day
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then the employee should see the details for the weekday "tuesday" in week "22" of year "2021" for the employee "Huba"
      | ActivityName  | Hours  | Date       |
      | New Activity  | 10     | 2021-06-01 |

  Scenario: Employee views the completionStatus of an activity
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    When the employee with initials "Huba" registers "10" hours on the activity "New Activity" on the date "2021-06-01"
    Then the activity completion status should be "Not completed"


  Scenario: Employee views the size of the employees list in an activity
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the size of the employees list in the activity should be 2

  Scenario: Employee gets status of an activity
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the status of the activity should be "0.0/100.0"

  Scenario: Employee views the completionStatus of a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    When the user creates an activity with the following details
      | Name          | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity 2| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    When the user switching completion state of the activity with name "New Activity"
    Then the projectInfo completion status should be "1/2"

  Scenario: Employee views the size of the activity list in a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the size of the activity list in the project should be 1

  Scenario: Employee views the size of the employees list in a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    Then the size of the employees list in the project should be 2

  Scenario: Employee views all activities in a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    When the user creates an activity with the following details
      | Name        | Budget Hours | Start Week | End Week | Start Year | End Year | Initials  |
      | New Activity| 100          | 5          | 8        | 2024       | 2024     |Huba, Abed |
    Then the employee should see the following activities in the project
      | New Activity |

  Scenario: Employee views all the employees in a project
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Given there is a project with name "New Project"
    And there are employees with the following initials in the project
      | Huba     |
      | Abed     |
    Then the employee should see this employee(s) in the project
      | Huba     |
      | Abed     |

  Scenario: employee views the initials of an employee
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
    Then the employee should see the following initials
      | Huba     |
      | Abed     |


