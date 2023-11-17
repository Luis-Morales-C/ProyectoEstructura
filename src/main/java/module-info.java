module estructura.proyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens estructura to javafx.fxml;
    exports estructura;
}