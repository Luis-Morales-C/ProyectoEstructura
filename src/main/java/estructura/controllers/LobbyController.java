package estructura.controllers;

import estructura.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LobbyController {

    private Stage stage;
    private Main mainApp;

    @FXML
    private Label etiqueta;

    @FXML
    private Button salir;

    @FXML
    private Button registro;

    @FXML
    private PasswordField tfContrasena;

    @FXML
    private TextField tfUsuario;

    @FXML
    void eventoBoton(ActionEvent event) throws Exception {
        String usuario = tfUsuario.getText();
        String contrasena = tfContrasena.getText();

        boolean valido = verificarUsuarioLobby(usuario, contrasena);
        if (valido) {
            // Si el usuario es válido, carga el otro FXML
            mainApp.mostrarLobby();
        } else {
            tfContrasena.setText("");
            tfUsuario.setText("");
            System.out.println("Usuario o contraseña incorrecto");
            tfContrasena.setText("");
            showAlert("ERROR", "Usuario o contraseña incorrecto");
        }
    }

    private boolean verificarUsuarioLobby(String usuario, String contrasena) {
        // Lógica de verificación de usuario (puedes ajustar esto según tus necesidades)
        String usuarioCorrecto = "usuario";
        String contrasenaCorrecta = "contrasena";

        return usuario.equals(usuarioCorrecto) && contrasena.equals(contrasenaCorrecta);
    }

    @FXML
    void eventoSalir(ActionEvent event) throws Exception {
        showAlert("Cerrar sesion", "Uso terminado");
        ocultar(event);
    }

    public void ocultar(ActionEvent even) throws Exception {
        this.stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest((event) -> {
            showAlert("Cerrar sesion", "Gracias por usar este programa");
            System.out.println("Fin");
        });
    }

    // Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }


    public void setMain(Main main) {
        // Puedes realizar acciones de inicialización o configuración aquí si es necesario
        System.out.println("Referencia a Main establecida en LobbyController");
    }

    public void setP(Main main) {


    }
}

