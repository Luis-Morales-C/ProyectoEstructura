package estructura.model;


import estructura.exceptions.ProcesoExisteException;
import estructura.persistencia.Persistencia;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

/**
 * Clase que representa un proceso en la aplicación.
 */
public class Proceso {
    private String id;
    private String nombre;
    private int numActividades;
    private ListaDobleEnlazada<Proceso> ListaProcesos;
    private ListaDobleEnlazada<Actividad> actividades;
    private ListaDobleEnlazada<Tarea> tareas = new Actividad().getListaTarea();

    private static ListaDobleEnlazada<Integer> ids = new ListaDobleEnlazada<>();
    private int duracionTotal;

    /**
     * Constructor de la clase Procesos.
     *
     * @param nombre El nombre del proceso.
     */
    public Proceso(String nombre) {
        this.id = String.valueOf(generarID());
        this.nombre = nombre;
        this.actividades = new ListaDobleEnlazada<>();
        this.duracionTotal = 0;
    }

    public Proceso() {
        super();
    }

    /**
     * Obtiene el ID del proceso.
     *
     * @return El ID del proceso.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del proceso.
     *
     * @return El nombre del proceso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Añade una actividad al proceso.
     *
     * @param actividad La actividad a añadir.
     */
    public void addActividad(Actividad actividad) {
        actividades.agregarUltimo(actividad);
        actualizarDuracion();
    }

    /**
     * Actualiza la duración total del proceso.
     *
     * @param totalDurationMinutes La duración total en minutos a agregar.
     */
    private void actualizarDuracion(int totalDurationMinutes) {
        this.duracionTotal += totalDurationMinutes;
    }

    /**
     * Actualiza la duración total del proceso sumando las duraciones de todas las actividades.
     */
    private void actualizarDuracion() {
        this.duracionTotal = 0;
        actividades.forEach(actividad ->
                actualizarDuracion(actividad.getTotalDuracionMinutes()), false);
    }

    /**
     * Obtiene la duración total del proceso en minutos.
     *
     * @return La duración total en minutos del proceso.
     */
    public int getDuracionTotal() {
        return this.duracionTotal;
    }

    /**
     * Obtiene la lista de actividades asociadas al proceso.
     *
     * @return La lista de actividades del proceso.
     */
    public ListaDobleEnlazada<Actividad> getActividades() {
        return actividades;
    }


    public ListaDobleEnlazada<Tarea> getTareas() {
        return tareas;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActividades(ListaDobleEnlazada<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public void setTareas(ListaDobleEnlazada<Tarea> tareas) {
        this.tareas = tareas;
    }

    public ListaDobleEnlazada<Proceso> getListaProcesos() {
        if (ListaProcesos == null) {
            ListaProcesos = new ListaDobleEnlazada<>();
        }
        return ListaProcesos;
    }


    public void setListaProcesos(ListaDobleEnlazada<Proceso> listaProcesos) {
        ListaProcesos = listaProcesos;
    }

    public boolean buscarProceso(Proceso procesoBuscado) {
        if (ListaProcesos == null) {
            return false;
        }
        Iterator<Proceso> iterator = ListaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            String cadena1=procesoActual.getNombre().replace(" ","");
            if (cadena1.equals(procesoBuscado.getNombre().replace(" ",""))) {
                return true;
            }
        }
        return false;
    }

    public Proceso crearProceso(String nombre) throws ProcesoExisteException {
        Proceso nuevoProceso = new Proceso(nombre);
        if (ListaProcesos == null) {
            ListaProcesos = new ListaDobleEnlazada<>();
        }
        if (!buscarProceso(nuevoProceso)) {
            ListaProcesos.agregarUltimo(nuevoProceso);
        } else {
            throw new ProcesoExisteException("El proceso ya existe");
        }
        return nuevoProceso;
    }
    public void eliminarProceso(Proceso proceso) {
        try {
            if (ListaProcesos != null) {
                Iterator<Proceso> iterator = ListaProcesos.iterator();
                while (iterator.hasNext()) {
                    Proceso procesoActual = iterator.next();
                    String cadena1 = procesoActual.getNombre().replace(" ", "");
                    String cadena2 = proceso.getNombre().replace(" ", "");
                    if (cadena1.equals(cadena2)) {
                        iterator.remove();
                        break;  // Mover el break dentro del if para salir después de eliminar el elemento
                    }
                }

                // Actualizar la lista antes de guardarla en persistencia
                setListaProcesos(ListaProcesos);

                // Guardar la lista actualizada en persistencia
                Persistencia.guardarProcesos(getListaProcesos());
            }
        } catch (IOException e) {
            // Manejar la excepción (mostrar mensaje, registrar en un archivo de registro, etc.)
            e.printStackTrace();
        }
    }

    public int getNumActividades() {
        return numActividades;
    }

    public void setNumActividades(int numActividades) {
        this.numActividades = numActividades;
    }
    public static int generarID() {
        Random random = new Random();
        int nuevoID;
        do {
            nuevoID = random.nextInt(100) + 1;
        } while (idExisteEnLista(nuevoID));
        return nuevoID;
    }
    private static boolean idExisteEnLista(int id) {
        // Utiliza el iterador para recorrer la lista de IDs y verifica si el ID ya existe
        for (Integer existingID : ids) {
            if (existingID == id) {
                return true; // El ID ya existe en la lista
            }
        }
        return false; // El ID no existe en la lista
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proceso proceso = (Proceso) o;

        if (numActividades != proceso.numActividades) return false;
        if (duracionTotal != proceso.duracionTotal) return false;
        if (!Objects.equals(id, proceso.id)) return false;
        if (!Objects.equals(nombre, proceso.nombre)) return false;
        if (!Objects.equals(ListaProcesos, proceso.ListaProcesos))
            return false;
        if (!Objects.equals(actividades, proceso.actividades)) return false;
        return Objects.equals(tareas, proceso.tareas);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + numActividades;
        result = 31 * result + (ListaProcesos != null ? ListaProcesos.hashCode() : 0);
        result = 31 * result + (actividades != null ? actividades.hashCode() : 0);
        result = 31 * result + (tareas != null ? tareas.hashCode() : 0);
        result = 31 * result + duracionTotal;
        return result;
    }
}
