package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivityHelper {
    private final ProjectApp application;

    public EmployeeActivityHelper(ProjectApp application) {
        this.application = application;
    }

    public void simulateActiveActivities(EmployeeInfo employee, int numberOfActivities) throws Exception {
        ProjectInfo project = application.getSelectedProject();
        List<EmployeeInfo> e = new ArrayList<>();
        e.add(employee);
        for (int i = 0; i < numberOfActivities; i++) {
            Activity a = application.createActivity(project, "Activity" + i, "10", "1", "52", e,"2021", "2099");
        }
    }
}