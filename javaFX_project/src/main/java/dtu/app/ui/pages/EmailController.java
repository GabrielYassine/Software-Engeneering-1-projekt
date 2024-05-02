package dtu.app.ui.pages;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Email;
import dtu.app.ui.info.EmployeeInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class EmailController {
    @FXML
    public TableView<Email> emailTableView;
    @FXML
    private ComboBox<EmployeeInfo> employeesComboBox;
    @FXML
    private TableColumn<Email, String> textColumn;
    @FXML
    private TableColumn<Email, String> subjectColumn;
    @FXML
    private TableColumn<Email, String> dateColumn;

    @FXML
    private void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("Text"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        employeesComboBox.getItems().addAll(App.application.getEmployeesInApp());
        employeesComboBox.setOnAction(actionEvent -> viewEmail(employeesComboBox.getValue()));
    }

    @FXML
    private void viewEmail(EmployeeInfo employee) {
        emailTableView.getItems().clear();
        emailTableView.getItems().addAll(App.application.getEmployeeInbox(employee));
    }
}
