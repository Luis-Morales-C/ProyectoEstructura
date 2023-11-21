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

<<<<<<< HEAD
    public void setTareas(ListaDobleEnlazada<Tarea> tareas) {
        this.tareas = tareas;
=======
    public int getNumActividades() {
        return getActividades().getSize();
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

    public int getTiempoTotalProceso() {
        int tiempoTotalProceso = 0;
        for (Actividad actividad : listaActividades) {
            tiempoTotalProceso += Integer.parseInt(actividad.getTiempoMaximo());
        }
        return tiempoTotalProceso;
    }

    public void incrementarNumActividades() {
        this.numActividades++;
    }

    public void decrementarNumActividades() {
        this.numActividades--;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return this == o;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + numActividades;
        result = 31 * result + (listaActividades != null ? listaActividades.hashCode() : 0);
        result = 31 * result + duracionTotal;
        return result;
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
    }
}
