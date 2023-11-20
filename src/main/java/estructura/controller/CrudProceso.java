package estructura.controller;

import estructura.MainApp;
import estructura.model.Actividad;
import estructura.model.Proceso;
import estructura.model.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrudProceso {

    private MainApp aplicacion;

    @FXML
    private TableColumn<?, ?> colIdProceso;

    @FXML
    private TableColumn<?, ?> colNombreProceso;

    @FXML
    private TableColumn<?, ?> colNumActividades;

    @FXML
    private Tab tabProcesos;

    @FXML
    private TableView<?> tblProcesos;

    @FXML
    private TextField txtNombreProceso;

    private ObservableList<Proceso> listaProcesos = FXCollections.observableArrayList();
    private ObservableList<Actividad> listaActividad = FXCollections.observableArrayList();
    private ObservableList<Tarea> listaTarea = FXCollections.observableArrayList();

    @FXML
    void onBuscarClick(ActionEvent event) {

    }

    @FXML
    void onCambiarNombreClick(ActionEvent event) {

    }

    @FXML
    void onCambioNombreClick(ActionEvent event) {

    }

    @FXML
    void onCancelarClick(ActionEvent event) {

    }

    @FXML
    void onConsultarTiempoPClick(ActionEvent event) {

    }

    @FXML
    void onRegistrarClick(ActionEvent event) {

    }

    @FXML
    void onRemoverClick(ActionEvent event) {

    }


    public MainApp getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(MainApp aplicacion) {
        this.aplicacion = aplicacion;
    }
}
