package dtu.app.ui.pages;

import dtu.app.ui.classes.Email;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmailController {
    @FXML
    public TableView<Email> emailTableView;
    @FXML
    private TableColumn<Email, String> contentsColumn;
    @FXML
    private TableColumn<Email, String> subjectColumn;

    @FXML
    private void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        contentsColumn.setCellValueFactory(new PropertyValueFactory<>("Contents"));
    }
}
