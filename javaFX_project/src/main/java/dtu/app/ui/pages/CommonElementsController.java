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

    public void goBack() throws IOException {
        if (!App.navigationHistory.isEmpty()) {
            App.navigationHistory.pop();
            if (!App.navigationHistory.isEmpty()) {
                String previousPage = App.navigationHistory.peek();
                App.scene.setRoot(App.loadFXML(previousPage));
            }
        }
    }

    public void addEmployee(ListView<EmployeeInfo> employeesListView, ListView<EmployeeInfo> selectedEmployeesListView) {
        EmployeeInfo selectedEmployee = employeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeesListView.getItems().remove(selectedEmployee);
            selectedEmployeesListView.getItems().add(selectedEmployee);
        }
    }

    public void removeEmployee(ListView<EmployeeInfo> employeesListView, ListView<EmployeeInfo> selectedEmployeesListView) {
        EmployeeInfo selectedEmployee = selectedEmployeesListView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployeesListView.getItems().remove(selectedEmployee);
            employeesListView.getItems().add(selectedEmployee);
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
                if (!newValue.matches("[a-zA-Z]*") || newValue.length() > 10) {
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