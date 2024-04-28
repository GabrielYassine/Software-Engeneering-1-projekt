Feature: Create Project
  Description: The user wants to create a new project
  Actors: Employee

  Background:
    Given there are employees with the following initials
      | Huba     |
      | Abed     |
      | Dora     |
      | Jama     |

  Scenario: User creates a project successfully
    When the user creates a project with the following details
      | Name       | Initials | ProjectLeader |
      | Project 1  | Huba     | Huba          |
    Then the project should be created
    And the project should have the following details
      | ID  | Name       | Initials | ProjectLeader |
      | 24001 | Project 1  | Huba   | Huba          |

  Scenario: User creates a project with no project leader
    When the user creates a project with the following details
      | Name       | Initials | ProjectLeader |
      | Project 1  | Huba     |               |
    Then the project should be created
    And the project should have the following details
      | ID  | Name       | Initials | ProjectLeader |
      | 24001 | Project 1  | Huba   |               |

  Scenario: User creates a project with no employees
    When the user creates a project with the following details
      | Name       | Initials | ProjectLeader |
      | Project 1  |          |               |
    Then the project should be created
    And the project should have the following details
      | ID    | Name       | Initials | ProjectLeader |
      | 24001 | Project 1  |          |               |

  Scenario: User creates a project with multiple employees
    When the user creates a project with the following details
      | Name       | Initials               | ProjectLeader |
      | Project 1  | Huba, Abed, Dora, Jama | Huba          |
    Then the project should be created
    And the project should have the following details
      | ID    | Name       | Initials               | ProjectLeader |
      | 24001 | Project 1  | Huba, Abed, Dora, Jama | Huba          |

  Scenario: User creates a project with no name
    When the user creates a project with the following details
      | Name       | Initials | ProjectLeader |
      |            |          |               |
    Then the project should not be created
    Then an error message "Name missing" should be given