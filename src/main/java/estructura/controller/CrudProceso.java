package estructura.controller;

import estructura.MainApp;
import estructura.model.Actividad;
import estructura.model.ListaDobleEnlazada;
import estructura.model.Proceso;
import estructura.model.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Collection;
import java.util.List;

public class CrudProceso {

    private MainApp aplicacion;

    private ModelFactory modelFactory;

    private Proceso procesoSeleccionado;

    private Actividad actividadSeleccionada;

    private Tarea tareaSeleccionada;

    @FXML
    private TableColumn<?, ?> colIdProceso;

    @FXML
    private TableColumn<?, ?> colNombreProceso;

    @FXML
    private TableColumn<?, ?> colNumActividades;

    @FXML
    private Tab tabProcesos;

    @FXML
    private TableView<Proceso> tblProcesos;

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
        String id= String.valueOf(Proceso.generarID());
        String nombre= txtNombreProceso.getText();
        int numActividades=0;


        if (nombre.isEmpty() ) {
            mostrarMensaje("Por favor escriba el nombre");
            return;
        }
        //Crear Proceso

        Proceso nuevoProceso=null;
        Proceso proceso=new Proceso();

        proceso.setId(id);
        proceso.setNombre(nombre);
        proceso.setNumActividades(numActividades);

        nuevoProceso=modelFactory.crearProceso(proceso);

        if (nuevoProceso != null) {
            // Agregar el proceso a la lista
            listaProcesos.add(nuevoProceso);

            // Actualizar la tabla
            cargarProcesosEnTabla();

            // Limpiar los campos de texto

            limpiarCamposProceso();
            // Mostrar un mensaje de éxito
            mostrarMensaje("Proceso registrado correctamente.");
        } else {
            mostrarMensaje("Proceso ya existe");
        }
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
    @FXML
    public void initialize() {
        modelFactory = ModelFactory.getInstance();
        //inicializar la tabla de procesos
        colIdProceso.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNumActividades.setCellValueFactory(new PropertyValueFactory<>("numActividades"));

        //inicializar la tabla de actividades


        //configurar el evento de seleccion de la tabla
        // Configurar el evento de selección de la tabla
        tblProcesos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = (Proceso) newSelection;
            }
        });

        cargarProcesosEnTabla();


    }
        // Cargar procesos en la tabla
        public void cargarProcesosEnTabla() {
            if (modelFactory != null && modelFactory.getProceso() != null) {
                colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colIdProceso.setCellValueFactory(new PropertyValueFactory<>("id"));
                colNumActividades.setCellValueFactory(new PropertyValueFactory<>("numActividades"));

                // Asignar los datos a la tabla
                tblProcesos.getItems().clear();

                List<Proceso> listaProcesos = getListaProcesos();
                if (listaProcesos != null) {
                    tblProcesos.setItems((ObservableList<Proceso>) listaProcesos);
                } else {
                    // Manejo de caso en que getListaProcesos() devuelve nulo
                }

                tblProcesos.refresh();
            } else {
                // Manejo de caso en que modelFactory o getProceso() es nulo
            }
        }

    public ObservableList<Proceso> getListaProcesos() {
        ListaDobleEnlazada<Proceso> listaProcesos = modelFactory.getProceso().getListaProcesos();
        if (listaProcesos != null) {
            this.listaProcesos.addAll(listaProcesos.aLista());
        }
        return this.listaProcesos;
    }


    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    void limpiarCamposProceso() {
         txtNombreProceso.clear();
    }


}
