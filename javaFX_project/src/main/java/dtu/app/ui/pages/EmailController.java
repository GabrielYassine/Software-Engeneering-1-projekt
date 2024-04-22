package dtu.app.ui.pages;

import dtu.app.ui.classes.Email;
import dtu.app.ui.classes.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmailController {
    @FXML
    public TableView<Email> emailTableView;
    @FXML
    private ComboBox<Employee> employeesComboBox;
    @FXML
    private TableColumn<Email, String> contentsColumn;
    @FXML
    private TableColumn<Email, String> subjectColumn;

    @FXML
    private void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        contentsColumn.setCellValueFactory(new PropertyValueFactory<>("Contents"));
        
        employeesComboBox.getItems().addAll(App.database.getEmployees());
    }
}
