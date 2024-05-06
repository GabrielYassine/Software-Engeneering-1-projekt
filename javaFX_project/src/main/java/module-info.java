module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens dtu.app.ui to javafx.fxml;
    exports dtu.app.ui;
    exports dtu.app.ui.info;
    exports dtu.app.ui.pages;
    opens dtu.app.ui.pages to javafx.fxml;
    exports dtu.app.ui.domain;
    opens dtu.app.ui.domain to javafx.fxml;
    exports dtu.app.ui.database;
    opens dtu.app.ui.database to javafx.fxml;
}