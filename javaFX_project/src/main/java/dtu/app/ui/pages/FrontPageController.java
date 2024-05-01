package dtu.app.ui.pages;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FrontPageController {

    public Button primaryButton;

    @FXML
    private void switchToSecondary() throws Exception {
        App.application.initializeTestData();
        App.setRoot("menu");
    }
}