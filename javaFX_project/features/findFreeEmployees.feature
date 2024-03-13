Feature: Find Free Employees

  Scenario: Identify Employees with Low Activity Commitments
    Given the system needs to find available employees
    When searching for employees with low activity commitments
    Then the system should provide a sorted list of available employees

  Scenario: Exclude Employees on Leave
    Given the system needs to find available employees
    When searching for employees during a specific activity period
    Then the system should exclude employees on sick leave or vacation

  Scenario: Display Sorted List of Available Employees
    Given the system finds available employees
    When displaying the list of available employees
    Then the system should present them in a sorted order
