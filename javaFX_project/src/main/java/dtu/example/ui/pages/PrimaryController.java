package dtu.example.ui.pages;

import java.io.IOException;

import dtu.example.ui.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    public Button primaryButton;
    @FXML
    private TextField employeeNameField;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}