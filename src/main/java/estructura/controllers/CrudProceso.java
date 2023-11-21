package estructura.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CrudProceso {

    @FXML
    private TableView<Proceso> tblProcesos;

    @FXML
    private TableColumn<Proceso, Integer> colIdProceso;

    @FXML
    private TableColumn<Proceso, String> colNombreProceso;

    @FXML
    private TableColumn<Proceso, Integer> colNumActividades;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblCrearProceso;

    @FXML
    private TextField txtNombreProceso;

    @FXML
    private Button btnCambiarNombre;

    @FXML
    private TextField txtNuevoNombre;

    @FXML
    private Button btnConsultarTiempo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelar;

    @FXML
    private void onRegistrarClick(ActionEvent event) {
        // Lógica para registrar un nuevo proceso
    }

    @FXML
    private void onCambiarNombreClick(ActionEvent event) {
        // Lógica para cambiar el nombre de un proceso
    }

    @FXML
    private void onConsultarTiempoPClick(ActionEvent event) {
        // Lógica para consultar el tiempo de un proceso
    }

    @FXML
    private void onRemoverClick(ActionEvent event) {
        // Lógica para eliminar un proceso
    }

    @FXML
    private void onBuscarClick(ActionEvent event) {
        // Lógica para buscar un proceso
    }

    @FXML
    private void onCancelarClick(ActionEvent event) {
        // Lógica para cancelar la operación actual
    }

    public void onCambioNombreClick(ActionEvent actionEvent) {
    }

    // Puedes agregar más métodos y atributos según sea necesario

    // Clase de modelo para representar un proceso
    public static class Proceso {
        private final Integer idProceso;
        private final String nombreProceso;
        private final Integer numActividades;

        public Proceso(Integer idProceso, String nombreProceso, Integer numActividades) {
            this.idProceso = idProceso;
            this.nombreProceso = nombreProceso;
            this.numActividades = numActividades;
        }

        // Puedes agregar getters según sea necesario
    }
}
