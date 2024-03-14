Feature: Create Activity
  Description: Company or employee creates an activity
  Actors: Company, employee

Scenario: Create an activity
  Given a project exist in the system
  And there is an activity with name "Testing", beginning "25-03-2024", end "05-04-2024", expected hours "100"
  When the company or employee creates the activity
  Then the activity is created


Scenario: Create an activity without enough data
  Given a project exist in the system
  And there is an activity without every detail specified
  When the company or employee creates the activity
  Then the activity is created without the details specified


Scenario: Cant create an activity with wrong data
  Given a project exist in the system
  And there is an activity with wrong data
  When the company or employee creates the activity
  Then the error message "Can't create activity: wrong data given"