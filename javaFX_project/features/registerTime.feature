Feature: Register Time

	Scenario: Daily Time Registration
		Given an employee wants to register time
		When the employee submits daily timesheets
		Then the system should record the time spent on each activity

	Scenario: Report Activities
		Given an employee wants to report activities like vacation or sick days
		When the employee submits the start and end dates for such activities
		Then the system should update the employee's record accordingly

	Scenario: Record Time for Specific Activities
		Given an employee needs to record time for activities
		When the employee registers hours for specific project activities
		Then the system should accurately track time spent on those activities

	Scenario: Record Time for Unassigned Activities
		Given an employee is asked to help on activities not directly assigned
		When the employee registers time for such activities
		Then the system should allow recording of time without direct assignment
