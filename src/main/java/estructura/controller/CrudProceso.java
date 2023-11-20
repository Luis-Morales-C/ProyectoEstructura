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

import java.util.List;

public class CrudProceso {

    private MainApp aplicacion;

    private ModelFactory modelFactory;

    private Proceso procesoSeleccionado;

    private Actividad actividadSeleccionada;

    private Tarea tareaSeleccionada;


    //Items Proceso
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
    @FXML
    private TextField txtAux;

    //items actividades
    @FXML
    private SplitMenuButton SplitCrear;

    @FXML
    private CheckBox checkObligatorio;

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
    private TextField txtNombreActividad;

    @FXML
    private TextField txtDescripcionActividad;
    @FXML
    void onCrearActividad(ActionEvent event) {

    }
    @FXML
    void onCrearDespuesDe(ActionEvent event) {

    }

    @FXML
    void onCrearOrden(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        modelFactory = ModelFactory.getInstance();
        //inicializar la tabla de procesos
        colIdProceso.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNumActividades.setCellValueFactory(new PropertyValueFactory<>("numActividades"));

        //inicializar la tabla de procesos2
        colNombreProceso2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //inicializar la tabla de actividades

        colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcionActividad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));

        // Configurar el evento de selección de la tabla
        tblProcesos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = (Proceso) newSelection;
            }
        });
        tblProcesos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = (Proceso) newSelection;
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

    @FXML
    void onCrearUltimo(ActionEvent event) {
        String nombreActividad = txtNombreActividad.getText();
        String descripcionActividad = txtDescripcionActividad.getText();

        if (nombreActividad.isEmpty()) {
            mostrarMensaje("Por favor escriba el nombre");
            return;
        }
        if (descripcionActividad.isEmpty()) {
            mostrarMensaje("Por favor escriba la descripción");
            return;
        }

        Actividad nuevaActividad = new Actividad();
        Actividad actividad = new Actividad();
        actividad.setNombre(nombreActividad);
        actividad.setDescripcion(descripcionActividad);


        actividad.setObligatoria(obtenerEstado());


        nuevaActividad = modelFactory.crearActividadFinal(procesoSeleccionado, actividad);

        if (nuevaActividad != null) {
            listaActividad.add(nuevaActividad);

            cargarActividadesEnTabla();

            limpiarCamposActividad();

            mostrarMensaje("Actividad Registrada");
        } else {
            mostrarMensaje("Actividad no registrada");
        }
    }



    private ObservableList<Proceso> listaProcesos = FXCollections.observableArrayList();
    private ObservableList<Actividad> listaActividad = FXCollections.observableArrayList();
    private ObservableList<Tarea> listaTarea = FXCollections.observableArrayList();


    //Registro Proceso
    @FXML
    void onRegistrarClick(ActionEvent event) {
        String id = String.valueOf(Proceso.generarID());
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

    //eliminar proceso
    @FXML
    void onRemoverClick(ActionEvent event) {
        if (procesoSeleccionado != null) {
            // Llamamos al método eliminarProceso del modelo, pasando el procesoSeleccionado
            modelFactory.getProceso().eliminarProceso(procesoSeleccionado);

            // Actualizamos la tabla después de la eliminación
            cargarProcesosEnTabla();

            // Mostrar un mensaje de éxito o cualquier lógica adicional que desees
            mostrarMensaje("Proceso eliminado correctamente.");
        } else {
            // Mostrar un mensaje de error o lógica adicional si no hay un proceso seleccionado
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
        if(procesoSeleccionado!=null){
            String nombre= txtAux.getText();
            procesoSeleccionado.setNombre(nombre);
            mostrarMensaje("Proceso cambiado correctamente.");
        }else{
            mostrarMensaje("Proceso no cambiado");
        }
            // Actualizar la tabla
            cargarProcesosEnTabla();

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


    public MainApp getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(MainApp aplicacion) {
        this.aplicacion = aplicacion;
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
    public void cargarProcesosNombreEnTabla() {
        if (modelFactory != null && modelFactory.getProceso() != null) {
             colNombreProceso2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            // Asignar los datos a la tabla
            tblProcesos2.getItems().clear();

            List<Proceso> listaProcesos = getListaProcesos();
            if (listaProcesos != null) {
                tblProcesos2.setItems((ObservableList<Proceso>) listaProcesos);
            } else {
                // Manejo de caso en que getListaProcesos() devuelve nulo
            }

            tblProcesos2.refresh();
        } else {
            // Manejo de caso en que modelFactory o getProceso() es nulo
        }
    }
    public void cargarActividadesEnTabla() {
        if (modelFactory != null && modelFactory.getProceso().getActividades() != null) {
            colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colDescripcionActividad.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            colEstado.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));

            // Asignar los datos a la tabla
            tblActividades.getItems().clear();

            ObservableList<Actividad> listaActividades = getListaActivades(); // Cambio aquí
            if (listaActividades != null) {
                tblActividades.setItems(listaActividades);
            } else {
                // Manejo de caso en que la listaActividades es nula
            }
            tblActividades.refresh();
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
    public ObservableList<Actividad> getListaActivades() {
        ListaDobleEnlazada<Actividad> listaActividad = modelFactory.getProceso().getActividades();
        ObservableList<Actividad> observableList = FXCollections.observableArrayList();

        if (listaActividad != null) {
            observableList.addAll(listaActividad.aLista());
        } else {
            // Manejo de caso en que la lista de actividades es nula
            System.out.println("La lista de actividades es nula");
        }

        return observableList;
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

    }
}
