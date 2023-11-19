package estructura.persistencia;


import estructura.model.*;

import java.io.*;
import java.util.*;

public class Persistencia {
    public static final String RUTA_ARCHIVO_PROCESOS= "src/main/resources/persistencia/procesos.txt";
    public static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/persistencia/actividades.txt";
    public static final String RUTA_ARCHIVO_TAREAS = "src/main/resources/persistencia/tareas.txt";


    public static void guardarProcesos(ListaDobleEnlazada<Proceso> listaProcesos) throws IOException {
        String contenido = "";

        for(Proceso proceso: listaProcesos) {
            contenido += proceso.getId()+"@@"+proceso.getNombre()+"@@"+proceso.getActividades()+"\n";
        }
        guardarArchivo(RUTA_ARCHIVO_PROCESOS, contenido);
    }

    public static void guardarActividades(ArrayList<Actividad> listaActividades) throws IOException {
        String contenido = "";

        for(Actividad actividad: listaActividades) {
            contenido += actividad.getNombre()+"@@"+actividad.getDescripcion()+"@@"+actividad.getEstado()+"\n";
        }
        guardarArchivo(RUTA_ARCHIVO_ACTIVIDADES, contenido);
    }

    public static void guardarTareas(ArrayList<Tarea> listaTareas) throws IOException {
        String contenido = "";

        for(Tarea tarea: listaTareas) {
            contenido += tarea.getDescripcion()+"@@"+tarea.getDescripcion()+"@@"+tarea.getDuracionMinutos()+"\n";
        }
        guardarArchivo(RUTA_ARCHIVO_TAREAS, contenido);
    }

    public static void guardarArchivo(String ruta,String contenido) throws IOException {
        FileWriter fw = new FileWriter(ruta, false); //false para no append
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static ArrayList<Proceso> cargarProceso() throws IOException {
        ArrayList<Proceso> procesos = new ArrayList<>();

        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_PROCESOS);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Proceso proceso=new Proceso();

            Actividad actividad=new Actividad(linea.split("@@")[2],linea.split("@@")[3],Estado.valueOf(linea.split("@@")[4]));
            proceso.setId(linea.split("@@")[0]);
            proceso.setNombre(linea.split("@@")[1]);

            ListaDobleEnlazada<Actividad> actividades=new ListaDobleEnlazada<>();
            actividades.agregarUltimo(actividad);
            proceso.setActividades(actividades);

            procesos.add(proceso);

        }
        return procesos;
    }

    public static ArrayList<Actividad> cargarActividades() throws IOException {
        ArrayList<Actividad> actividades = new ArrayList<>();

        ArrayList<String> contenido = leerArchivo(RUTA_ARCHIVO_ACTIVIDADES);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Actividad actividad=new Actividad();
            Tarea tarea=new Tarea(linea.split("@@")[3], Estado.valueOf(linea.split("@@")[4]),
                    Integer.parseInt(linea.split("@@")[5]));

            ListaDobleEnlazada<Tarea> tareas = new ListaDobleEnlazada<>();
            tareas.agregarUltimo(tarea);

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
        String linea="";

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

        while((linea = bfr.readLine())!=null)
            contenido.add(linea);

        bfr.close();
        fr.close();
        return contenido;
    }


}
