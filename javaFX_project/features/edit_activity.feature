Feature: Edit Activity
  Description: Company or employee edits an activity
  Actors: Company, employee

Scenario: Edit an activity
  Given there is an activity with name "Testing", beginning "25-03-2024", end "05-04-2024", expected hours "100"
  When the company or employee edits the activity
  Then the activity is edited

Scenario: