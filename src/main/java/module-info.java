module atlas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens atlas to javafx.fxml;

    exports atlas;
}