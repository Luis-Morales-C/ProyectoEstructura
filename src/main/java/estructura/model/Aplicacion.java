package estructura.model;

import estructura.exceptions.ProcesoExisteException;
import estructura.persistencia.Persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Aplicacion implements Serializable {
    private ListaDobleEnlazada<Proceso> listaProcesos = new ListaDobleEnlazada<>();

    public Aplicacion() {
    }

    public List<Proceso> getListaProcesos() {
        return listaProcesos.aLista();
    }

    public void setListaProcesos(ListaDobleEnlazada<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public boolean buscarProceso(Proceso procesoBuscado) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            String nombre = procesoActual.getNombre().replace(" ","");
            if (nombre.equals(procesoBuscado.getNombre().replace(" ",""))) {
                return true;
            }
        }
        return false;
    }
    public Proceso crearProceso(Proceso proceso) throws ProcesoExisteException {
        if (!buscarProceso(proceso)) {
            listaProcesos.agregarUltimo(proceso);
        } else {
            throw new ProcesoExisteException("El proceso ya existe");
        }
        return proceso;
    }

    public void eliminarProceso(Proceso proceso) {
        try {
            Iterator<Proceso> iterator = listaProcesos.iterator();
            while (iterator.hasNext()) {
                Proceso procesoActual = iterator.next();
                String cadena1 = procesoActual.getNombre().replace(" ", "");
                String cadena2 = proceso.getNombre().replace(" ", "");
                if (cadena1.equals(cadena2)) {
                    iterator.remove();
                    break;  // Mover el break dentro del if para salir después de eliminar el elemento
                }
            }
            setListaProcesos(listaProcesos);
            Persistencia.guardarProcesos(getListaProcesos());
        } catch (IOException e) {
            // Manejar la excepción (mostrar mensaje, registrar en un archivo de registro, etc.)
            e.printStackTrace();
        }
    }

    public Actividad crearActividadFinal(Proceso procesoSeleccionado, Actividad actividad) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            if(procesoActual.equals(procesoSeleccionado)) {
                actividad.setProceso(procesoActual.getNombre());
                procesoActual.getActividades().agregarUltimo(actividad);
                procesoSeleccionado = procesoActual;
            }
        }
        return actividad;
    }

    public Actividad crearActividadDespuesDe(Proceso procesoSeleccionado, Actividad actividadAnterior, Actividad nuevaActividad) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            if(procesoActual.equals(procesoSeleccionado)) {
                Iterator<Actividad> actividadIterator = procesoActual.getActividades().iterator();
                ListaDobleEnlazada<Actividad> nuevaListaActividades = new ListaDobleEnlazada<>();
                while(actividadIterator.hasNext()){
                    Actividad actividadActual = actividadIterator.next();
                    actividadActual.setProceso(procesoActual.getNombre());
                    nuevaListaActividades.agregarUltimo(actividadActual);
                    if(actividadActual.equals(actividadAnterior)) {
                        nuevaListaActividades.agregarUltimo(nuevaActividad);
                    }
                }
                procesoActual.setActividades(nuevaListaActividades);
            }
        }
        return nuevaActividad;
    }

    public List<Actividad> buscarActividades(Proceso proceso) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            if(procesoActual.equals(proceso))
                return procesoActual.getActividades().aLista();
        }
        return null;
    }

    public List<Tarea> buscarTareas(Proceso proceso, Actividad actividad) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            if(procesoActual.equals(proceso)) {
                Iterator<Actividad> actividadIterator = procesoActual.getActividades().iterator();
                while (actividadIterator.hasNext()) {
                    Actividad actividadActual = actividadIterator.next();
                    if(actividadActual.equals(actividad)) {
                        return actividadActual.getTareasPendientes().toList();
                    }
                }
            }
        }
        return null;
    }

    public Tarea crearTareaFinal(Proceso procesoSeleccionado, Actividad actividadSeleccionada, Tarea tarea) {
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            if(procesoActual.equals(procesoSeleccionado)) {
                Iterator<Actividad> actividadIterator = procesoActual.getActividades().iterator();
                procesoActual.getActividades().borrarLista();
                while(actividadIterator.hasNext()) {
                    Actividad actividadActual = actividadIterator.next();
                    if(actividadActual==null)
                        break;
                    if(actividadActual.equals(actividadSeleccionada)) {
                        tarea.setProceso(procesoActual.getNombre());
                        tarea.setActividad(actividadActual.getNombre());
                        actividadActual.getTareasPendientes().enqueue(tarea);
                    }
                    procesoActual.getActividades().agregarUltimo(actividadActual);
                }
            }
        }
        return tarea;
    }
}
