package estructura.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa una tarea dentro de un proceso o actividad.
 */
public class Tarea implements Serializable {
    private String descripcion;
    private Estado estado;
    private int duracionMinutos;
    private LocalDateTime inicio;
    private LocalDateTime fin;


    /**
     * Constructor para crear una nueva instancia de Tarea.
     *
     * @param descripcion      Una cadena que describe la tarea.
     * @param estado           El estado de la tarea, utilizando el enum EstadoTarea.
     * @param duracionMinutos  La duración estimada de la tarea en minutos.
     */
    public Tarea(String descripcion, Estado estado, int duracionMinutos) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.duracionMinutos = duracionMinutos;
    }
    public Tarea(){
        super();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Marca la tarea como completada.
     */
    public void completarTarea() {
        setEstado(Estado.COMPLETO);
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", duracionMinutos=" + duracionMinutos +
                '}';
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public void iniciarTarea() {
        this.inicio = LocalDateTime.now();
    }

    public void finalizarTarea() {
        this.fin = LocalDateTime.now();
    }

    public int calcularDuracion() {
        return (int) inicio.until(fin, java.time.temporal.ChronoUnit.MINUTES);
    }
}
