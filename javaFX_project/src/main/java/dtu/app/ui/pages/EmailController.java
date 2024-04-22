package dtu.app.ui.pages;

import dtu.app.ui.classes.Email;
import dtu.app.ui.classes.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EmailController {
    @FXML
    public TableView<Email> emailTableView;
    @FXML
    private ComboBox<Employee> employeesComboBox;
    @FXML
    private TableColumn<Email, String> textColumn;
    @FXML
    private TableColumn<Email, String> subjectColumn;

    @FXML
    private void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        employeesComboBox.getItems().addAll(App.database.getEmployees());
        EventHandler<ActionEvent> event = actionEvent -> viewEmail(employeesComboBox.getValue().getInitials());
        employeesComboBox.setOnAction(event);
    }

    @FXML
    public void viewEmail(String initials) {
        try {
            emailTableView.getItems().clear();
            Employee employee = App.database.getEmployee(initials);
            List<Email> inbox = employee.getInbox();
            emailTableView.getItems().addAll(inbox);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
