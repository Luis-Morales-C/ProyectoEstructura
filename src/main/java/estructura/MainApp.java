package estructura;

import estructura.controller.CrudProceso;
import estructura.controller.InicioController;
import estructura.model.Proceso;
import estructura.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    public Stage primaryStage;
    @Override
    public void start(Stage Stage) {
        mostrarRegistro();
    }
    public void mostrarRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();


            CrudProceso controller = loader.getController();
            controller.setAplicacion(this);

            Scene scene = new Scene(rootLayout);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Portal Procesos");
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
