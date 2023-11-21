package estructura;

import estructura.controllers.LobbyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Muestra el Lobby primero
        mostrarLobby();

        // Luego muestra el Registro después de un tiempo (por ejemplo, 5 segundos)
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        mostrarRegistro();
                    }
                },
                5000  // 5 segundos de espera
        );
    }

    public void mostrarRegistro() {
        try {
            String fxmlPath = "/view/Inicio.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarLobby() {
        try {
            String fxmlPath = "/view/Lobby.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/estructura/controllers/LobbyController.java");
            primaryStage.show();

            LobbyController lobbyController = loader.getController();
            lobbyController.setStage(primaryStage);
            lobbyController.setP(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
