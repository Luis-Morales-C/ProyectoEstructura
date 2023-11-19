package estructura.model;



import java.util.List;
import java.util.Random;

/**
 * La clase Actividades representa una actividad en el contexto del proyecto.
 * Una actividad tiene tareas pendientes y completadas, así como métodos para
 * gestionarlas y obtener información sobre la actividad.
 */
public class Actividad {
    private String nombre;
    private String descripcion;
    private Estado estado;
    private ListaDobleEnlazada<Tarea> listaTarea;
    private Cola<Tarea> tareasPendientes; // Cola de tareas pendientes
    private Cola<Tarea> completarTarea; // Cola de tareas completadas

    /**
     * Constructor de la clase Actividades.
     *
     * @param nombre El nombre de la actividad.
     */
    public Actividad(String nombre,String descripcion,Estado estado) {
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.estado=estado;
        this.tareasPendientes = new Cola<>();
        this.completarTarea = new Cola<>();
    }
    public Actividad(){
        super();
    }

    /**
     * Agrega una tarea a la lista de tareas pendientes.
     *
     * @param tarea La tarea a agregar.
     */
    public void addTarea(Tarea tarea) {
        tareasPendientes.enqueue(tarea);
    }

    /**
     * Completa la tarea más antigua de la lista de tareas pendientes.
     *
     * @return La tarea completada, o null si no hay tareas pendientes.
     */
    public Tarea completetarTarea() {
        Tarea completetarTarea = tareasPendientes.dequeue();
        if (completetarTarea != null) {
            this.completarTarea.enqueue(completetarTarea);
            completetarTarea.completarTarea();
        }
        return completetarTarea;
    }

    /**
     * Obtiene la cola de tareas pendientes.
     *
     * @return La cola de tareas pendientes.
     */
    public Cola<Tarea> getTareasPendientes() {
        return tareasPendientes;
    }

    /**
     * Obtiene la cola de tareas completadas.
     *
     * @return La cola de tareas completadas.
     */
    public Cola<Tarea> getCompletarTarea() {
        return completarTarea;
    }


    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene todas las tareas pendientes en forma de lista.
     *
     * @return Lista de tareas pendientes.
     */
    public List<Tarea> getPendingTasksAsList() {
        return tareasPendientes.toList();
    }

    /**
     * Obtiene todas las tareas completadas en forma de lista.
     *
     * @return Lista de tareas completadas.
     */
    public List<Tarea> getCompletedTasksAsList() {
        return completarTarea.toList();
    }

    /**
     * Calcula y devuelve la duración total de la actividad.
     *
     * @return La duración total en minutos de todas las tareas (pendientes y completadas).
     */
    public int getTotalDuracionMinutes() {
        int totalDuration = 0;
        for (Tarea tarea : tareasPendientes.toList()) totalDuration += tarea.getDuracionMinutos();

        for (Tarea tarea : completarTarea.toList()) totalDuration += tarea.getDuracionMinutos();

        return totalDuration;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTareasPendientes(Cola<Tarea> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public void setCompletarTarea(Cola<Tarea> completarTarea) {
        this.completarTarea = completarTarea;
    }

    public ListaDobleEnlazada<Tarea> getListaTarea() {
        return listaTarea;
    }

    public void setListaTarea(ListaDobleEnlazada<Tarea> listaTarea) {
        this.listaTarea = listaTarea;
    }
}
