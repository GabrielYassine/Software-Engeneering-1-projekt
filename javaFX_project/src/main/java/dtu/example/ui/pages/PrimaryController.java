package dtu.example.ui.pages;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    public Button primaryButton;
    public Button initializeTestRunButton;
    @FXML
    private TextField employeeNameField;

    @FXML
    private void switchToSecondary() throws IOException {
        App.database.initializeTestRun();
        App.setRoot("secondary");
    }
}