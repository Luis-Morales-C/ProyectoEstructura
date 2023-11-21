module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports estructura.model;
    exports estructura.controllers;
    exports estructura; // Exporta el paquete estructura

    opens estructura.model to javafx.base;
    opens estructura.controllers to javafx.fxml;
    opens estructura to javafx.fxml;
}
