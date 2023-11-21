package estructura.controller;

import estructura.MainApp;
import estructura.model.Actividad;
<<<<<<< HEAD
import estructura.model.Estado;
=======
>>>>>>> origin/master
import estructura.model.Proceso;
import estructura.model.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CrudProceso {

    private MainApp app;

    private ModelFactory modelFactory;

    private Proceso procesoSeleccionado;

    private Actividad actividadSeleccionada;

    private Tarea tareaSeleccionada;


    //Items Proceso
=======
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrudProceso {

    private MainApp aplicacion;

>>>>>>> origin/master
    @FXML
    private TableColumn<?, ?> colIdProceso;

    @FXML
    private TableColumn<?, ?> colNombreProceso;

    @FXML
    private TableColumn<?, ?> colNumActividades;

    @FXML
    private Tab tabProcesos;

    @FXML
<<<<<<< HEAD
    private TableView<Proceso> tblProcesos;

    @FXML
    private TextField txtNombreProceso;
    @FXML
    private TextField txtAux;

    //items actividades
    @FXML
    private SplitMenuButton SplitCrear;

    @FXML
    private TableColumn<?, ?> colProcesoTarea;
    @FXML
    private TableView<Proceso> tblProcesosTareas;
    @FXML
    private MenuButton crearTarea;

    @FXML
    private CheckBox checkObligatorio;

    @FXML
    private TextField txtPosicionTarea;
    @FXML
    private TextField txtDescripcionTarea;
    @FXML
    private TextField txtDuracionTarea;
    @FXML
    private CheckBox checkBoxTarea;
    @FXML
    private TableColumn<?, ?> colNombreActividad;

    @FXML
    private TableColumn<?, ?> colDescripcionActividad;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colNombreProceso2;

    @FXML
    private TableView<Actividad> tblActividades;

    @FXML
    private TableView<Proceso> tblProcesos2;

    @FXML
    private TableColumn<?, ?> colDescripcionTarea;
    @FXML
    private TableColumn<?, ?> colDuracionTarea;
    @FXML
    private TableColumn<?, ?> colObligatorioTarea;
    @FXML
    private TableView<Tarea> tblTareas;

    @FXML
    private TableColumn<?, ?> colNombreActividadTarea;
    @FXML
    private TableColumn<?, ?> colDescripcionActividadTarea;
    @FXML
    private TableColumn<?, ?> colEstadoActividadTarea;
    @FXML
    private TableView<Actividad> tblActividadesTareas;

    @FXML
    private TextField txtNombreActividad;

    @FXML
    private TextField txtDescripcionActividad;
    @FXML
    MenuItem crearUltimoActividad;
    @FXML
    MenuItem crearDespuesActividad;
    @FXML
    MenuItem crearOrdenActividad;

    private ObservableList<Proceso> listaProcesos = FXCollections.observableArrayList();
    private ObservableList<Proceso> listaProcesosAct = FXCollections.observableArrayList();
    private ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
    private ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

    @FXML
    void onCrearActividad(ActionEvent event) {
        if (procesoSeleccionado != null) {
            crearUltimoActividad.setDisable(false);
            crearDespuesActividad.setDisable(false);
            crearOrdenActividad.setDisable(false);
        }
    }

    @FXML
    void onCrearDespuesDe(ActionEvent event) {
        String nombreActividad = txtNombreActividad.getText();
        String descripcionActividad = txtDescripcionActividad.getText();
        if (verificarCamposActividad()) {
            if (actividadSeleccionada != null) {
                Actividad nuevaActividad = null;
                Actividad actividad = new Actividad();
                actividad.setNombre(nombreActividad);
                actividad.setDescripcion(descripcionActividad);
                actividad.setObligatoria(obtenerEstado());
                nuevaActividad = modelFactory.crearActividadDespuesDe(procesoSeleccionado, actividadSeleccionada,
                        actividad);
                if (nuevaActividad != null) {
                    listaActividades.add(nuevaActividad);
                    cargarActividadesEnTabla();
                    limpiarCamposActividad();
                    mostrarMensaje("Actividad Registrada");
                } else {
                    mostrarMensaje("Actividad no registrada");
                }
            } else {
                mostrarMensaje("Debe seleccionar una actividad para insertar " + txtNombreActividad.getText() + " después" +
                        " de esta");
            }
        } else {
            mostrarMensaje("Las actividades deben tener nombre y descripción");
        }
    }

    @FXML
    void crearTareaFinal(ActionEvent event) {
        String descripcionTarea = txtDescripcionTarea.getText();
        int duracion = Integer.parseInt(txtDuracionTarea.getText());

        if (verificarCamposActividadTarea()) {
            Tarea nuevaTarea = null;
            Tarea tarea = new Tarea();
            tarea.setEstado(Estado.valueOf(obtenerEstadoTarea()));
            tarea.setDescripcion(descripcionTarea);
            tarea.setDuracionMinutos(duracion);
            nuevaTarea = modelFactory.crearTareaFinal(procesoSeleccionado, actividadSeleccionada, tarea);
            if (nuevaTarea != null) {
                listaTareas.add(nuevaTarea);
                cargarActividadesEnTabla();
                limpiarCamposTarea();
                mostrarMensaje("Tarea Registrada");
            } else {
                mostrarMensaje("Tarea no registrada");
            }
        } else {
            mostrarMensaje("Las Tarea deben tener descripción");
        }
    }

    @FXML
    void onCrearUltimo(ActionEvent event) {
        String nombreActividad = txtNombreActividad.getText();
        String descripcionActividad = txtDescripcionActividad.getText();

        if (verificarCamposActividad()) {
            Actividad nuevaActividad = null;
            Actividad actividad = new Actividad();
            actividad.setNombre(nombreActividad);
            actividad.setDescripcion(descripcionActividad);
            actividad.setObligatoria(obtenerEstado());
            nuevaActividad = modelFactory.crearActividadFinal(procesoSeleccionado, actividad);
            if (nuevaActividad != null) {
                listaActividades.add(nuevaActividad);
                cargarActividadesEnTabla();
                limpiarCamposActividad();
                mostrarMensaje("Actividad Registrada");
            } else {
                mostrarMensaje("Actividad no registrada");
            }
        } else {
            mostrarMensaje("Las actividades deben tener nombre y descripción");
        }
    }

    private boolean verificarCamposActividad() {
        return !txtNombreActividad.getText().isEmpty() || !txtDescripcionActividad.getText().isEmpty();
    }

    private boolean verificarCamposActividadTarea() {
        return !txtDuracionTarea.getText().isEmpty() || !txtDescripcionActividad.getText().isEmpty();
    }

    @FXML
    void onCrearOrden(ActionEvent event) {
=======
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
>>>>>>> origin/master

    }

    @FXML
<<<<<<< HEAD
    public void initialize() {
        modelFactory = ModelFactory.getInstance();
        //inicializar la tabla de procesos
        colIdProceso.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNumActividades.setCellValueFactory(new PropertyValueFactory<>("numActividades"));
        colNombreProceso2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcionActividad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));

        tblProcesos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = (Proceso) newSelection;
            }
        });
        tblProcesos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = (Proceso) newSelection;
                cargarActividadesEnTabla();
            }
        });
        tblActividades.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                actividadSeleccionada = (Actividad) newSelection;
            }
        });
        cargarProcesosEnTabla();
        cargarProcesosNombreEnTabla();
        cargarActividadesEnTabla();
    }

    private String obtenerEstado() {
        return checkObligatorio.isSelected() ? "Obligatorio" : "No Obligatorio";
    }

    private String obtenerEstadoTarea() {
        return checkBoxTarea.isSelected() ? "OBLIGATORIO" : "OPTIONAL";
=======
    void onConsultarTiempoPClick(ActionEvent event) {

>>>>>>> origin/master
    }

    @FXML
    void onRegistrarClick(ActionEvent event) {
<<<<<<< HEAD
        String id = String.valueOf(Proceso.generarID());
        String nombre = txtNombreProceso.getText();
        int numActividades = 0;

        if (nombre.isEmpty()) {
            mostrarMensaje("Por favor escriba el nombre");
            return;
        }
        Proceso nuevoProceso = null;
        Proceso proceso = new Proceso();
        proceso.setId(id);
        proceso.setNombre(nombre);
        proceso.setNumActividades(numActividades);
        nuevoProceso = modelFactory.crearProceso(proceso);
        if (nuevoProceso != null) {
            listaProcesos.add(nuevoProceso);
            cargarProcesosEnTabla();
            limpiarCamposProceso();
            mostrarMensaje("Proceso registrado correctamente.");
        } else {
            mostrarMensaje("Proceso ya existe");
        }
    }

    //eliminar proceso
    @FXML
    void onRemoverClick(ActionEvent event) {
        if (procesoSeleccionado != null) {
            modelFactory.getAplicacion().eliminarProceso(procesoSeleccionado);
            cargarProcesosEnTabla();
            mostrarMensaje("Proceso eliminado correctamente.");
        } else {
            mostrarMensaje("Seleccione un proceso antes de intentar eliminarlo.");
        }
    }

    @FXML
    void onBuscarClick(ActionEvent event) {
        boolean bandera = false;
        String buscado = txtAux.getText();

        for (Proceso proceso : listaProcesos) {
            if (buscado.equals(proceso.getNombre())) {
                bandera = true;
                break;  // Agregar un break para salir del bucle una vez que se encuentra el proceso
            }
        }

        if (bandera) {
            mostrarMensaje("Proceso encontrado");
        } else {
            mostrarMensaje("Proceso no encontrado");
        }
    }

    @FXML
    void onCambiarNombreClick(ActionEvent event) {
        if (procesoSeleccionado != null) {
            String nombre = txtAux.getText();
            procesoSeleccionado.setNombre(nombre);
            mostrarMensaje("Proceso cambiado correctamente.");
        } else {
            mostrarMensaje("Proceso no cambiado");
        }
        // Actualizar la tabla
        //cargarProcesosEnTabla();

        // Limpiar los campos de texto

        limpiarCamposProceso();
        // Mostrar un mensaje de éxito

    }

    @FXML
    void onCancelarClick(ActionEvent event) {
        limpiarCamposProceso();

    }

    @FXML
    void onConsultarTiempoPClick(ActionEvent event) {

    }

    public MainApp getApp() {
        return app;
    }

    public void setApp(MainApp app) {
        this.app = app;
    }

    // Cargar procesos en la tabla
    public void cargarProcesosEnTabla() {
        colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdProceso.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumActividades.setCellValueFactory(new PropertyValueFactory<>("numActividades"));

        tblProcesos.getItems().clear();
        tblProcesos.setItems(getListaProcesos());
        tblProcesos.refresh();
    }

    public void cargarProcesosNombreEnTabla() {
        colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblProcesos2.getItems().clear();
        tblProcesos2.setItems(getListaProcesosAct());
        tblProcesos2.refresh();
    }

    public void cargarProcesosTarea() {
        colProcesoTarea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblProcesosTareas.getItems().clear();
        tblProcesosTareas.setItems(getListaProcesosTarea());
        tblProcesosTareas.refresh();
    }

    public void cargarActividadesEnTabla() {
        colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcionActividad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));
        tblActividades.getItems().clear();
        tblActividades.setItems(getListaActivades(procesoSeleccionado));
        tblActividades.refresh();
    }

    public void cargarActividadesTareas() {
        colNombreActividadTarea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcionActividadTarea.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstadoActividadTarea.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));
        tblActividadesTareas.getItems().clear();
        tblActividadesTareas.setItems(getListaActivades(procesoSeleccionado));
        tblActividadesTareas.refresh();
    }
    public void cargarTareasEnTabla() {
        colDescripcionTarea.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colDuracionTarea.setCellValueFactory(new PropertyValueFactory<>("duracionMinutos"));
        colObligatorioTarea.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tblTareas.getItems().clear();
        tblTareas.setItems(getListaTareas(procesoSeleccionado, actividadSeleccionada));
        tblTareas.refresh();
    }

    public ObservableList<Proceso> getListaProcesos() {
        listaProcesos.addAll(modelFactory.getAplicacion().getListaProcesos());
        return listaProcesos;
    }

    public ObservableList<Proceso> getListaProcesosAct() {
        listaProcesosAct.addAll(modelFactory.getAplicacion().getListaProcesos());
        return listaProcesos;
    }

    public ObservableList<Proceso> getListaProcesosTarea() {
        listaProcesosAct.addAll(modelFactory.getAplicacion().getListaProcesos());
        return listaProcesos;
    }

    public ObservableList<Actividad> getListaActivades(Proceso proceso) {
        if(proceso != null)
            listaActividades.addAll(modelFactory.getAplicacion().buscarActividades(proceso));
        return listaActividades;
    }


    public ObservableList<Tarea> getListaTareas(Proceso proceso, Actividad actividad) {
        if(actividad != null)
            listaTareas.addAll(modelFactory.getAplicacion().buscarTareas(proceso, actividad));
        return listaTareas;
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

    void limpiarCamposActividad() {
        txtNombreActividad.clear();
        txtDescripcionActividad.clear();
        checkObligatorio.setSelected(false);
    }

    void limpiarCamposTarea() {
        txtDescripcionTarea.clear();
        txtDuracionTarea.clear();
        txtPosicionTarea.clear();
        checkBoxTarea.setSelected(false);
=======

    }

    @FXML
    void onRemoverClick(ActionEvent event) {

    }


    public MainApp getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(MainApp aplicacion) {
        this.aplicacion = aplicacion;
>>>>>>> origin/master
    }
}
