package estructura.main.modelos;

/**
 * Representa una tarea dentro de un proceso o actividad.
 */
public class Tarea {
    private String descripcion;
    private EstadoTarea estado;
    private int duracionMinutos;

    /**
     * Constructor para crear una nueva instancia de Tarea.
     *
     * @param descripcion      Una cadena que describe la tarea.
     * @param estado           El estado de la tarea, utilizando el enum EstadoTarea.
     * @param duracionMinutos  La duración estimada de la tarea en minutos.
     */
    public Tarea(String descripcion, EstadoTarea estado, int duracionMinutos) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.duracionMinutos = duracionMinutos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
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
        setEstado(EstadoTarea.COMPLETO);
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", duracionMinutos=" + duracionMinutos +
                '}';
    }
}
