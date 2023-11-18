package estructura.main.modelos;

/**
 * Enumeración para las opciones de estado de una tarea.
 */
public enum EstadoTarea {
    OBLIGATORIO("Obligatoria"),
    OPTIONAL("Opcional"),
    COMPLETO("Completada");


    private final String status;

    /**
     * Constructor de EstadoTarea.
     *
     * @param status El estado de la tarea.
     */
    EstadoTarea(String status) {
        this.status = status;
    }

    /**
     * Obtiene una representación en cadena del estado.
     *
     * @return El estado en forma de cadena.
     */
    @Override
    public String toString() {
        return this.status;
    }
}
