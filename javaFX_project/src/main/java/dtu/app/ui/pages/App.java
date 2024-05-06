package dtu.app.ui.pages;

import dtu.app.ui.ProjectApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * JavaFX App
 */

public class App extends Application {

    public static ProjectApp application;
    public static Scene scene;
    public static final Stack<String> navigationHistory = new Stack<>();
    @Override
    public void start(Stage stage) throws Exception {
        App.application = new ProjectApp();
        App.application.initializeTestData();
        scene = new Scene(loadFXML("menu"), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        navigationHistory.push(fxml);
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dtu/app/ui/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}