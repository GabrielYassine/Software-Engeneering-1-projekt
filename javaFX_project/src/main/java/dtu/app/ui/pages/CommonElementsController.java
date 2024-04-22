package dtu.app.ui.pages;

import dtu.app.ui.classes.Employee;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CommonElementsController {

    public Button backButton;

    public void goBack() throws IOException {
        if (!App.navigationHistory.isEmpty()) {
            App.navigationHistory.pop();
            if (!App.navigationHistory.isEmpty()) {
                String previousPage = App.navigationHistory.peek();
                App.scene.setRoot(App.loadFXML(previousPage));
            }
        }
    }

    public void addEmployee(ListView<Employee> employeesListView, ListView<Employee> selectedEmployeesListView) {
        Employee selectedEmployee = employeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeesListView.getItems().remove(selectedEmployee);
            selectedEmployeesListView.getItems().add(selectedEmployee);
        }
    }

    public void removeEmployee(ListView<Employee> employeesListView, ListView<Employee> selectedEmployeesListView) {
        Employee selectedEmployee = selectedEmployeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployeesListView.getItems().remove(selectedEmployee);
            employeesListView.getItems().add(selectedEmployee);
        }
    }

    public void resetActivityCreationFields(TextField activityNameField, TextField budgetHoursField, TextField startWeekField, TextField endWeekField, ListView<Employee> employeesListView, ListView<Employee> selectedEmployeesListView, TextField initialsField, TextField hoursField, DatePicker datePicker) {
        List<TextField> textFields = Arrays.asList(activityNameField, budgetHoursField, startWeekField, endWeekField, initialsField, hoursField);
        List<ListView<Employee>> listViews = Arrays.asList(employeesListView, selectedEmployeesListView);

        textFields.forEach(textField -> {
            if (textField != null) {
                textField.clear();
            }
        });

        listViews.forEach(listView -> {
            if (listView != null) {
                listView.getItems().clear();
            }
        });

        if (employeesListView != null && App.database != null) {
            employeesListView.getItems().addAll(App.database.getEmployees());
        }

        if (datePicker != null) {
            datePicker.setValue(null);
        }
    }

    public void setupNumericTextFieldListeners(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*\\.?\\d?") || newValue.length() > 4) {
                    textField.setText(oldValue);
                }
            });
        }
    }

    public void setupLetterTextFieldListeners(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z]*") || newValue.length() > 4) {
                    textField.setText(oldValue);
                }
            });
        }
    }
    public <T> void setRowClickAction(TableView<T> tableView, Consumer<T> action) {
        tableView.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    T clickedRow = row.getItem();
                    action.accept(clickedRow);
                }
            });
            return row;
        });
    }
}