package estructura.infrastructure;

import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar la interfaz de usuario desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/main.fxml"));
        // Crear la escena utilizando el diseño cargado desde FXML
        Scene scene = new Scene(fxmlLoader.load());


        // Configurar la escena en el escenario (ventana principal)
        stage.setScene(scene);

        // Mostrar la ventana principal
        stage.show();
    }

    public static void main(String[] args) {
        // Lanzar la aplicación
        launch();
    }
}
