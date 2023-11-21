package estructura.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public static void insertarUsuario(Usuario usuario) {
        List<Usuario> usuarios = obtenerUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            usuarios = (List<Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, se creará cuando se agregue el primer usuario
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static boolean usuarioRegistrado(String nombreUsuario) {
        List<Usuario> usuarios = obtenerUsuarios();
        return usuarios.stream().anyMatch(u -> u.getNombreUsuario().equals(nombreUsuario));
    }

    private static void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
