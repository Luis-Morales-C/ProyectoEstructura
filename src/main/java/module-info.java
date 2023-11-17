module estructura.proyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens estructura.proyecto to javafx.fxml;
    exports estructura.proyecto;
}