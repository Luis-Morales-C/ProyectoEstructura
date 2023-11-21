package estructura.model;

import estructura.model.Cola;
import estructura.model.Tarea;

import java.util.List;

public class Actividad {
    private String id;
    private String nombre;
    private Cola<Tarea> tareasPendientes;
    private Cola<Tarea> completarTarea;
    private String descripcion;
    private boolean obligatoria;
    private Estado estado;
    private ListaDobleEnlazada<Tarea> listaTarea;

    public Actividad(String nombre) {
        this.nombre = nombre;
        this.tareasPendientes = new Cola<>();
        this.completarTarea = new Cola<>();
        this.obligatoria = false;
    }

    public Actividad() {
        // Constructor por defecto
    }

    public Actividad(String s, String s1, Estado estado) {

    }

    public void addTarea(Tarea tarea) {
        tareasPendientes.enqueue(tarea);
    }

    public Tarea completarTarea() {
        Tarea tareaCompletada = tareasPendientes.dequeue();
        if (tareaCompletada != null) {
            completarTarea.enqueue(tareaCompletada);
            tareaCompletada.completarTarea();
        }
        return tareaCompletada;
    }

    // Resto de los métodos sin cambios

    public int getTotalDuracionMinutes() {
        int totalDuration = 0;
        totalDuration += calcularDuracion(tareasPendientes);
        totalDuration += calcularDuracion(completarTarea);
        return totalDuration;
    }

    private int calcularDuracion(Cola<Tarea> cola) {
        int totalDuration = 0;
        for (Tarea tarea : cola.toList()) {
            totalDuration += tarea.getDuracionMinutos();
        }
        return totalDuration;
    }

    public ListaDobleEnlazada<Tarea> getListaTarea() {
        return null;
    }

    public void setNombre(String s) {

    }

    public List<Tarea> getPendingTasksAsList() {
        return null;
    }

    public void setDescripcion(String s) {

    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setListaTarea(ListaDobleEnlazada<Tarea> listaTarea) {
        this.listaTarea = listaTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return null;
    }

    public void setObligatoria(boolean obligatoria) {

    }
}
