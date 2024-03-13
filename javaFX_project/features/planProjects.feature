Feature: Plan Projects

  Scenario: Assign Project Leader
    Given a project is created
    When a project leader is assigned
    Then the project should have a designated project leader

  Scenario: Define Project Activities
    Given a project is created
    When project activities are defined with start and end weeks
    Then the project should have a list of activities with timeframes

  Scenario: Refine Project Plans
    Given a project is created with initial plans
    When the project plans are refined gradually
    Then the project should reflect updated activity details
