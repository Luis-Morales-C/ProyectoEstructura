package estructura.main.modelos;

import estructura.main.nodos.ListaDobleEnlazada;
import estructura.main.util.CrearID;

/**
 * Clase que representa un proceso en la aplicación.
 */
public class Procesos {
    private String id;
    private String name;
    private ListaDobleEnlazada<Actividades> actividades;
    private int duracionTotal;

    /**
     * Constructor de la clase Procesos.
     *
     * @param name El nombre del proceso.
     */
    public Procesos(String name) {
        this.id = CrearID.generarID();
        this.name = name;
        this.actividades = new ListaDobleEnlazada<>();
        this.duracionTotal = 0;
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
    public String getName() {
        return name;
    }

    /**
     * Añade una actividad al proceso.
     *
     * @param actividad La actividad a añadir.
     */
    public void addActividad(Actividades actividad) {
        actividades.addLast(actividad);
        actualizarDuracion();
    }

    /**
     * Obtiene una actividad del proceso por su ID.
     *
     * @param actividadId El ID de la actividad.
     * @return La actividad correspondiente, o null si no se encuentra.
     */
    public Actividades getActivity(String actividadId) {
        return actividades.findFirst(actividad -> actividad.getId().equals(actividadId));
    }

    /**
     * Elimina una actividad del proceso por su ID.
     *
     * @param actividadId El ID de la actividad a eliminar.
     * @return true si la actividad se eliminó correctamente, false si no se encontró.
     */
    public boolean removeActivity(String actividadId) {
        boolean isRemoved = actividades.removeIf(actividad -> actividad.getId().equals(actividadId));
        if (isRemoved) {
            actualizarDuracion();
        }
        return isRemoved;
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
    public ListaDobleEnlazada<Actividades> getActividades() {
        return actividades;
    }
}
