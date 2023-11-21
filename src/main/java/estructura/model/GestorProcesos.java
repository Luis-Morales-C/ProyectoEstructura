package estructura.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona los procesos de la aplicación.
 */
public class GestorProcesos {
    // Única instancia de GestorProcesos
    private static GestorProcesos instance;

    // Mapa para almacenar los procesos
    private Map<String, Proceso> procesos;

    /**
     * Constructor privado para prevenir la instanciación desde fuera.
     */
    private GestorProcesos() {
        procesos = new HashMap<>();
    }

    /**
     * Método para obtener la única instancia de la clase.
     *
     * @return la única instancia de GestorProcesos.
     */
    public static GestorProcesos getInstance() {
        if (instance == null) {
            instance = new GestorProcesos();
        }
        return instance;
    }

    /**
     * Añade un proceso al gestor.
     *
     * @param process el proceso a añadir.
     */
    public void addProcess(Proceso process) {
        procesos.put(process.getId(), process);
    }

    /**
     * Obtiene un proceso por su ID.
     *
     * @param id el ID del proceso.
     * @return el proceso correspondiente, o null si no se encuentra.
     */
    public Proceso getProcess(String id) {
        return procesos.get(id);
    }

    /**
     * Elimina un proceso por su ID.
     *
     * @param id el ID del proceso a eliminar.
     * @return el proceso eliminado, o null si no se encuentra.
     */
    public Proceso removeProcess(String id) {
        return procesos.remove(id);
    }
}
