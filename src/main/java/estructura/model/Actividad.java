package estructura.model;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
public class Actividad implements Serializable {
    private String nombre;
    private String descripcion;
    private String obligatoria;
    private Cola<Tarea> tareasPendientes; // Cola de tareas pendientes
    private Cola<Tarea> completarTarea; // Cola de tareas completadas

    public Actividad(String nombre,String descripcion,String obligatoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.tareasPendientes = new Cola<>();
        this.completarTarea = new Cola<>();
    }
    public Actividad(){
        super();
    }

    public void addTarea(Tarea tarea) {
        tareasPendientes.enqueue(tarea);
    }

    public Tarea completetarTarea() {
        Tarea completetarTarea = tareasPendientes.dequeue();
        if (completetarTarea != null) {
            this.completarTarea.enqueue(completetarTarea);
            completetarTarea.completarTarea();
        }
        return completetarTarea;
    }

    public Cola<Tarea> getTareasPendientes() {
        return tareasPendientes;
    }

    public Cola<Tarea> getCompletarTarea() {
        return completarTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getPendingTasksAsList() {
        return tareasPendientes.toList();
    }

    public List<Tarea> getCompletedTasksAsList(){
            return completarTarea.toList();
    }

    public int getTotalDuracionMinutes() {
        int totalDuration = 0;
        for (Tarea tarea : tareasPendientes.toList()) totalDuration += tarea.getDuracionMinutos();

        for (Tarea tarea : completarTarea.toList()) totalDuration += tarea.getDuracionMinutos();

        return totalDuration;
    }
    public String calcularEstado(CheckBox estado){
        if(estado.isSelected()){
            return "OBLIGATORIO";
        }
        else{
            return "NO OBLIGATORIO";
        }
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

    public String isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(String obligatoria) {
        this.obligatoria = obligatoria;
    }

    public void setTareasPendientes(Cola<Tarea> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public void setCompletarTarea(Cola<Tarea> completarTarea) {
        this.completarTarea = completarTarea;
    }

}
