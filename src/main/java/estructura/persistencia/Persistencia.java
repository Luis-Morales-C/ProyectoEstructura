package estructura.persistencia;


import estructura.model.*;

import java.io.*;
import java.util.*;

public class Persistencia {
    public static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/persistencia/procesos.txt";
    public static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/persistencia/actividades.txt";
    public static final String RUTA_ARCHIVO_TAREAS = "src/main/resources/persistencia/tareas.txt";
    ;
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/usuarios.txt";


    public static void guardarProcesos(ArrayList<Proceso> listaProcesos) throws IOException {
        String contenido = "";
<<<<<<< HEAD

        for(Proceso proceso: listaProcesos) {
            contenido += proceso.getId()+"@@"+proceso.getNombre()+"@@"+proceso.getActividades()+"\n";
=======
        for (Proceso proceso : listaProcesos) {
            contenido += proceso.getId() + "@@" + proceso.getNombre() + "@@" + proceso.getNumActividades() + "\n";
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        }
        guardarArchivo(RUTA_ARCHIVO_PROCESOS, contenido);
    }

    public static void guardarActividades(ArrayList<Actividad> listaActividades) throws IOException {
        String contenido = "";
<<<<<<< HEAD

        for(Actividad actividad: listaActividades) {
            contenido += actividad.getNombre()+"@@"+actividad.getDescripcion()+"@@"+actividad.getEstado()+"\n";
=======
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            for (Actividad actividad : procesoActual.getActividades()) {
                contenido += procesoActual.getNombre() + "@@" + actividad.getNombre() + "@@" + actividad.getDescripcion() + "@@" + actividad.isObligatoria() + "\n";
            }
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        }
        guardarArchivo(RUTA_ARCHIVO_ACTIVIDADES, contenido);
    }

    public static void guardarTareas(ArrayList<Tarea> listaTareas) throws IOException {
        String contenido = "";

<<<<<<< HEAD
        for(Tarea tarea: listaTareas) {
            contenido += tarea.getDescripcion()+"@@"+tarea.getDescripcion()+"@@"+tarea.getDuracionMinutos()+"\n";
=======
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while (iterator.hasNext()) {
            Proceso procesoActual = iterator.next();
            for (Actividad actividad : procesoActual.getActividades()) {
                for (Tarea tarea : actividad.getTareasPendientes().toList()) {
                    contenido += actividad.getProceso() + "@@" + actividad.getNombre() + "@@" + tarea.getDescripcion() + "@@" + tarea.getEstado() + "@@" + tarea.getDuracionMinutos() + "\n";
                }
            }
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        }
        guardarArchivo(RUTA_ARCHIVO_TAREAS, contenido);
    }

    public static void guardarArchivo(String ruta, String contenido) throws IOException {
        FileWriter fw = new FileWriter(ruta, false); //false para no append
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

<<<<<<< HEAD
    public static ArrayList<Proceso> cargarProceso() throws IOException {
        ArrayList<Proceso> procesos = new ArrayList<>();

=======
    private static void guardarUsuarios(List<Usuario> listaUsuarios) throws IOException {
        String contenido = "";
        for (Usuario usuario : listaUsuarios) {
            contenido += usuario.getRol() + "@@" + usuario.getUser() + "@@" + usuario.getPassword() + "\n";
        }
        guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido);
    }

    public static List<Usuario> cargarUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_USUARIOS);

        for (String linea : contenido) {
            if (!linea.isEmpty()) {
                String[] partes = linea.split("@@");
                Usuario usuario = new Usuario();
                usuario.setRol(partes[0]);
                usuario.setUser(partes[1]);
                usuario.setPassword(partes[2]);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public static ListaDobleEnlazada<Proceso> cargarProceso() throws IOException {
        ListaDobleEnlazada<Proceso> procesos = new ListaDobleEnlazada<>();
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_PROCESOS);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Proceso proceso=new Proceso();

            Actividad actividad=new Actividad(linea.split("@@")[2],linea.split("@@")[3],Estado.valueOf(linea.split("@@")[4]));
            proceso.setId(linea.split("@@")[0]);
            proceso.setNombre(linea.split("@@")[1]);

            ListaDobleEnlazada<Actividad> actividades=new ListaDobleEnlazada<>();
            actividades.addLast(actividad);
            proceso.setActividades(actividades);

            procesos.add(proceso);

        }
        return procesos;
    }

<<<<<<< HEAD
    public static ArrayList<Actividad> cargarActividades() throws IOException {
        ArrayList<Actividad> actividades = new ArrayList<>();

        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_ACTIVIDADES);
        String linea="";

=======
    public static ListaDobleEnlazada<Actividad> cargarActividades() throws IOException {
        ListaDobleEnlazada<Actividad> actividades = new ListaDobleEnlazada<>();
        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_ACTIVIDADES);
        String linea = "";
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Actividad actividad=new Actividad();
            Tarea tarea=new Tarea(linea.split("@@")[3], Estado.valueOf(linea.split("@@")[4]),
                    Integer.parseInt(linea.split("@@")[5]));

            ListaDobleEnlazada<Tarea> tareas = new ListaDobleEnlazada<>();
            tareas.addLast(tarea);

            actividad.setNombre(linea.split("@@")[0]);
            actividad.setDescripcion(linea.split("@@")[1]);
            actividad.setEstado(Estado.valueOf(linea.split("@@")[2]));

            actividad.setListaTarea(tareas);

            actividades.add(actividad);

        }
        return actividades;
    }

