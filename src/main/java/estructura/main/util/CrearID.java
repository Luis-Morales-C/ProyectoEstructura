package estructura.main.util;

import java.util.UUID;

public class CrearID {
    /**
     * Genera un identificador único como cadena de texto.
     *
     * @return Una cadena de texto que representa un identificador único.
     */
    public static String generarID() {
        return UUID.randomUUID().toString();
    }
}

