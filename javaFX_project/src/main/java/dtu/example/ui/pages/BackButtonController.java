package dtu.example.ui.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class BackButtonController {

    @FXML
    private Button backButton;

    @FXML
    private void goBack() throws IOException {
        App.goBack();
    }
}