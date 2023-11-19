package estructura.infrastructure.view.controllers;

        import estructura.main.modelos.Actividades;
        import estructura.main.modelos.Procesos;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Alert.AlertType;

        import java.util.ArrayList;
        import java.util.List;

public class Controller {

    public Button btnIniciarSesion;
    public Button btnVerDetallesProceso;
    public Button btnVerDetallesActividad;
    @FXML
    private ListView<String> listaProcesos;

    @FXML
    private ListView<String> listaActividades;

    @FXML
    private TextField nombreProceso;

    @FXML
    private TextField nombreActividad;

    @FXML
    private TextArea descripcionActividad;

    @FXML
    private CheckBox esObligatoria;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<Procesos> tablaProcesos;
    @FXML
    private TableView<Actividades> tablaActividades;

    private List<Procesos> listaDeProcesos = new ArrayList<>();


    @FXML
    private void handleNuevoProceso() {
        String nombre = nombreProceso.getText();

        if (!nombre.isEmpty()) {
            // Crear un nuevo proceso y agregarlo a la lista
            listaProcesos.getItems().add(nombre);

            // Limpiar el campo de nombre del proceso
            nombreProceso.clear();
        } else {
            // Mostrar un mensaje de error si el nombre está vacío
            showAlert("Error", "Ingrese un nombre para el proceso.");
        }
    }

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        // Otras inicializaciones

