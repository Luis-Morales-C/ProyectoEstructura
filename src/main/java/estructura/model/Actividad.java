package estructura.main.modelos;

import estructura.main.nodos.Cola;
import estructura.main.util.CrearID;

import java.util.List;

/**
 * La clase Actividades representa una actividad en el contexto del proyecto.
 * Una actividad tiene tareas pendientes y completadas, así como métodos para
 * gestionarlas y obtener información sobre la actividad.
 */
public class Actividades {
    private String id;
    private String nombre;
    private Cola<Tarea> tareasPendientes; // Cola de tareas pendientes
    private Cola<Tarea> completarTarea; // Cola de tareas completadas

    /**
     * Constructor de la clase Actividades.
     *
     * @param nombre El nombre de la actividad.
     */
    public Actividades(String nombre) {
        this.id = CrearID.generarID(); // Genera un ID único para la actividad
        this.nombre = nombre;
        this.tareasPendientes = new Cola<>();
        this.completarTarea = new Cola<>();
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

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTareasPendientes(Cola<Tarea> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public void setCompletarTarea(Cola<Tarea> completarTarea) {
        this.completarTarea = completarTarea;
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
     * Obtiene el ID de la actividad.
     *
     * @return El ID de la actividad.
     */
    public String getId() {
        return id;
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


}
