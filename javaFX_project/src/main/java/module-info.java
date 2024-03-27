module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens dtu.example.ui to javafx.fxml; // Gives access to fxml files
    exports dtu.example.ui;
    exports dtu.example.ui.pages;
    opens dtu.example.ui.pages to javafx.fxml; // Exports the class inheriting from javafx.application.Application
}