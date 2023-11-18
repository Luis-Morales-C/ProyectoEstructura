package estructura.infrastructure.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private Button b_process;

    @FXML
    private Button b_notifications;

    @FXML
    private Button b_export;

    @FXML
    private AnchorPane p_container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar la interfaz de usuario
        setupUI();
    }

    private void setupUI() {
        // Configurar el comportamiento de los botones u otros elementos si es necesario
        b_process.setOnAction(event -> handleProcessesButton());
        b_notifications.setOnAction(event -> handleNotificationsButton());
        b_export.setOnAction(event -> handleExportButton());


    }



    private void handleProcessesButton() {
        // Acción asociada al botón "Processes"
        System.out.println("Processes button clicked!");
    }

    private void handleNotificationsButton() {
        // Acción asociada al botón "Notifications"
        System.out.println("Notifications button clicked!");
    }

    private void handleExportButton() {
        // Acción asociada al botón "Export"
        System.out.println("Export button clicked!");
    }
}
