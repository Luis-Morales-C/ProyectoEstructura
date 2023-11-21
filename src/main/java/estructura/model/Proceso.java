package estructura.model;

import java.util.Random;

/**
 * Clase que representa un proceso en la aplicación.
 */
public class Proceso {
    private String id;
    private String nombre;
    private ListaDobleEnlazada<Actividad> actividades;
    private ListaDobleEnlazada<Tarea> tareas = new Actividad().getListaTarea();
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
        actividades.addLast(actividad);
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
        actividades.forEach(actividad -> actualizarDuracion(actividad.getTotalDuracionMinutes()), false);
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

    public static int generarID() {
        Random random = new Random();
        return random.nextInt();
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
}
