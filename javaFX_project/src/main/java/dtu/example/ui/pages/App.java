package dtu.example.ui.pages;

import dtu.example.ui.Database;
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

    private static Scene scene;
    public static Database database;
    private static final Stack<String> navigationHistory = new Stack<>();
    @Override
    public void start(Stage stage) throws IOException {
        database = new Database();
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        navigationHistory.push(fxml);
        scene.setRoot(loadFXML(fxml));
    }

    public static void goBack() throws IOException {
        String currentPage = navigationHistory.peek();
        if (!navigationHistory.isEmpty()) {
            navigationHistory.pop();
            if (!navigationHistory.isEmpty()) {
                String previousPage = navigationHistory.peek();
                if (previousPage.equals("createActivity") || previousPage.equals("createProject")) {
                    navigationHistory.pop();
                    if (!navigationHistory.isEmpty()) {
                        previousPage = navigationHistory.peek();
                        if (previousPage.equals(currentPage)) {
                            navigationHistory.pop();
                            previousPage = navigationHistory.peek();
                        }
                    }
                }
                scene.setRoot(loadFXML(previousPage));
            }
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dtu/example/ui/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}