        btnSalir.setOnAction(event -> handleSalir());
    }//completar



    @FXML
    private void handleSalir() {
        // Lógica para salir de la aplicación
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleIniciarSesion() {
        // Lógica para iniciar sesión del usuario
    }

    @FXML
    private void handleCerrarSesion() {
        // Lógica para cerrar sesión del usuario
    }


    ///

    @FXML
    private void handleVerDetallesProceso() {
        // Obtener el proceso seleccionado (puedes implementar esto según tu lógica)
        Procesos procesoSeleccionado = obtenerProcesoSeleccionado();

        // Verificar si se seleccionó un proceso
        if (procesoSeleccionado != null) {
            // Mostrar los detalles del proceso en una ventana o área específica
            mostrarDetallesProceso(procesoSeleccionado);
        } else {
            // Mostrar un mensaje indicando que no se ha seleccionado ningún proceso
            mostrarMensajeNoSeleccion();
        }
    }

    // Método de ejemplo para obtener el proceso seleccionado (debes implementar según tu lógica)
    private Procesos obtenerProcesoSeleccionado() {
        // Obtener la fila seleccionada en la tabla
        return tablaProcesos.getSelectionModel().getSelectedItem();
    }

    // Método de ejemplo para mostrar los detalles del proceso
    private void mostrarDetallesProceso(Procesos proceso) {
        // Verificar si se ha seleccionado un proceso
        if (proceso != null) {
            // Crear un cuadro de diálogo de tipo INFORMATION
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Detalles del Proceso");
            alert.setHeaderText("Detalles del Proceso: " + proceso.getName());

            // Construir el contenido del cuadro de diálogo con los detalles del proceso
            String contenido = "ID: " + proceso.getId() + "\n"
                    + "Nombre: " + proceso.getName() + "\n"
                    // Agregar más detalles según la estructura de tu clase Procesos
                    + "Duración Total: " + proceso.getDuracionTotal();
            alert.setContentText(contenido);

            // Mostrar el cuadro de diálogo
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un proceso, mostrar un mensaje
            mostrarMensajeNoSeleccion();
        }
    }

    // Método para mostrar un mensaje indicando que no se ha seleccionado ningún proceso
    private void mostrarMensajeNoSeleccion() {
        // Aquí implementa la lógica para mostrar un mensaje indicando que no se ha seleccionado ningún proceso
        // Puedes utilizar un cuadro de diálogo o algún otro componente visual
        // Ejemplo:
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Ningún proceso seleccionado");
        alert.setContentText("Por favor, selecciona un proceso.");
        alert.showAndWait();
    }





    @FXML
    private void handleVerDetallesActividad() {
        // Obtener la actividad seleccionada (puedes implementar esto según tu lógica)
        Actividades actividadSeleccionada = obtenerActividadSeleccionada();

        // Verificar si se seleccionó una actividad
        if (actividadSeleccionada != null) {
            // Mostrar los detalles de la actividad en una ventana o área específica
            mostrarDetallesActividad(actividadSeleccionada);
        } else {
            // Mostrar un mensaje indicando que no se ha seleccionado ninguna actividad
            mostrarMensajeNoSeleccion();
        }
    }

    // Método de ejemplo para obtener la actividad seleccionada (debes implementar según tu lógica)
    private Actividades obtenerActividadSeleccionada() {
        // Obtener la fila seleccionada en la tabla (asumiendo que tienes una TableView llamada tablaActividades)
        Actividades actividadSeleccionada;
        actividadSeleccionada = tablaActividades.getSelectionModel().getSelectedItem();

        return actividadSeleccionada;
    }

    // Método de ejemplo para mostrar los detalles de la actividad
    private void mostrarDetallesActividad(Actividades actividad) {
        // Verificar si se ha seleccionado una actividad
        if (actividad != null) {
            // Crear un cuadro de diálogo de tipo INFORMATION
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Detalles de la Actividad");
            alert.setHeaderText("Detalles de la Actividad: " + actividad.getNombre());

            // Construir el contenido del cuadro de diálogo con los detalles de la actividad
            String contenido = "ID: " + actividad.getId() + "\n"
                    + "Nombre: " + actividad.getNombre() + "\n"
                    // Puedes agregar más detalles según la estructura de tu clase Actividades
                    + "Duración Total: " + actividad.getTotalDuracionMinutes() + " minutos";
            alert.setContentText(contenido);

            // Mostrar el cuadro de diálogo
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado una actividad, mostrar un mensaje
            mostrarMensajeNoSeleccion();
        }
    }



    @FXML
    private void handleCrearProceso() {
        // Obtener el nombre del proceso desde el TextField
        String nombreProcesoTexto = nombreProceso.getText();

        // Verificar si el nombre del proceso no está vacío
        if (nombreProcesoTexto != null && !nombreProcesoTexto.trim().isEmpty()) {
            // Crear una nueva instancia de Procesos
            Procesos nuevoProceso = new Procesos(nombreProcesoTexto);

            // Agregar el nuevo proceso a tu estructura de datos o lista de procesos
            listaDeProcesos.add(nuevoProceso);

            // Limpiar el TextField después de crear el proceso
            nombreProceso.clear();

            // Actualizar la interfaz o realizar otras acciones según sea necesario
            actualizarInterfaz();
        } else {
            // Mostrar un mensaje de error o tomar otras acciones si el nombre del proceso está vacío
            mostrarMensajeError("El nombre del proceso no puede estar vacío");
        }
    }


    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    // Método para actualizar la interfaz según sea necesario
    private void actualizarInterfaz() {
        // Implementa la lógica para actualizar la interfaz
        // Esto puede incluir la actualización de listas, tablas u otros componentes visuales
    }



    @FXML
    private void handleCrearActividad() {
        // Obtener los valores de los campos de la interfaz
        String nombre = nombreActividad.getText();
        String descripcion = descripcionActividad.getText();
        boolean obligatoria = esObligatoria.isSelected();

        // Validar que se ingresen datos válidos
        if (nombre.isEmpty()) {
            mostrarMensajeError("El nombre de la actividad no puede estar vacío.");
            return;
        }

        // Crear la actividad con los valores obtenidos
        Actividades nuevaActividad = new Actividades(nombre);
        nuevaActividad.setDescripcion(descripcion);
        nuevaActividad.setObligatoria(obligatoria);

        // Lógica adicional para agregar la actividad al proceso, según tu implementación

        // Limpiar los campos después de crear la actividad
        limpiarCamposCrearActividad();
    }

    // Método para limpiar los campos después de crear una actividad
    private void limpiarCamposCrearActividad() {
        nombreActividad.clear();
        descripcionActividad.clear();
        esObligatoria.setSelected(false);
    }

    // Método para mostrar un mensaje de error en la interfaz
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}

