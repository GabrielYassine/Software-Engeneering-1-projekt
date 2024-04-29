package dtu.app.ui.pages;

import dtu.app.ui.domain.Employee;
import dtu.app.ui.info.EmployeeInfo;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CommonElementsController {

    public Button backButton;


    // This method is used to set the action of the back button:
    public void goBack() throws IOException {
        if (!App.navigationHistory.isEmpty()) {
            App.navigationHistory.pop();
            if (!App.navigationHistory.isEmpty()) {
                String previousPage = App.navigationHistory.peek();
                App.scene.setRoot(App.loadFXML(previousPage));
            }
        }
    }

    // This method is used to add an employee to a ListView:

    public void addEmployee(ListView<EmployeeInfo> employeesListView, ListView<EmployeeInfo> selectedEmployeesListView) {
        EmployeeInfo selectedEmployee = employeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeesListView.getItems().remove(selectedEmployee);
            selectedEmployeesListView.getItems().add(selectedEmployee);
        }
    }

    // This method is used to remove an employee from a ListView:

    public void removeEmployee(ListView<EmployeeInfo> employeesListView, ListView<EmployeeInfo> selectedEmployeesListView) {
        EmployeeInfo selectedEmployee = selectedEmployeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployeesListView.getItems().remove(selectedEmployee);
            employeesListView.getItems().add(selectedEmployee);
        }
    }

   // This method is used to limit the input of a TextField to only numbers and of a certain length:

    public void setupNumericTextFieldListeners(int length, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*\\.?\\d?") || newValue.length() > length) {
                    textField.setText(oldValue);
                }
            });
        }
    }

    // This method is used to limit the input of a TextField to only letters and of a certain length:
    public void setupLetterTextFieldListeners(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z]*") || newValue.length() > 10) {
                    textField.setText(oldValue);
                }
            });
        }
    }

    public void setupDoubleTextFieldListeners(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?") || newValue.chars().filter(ch -> ch == '.').count() > 1) {
                    textField.setText(oldValue);
                }
            });
        }
    }

    // This method is used to set the action of double-clicking on a row in a TableView:
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

    public void clearFields(TextField[] textFields, DatePicker[] datePickers, ListView<?>[] listViews, TableView<?>[] tableViews) {
        for (TextField textField : textFields) {
            textField.clear();
        }
        for (DatePicker datePicker : datePickers) {
            datePicker.setValue(null);
        }
        for (ListView<?> listView : listViews) {
            listView.getItems().clear();
        }
        for (TableView<?> tableView : tableViews) {
            tableView.getItems().clear();
        }
    }
}