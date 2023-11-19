package estructura.controller;

import estructura.exceptions.ProcesoExisteException;
import estructura.model.ListaDobleEnlazada;
import estructura.model.Proceso;
import estructura.persistencia.Persistencia;
import javafx.scene.control.Alert;

import java.io.IOException;


public class ModelFactory {
    Proceso proceso;

    private static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactory() {
        cargarDatos();
    }
    public ListaDobleEnlazada<Proceso> cargarDatos() {
        Proceso proceso = new Proceso();
        try {
            ListaDobleEnlazada<Proceso> listaProcesos = Persistencia.cargarDatos(proceso);
            setProceso(proceso); // Actualizar el proceso en ModelFactory
            return listaProcesos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Proceso crearProceso(Proceso proceso){
        Proceso nuevoProceso = null;
        try {
            nuevoProceso =getProceso().crearProceso(proceso.getNombre());
            Persistencia.guardarProcesos(getProceso().getListaProcesos());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ProcesoExisteException e) {
            mostrarMensaje("PROCESO YA EXISTE");
            throw new RuntimeException(e);
        }
        return nuevoProceso;
    }

    public Proceso getProceso() {
        if (proceso == null) {
            proceso = new Proceso();
        }
        return proceso;
    }
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}