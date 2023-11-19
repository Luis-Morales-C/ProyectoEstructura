package estructura.controller;

import estructura.exceptions.ProcesoExisteException;
import estructura.model.Proceso;
import estructura.persistencia.Persistencia;

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

    }
    public Proceso crearProceso(Proceso proceso){
        Proceso nuevoProceso = null;
        try {
            nuevoProceso =getProceso().crearProceso(proceso.getNombre());
            Persistencia.guardarProcesos(getProceso().getListaProcesos());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ProcesoExisteException e) {
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
}