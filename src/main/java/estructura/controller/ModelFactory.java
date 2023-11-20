package estructura.controller;

import estructura.exceptions.ProcesoExisteException;
import estructura.model.Actividad;
import estructura.model.Aplicacion;
import estructura.model.ListaDobleEnlazada;
import estructura.model.Proceso;
import estructura.persistencia.Persistencia;
import javafx.scene.control.Alert;

import java.io.IOException;


public class ModelFactory {
    Aplicacion aplicacion;

    private static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public ModelFactory() {
        cargarDatos();
    }

    public void cargarDatos() {
        aplicacion = new Aplicacion();
        try {
            Persistencia.cargarDatos(aplicacion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Proceso crearProceso(Proceso proceso) {
        Proceso nuevoProceso = null;
        try {
            nuevoProceso = getAplicacion().crearProceso(proceso);
            Persistencia.guardarProcesos(getAplicacion().getListaProcesos());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ProcesoExisteException e) {
            mostrarMensaje("El proceso ya existe");
            throw new RuntimeException(e);
        }
        return nuevoProceso;
    }

    public Actividad crearActividadFinal(Proceso proceso, Actividad actividad) {
        Actividad nuevaActividad = null;
        try {
            nuevaActividad = getAplicacion().crearActividadFinal(proceso, actividad);
            Persistencia.guardarActividades(getAplicacion().getListaProcesos(), proceso.getActividades().aLista());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return actividad;
    }

    public Actividad crearActividadDespuesDe(Proceso proceso, Actividad actividadAnterior, Actividad nueva) {
        Actividad nuevaActividad = null;
        try {
            nuevaActividad = getAplicacion().crearActividadDespuesDe(proceso, actividadAnterior, nueva);
            Persistencia.guardarActividades(getAplicacion().getListaProcesos(), proceso.getActividades().aLista());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return actividadAnterior;
    }

    /*public Proceso getProceso() {
        if (proceso == null) {
            proceso = new Proceso();
        }
        return proceso;
    }

    public Proceso buscarProceso(Proceso proceso) {

    }
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }*/
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}