    public static ArrayList<Tarea> cargarTareas() throws IOException {
        ArrayList<Tarea> tareas = new ArrayList<>();

        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_TAREAS);
<<<<<<< HEAD
        String linea="";

=======
        String linea = "";
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Tarea tarea=new Tarea();


            tarea.setDescripcion(linea.split("@@")[0]);
            tarea.setEstado(Estado.valueOf(linea.split("@@")[1]));
            tarea.setDuracionMinutos(Integer.parseInt(linea.split("@@")[2]));

           tareas.add(tarea);
        }
        return tareas;
    }


    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<String>();
        FileReader fr = new FileReader(ruta);
        BufferedReader bfr = new BufferedReader(fr);
        String linea = "";

        while ((linea = bfr.readLine()) != null)
            contenido.add(linea);

        bfr.close();
        fr.close();
        return contenido;
    }

<<<<<<< HEAD

    public static void guardarActividades(List<Proceso> listaProcesos, List<Actividad> actividads) {

=======
    public static void cargarDatos(Aplicacion aplicacion) throws IOException {
        ListaDobleEnlazada<Proceso> procesos = cargarProceso();
        ListaDobleEnlazada<Actividad> actividades = cargarActividades();
        ListaDobleEnlazada<Tarea> tareas = cargarTareas();
        List<Usuario> usuarios = cargarUsuarios();

        Iterator<Proceso> procesoIterator = procesos.iterator();
        while (procesoIterator.hasNext()) {
            Proceso procesoActual = procesoIterator.next();
            Iterator<Actividad> actividadIterator = actividades.iterator();
            while (actividadIterator.hasNext()) {
                Actividad actividadActual = actividadIterator.next();
                if (procesoActual.getNombre().equals(actividadActual.getProceso())) {
                    procesoActual.getActividades().agregarUltimo(actividadActual);
                    Iterator<Tarea> tareaIterator = tareas.iterator();
                    while (tareaIterator.hasNext()) {
                        Tarea tareaActual = tareaIterator.next();
                        if (actividadActual.getNombre().equals(tareaActual.getActividad())) {
                            actividadActual.getTareasPendientes().enqueue(tareaActual);
                        }
                    }
                }
            }
        }
        aplicacion.setListaUsuarios(usuarios);
        aplicacion.setListaProcesos(procesos);
>>>>>>> b68463c175c89b8725aaba06abe66fc722b30441
    }
}
