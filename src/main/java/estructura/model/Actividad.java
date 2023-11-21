package estructura.model;

import estructura.model.Cola;
import estructura.model.Tarea;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Random;
public class Actividad implements Serializable {
    private String nombre;
    private String descripcion;
    private String obligatoria;
    private String proceso;
    private String tareas;
    private String tiempoMinimo;
    private String tiempoMaximo;
    private Cola<Tarea> tareasPendientes = new Cola<>(); // Cola de tareas pendientes
    private Cola<Tarea> completarTarea = new Cola<>(); // Cola de tareas completadas
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441

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

<<<<<<< HEAD
    public Tarea completarTarea() {
        Tarea tareaCompletada = tareasPendientes.dequeue();
        if (tareaCompletada != null) {
            completarTarea.enqueue(tareaCompletada);
            tareaCompletada.completarTarea();
=======
    public String getObligatoria() {
        return obligatoria;
    }

    public String getTareas() {
        String tareas = "";
        for(Tarea tarea: getTareasPendientes().toList()) {
            tareas += tarea.getDescripcion()+", ";
        }
        return tareas;
    }

    public void setTareas(String tareas) {
        this.tareas = tareas;
    }

    public String getTiempoMinimo() {
        int tiempo = 0;
        for(Tarea tarea: getTareasPendientes().toList()) {
            Estado e = tarea.getEstado();
            if(e.equals(Estado.OBLIGATORIO))
                tiempo += tarea.getDuracionMinutos();
        }
        return tiempo+"";
    }

    public void setTiempoMinimo(String tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public String getTiempoMaximo() {
        int tiempo = 0;
        for(Tarea tarea: getTareasPendientes().toList()) {
            tiempo += tarea.getDuracionMinutos();
        }
        return tiempo+"";
    }

    public void setTiempoMaximo(String tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public Tarea completetarTarea() {
        Tarea completetarTarea = tareasPendientes.dequeue();
        if (completetarTarea != null) {
            this.completarTarea.enqueue(completetarTarea);
            completetarTarea.completarTarea();
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
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
