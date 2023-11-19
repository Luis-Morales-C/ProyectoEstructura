package estructura.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListaDobleEnlazada<E> implements Iterable<E> {

    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int tamaño;

    private static class Nodo<E> {
        E elemento;
        Nodo<E> siguiente;
        Nodo<E> anterior;

        /**
         * Constructor para crear un nuevo nodo con referencias de nodo anterior y siguiente.
         *
         * @param elemento el elemento a almacenar en este nodo
         * @param anterior el nodo anterior en la lista
         * @param siguiente el siguiente nodo en la lista
         */
        Nodo(E elemento, Nodo<E> anterior, Nodo<E> siguiente) {
            this.elemento = elemento;
            this.anterior = anterior;
            this.siguiente = siguiente;
        }

        /**
         * Constructor para crear un nuevo nodo sin elemento y sin referencias de nodo siguiente y anterior.
         */
        Nodo() {
            this(null, null, null);
        }
    }

    public ListaDobleEnlazada() {
        cabeza = new Nodo<>();
        cola = new Nodo<>(null, cabeza, null);
        cabeza.siguiente = cola;
        tamaño = 0;
    }

    /**
     * Agrega un elemento al principio de la lista.
     *
     * @param elemento el elemento a agregar
     */
    public void agregarPrimero(E elemento) {
        agregarEntre(elemento, cabeza, cabeza.siguiente);
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elemento el elemento a agregar
     */
    public void agregarUltimo(E elemento) {
        agregarEntre(elemento, cola.anterior, cola);
    }

    /**
     * Elimina el primer elemento de la lista.
     *
     * @return el elemento eliminado, o null si la lista está vacía
     */
    public E eliminarPrimero() {
        if (estaVacia()) return null;
        return eliminar(cabeza.siguiente);
    }

    /**
     * Elimina el último elemento de la lista.
     *
     * @return el elemento eliminado, o null si la lista está vacía
     */
    public E eliminarUltimo() {
        if (estaVacia()) return null;
        return eliminar(cola.anterior);
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }

    /**
     * Obtiene el tamaño de la lista.
     *
     * @return el tamaño de la lista
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Encuentra el primer elemento que coincide con el predicado dado.
     *
     * @param predicado un predicado para aplicar a cada elemento y determinar si debe ser devuelto
     * @return el primer elemento que coincide, o null si no hay coincidencia
     */
    public E encontrarPrimero(java.util.function.Predicate<E> predicado) {
        Nodo<E> actual = cabeza.siguiente;
        while (actual != cola) {
            if (predicado.test(actual.elemento)) {
                return actual.elemento;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    /**
     * Realiza la acción dada para cada elemento de la lista hasta que se procesen todos los elementos
     * o la acción lance una excepción. El orden de iteración está determinado por el parámetro reverse.
     *
     * @param acción   la acción a realizar para cada elemento
     * @param reverso si es true, la lista se itera en orden inverso
     */
    public void forEach(Consumer<E> acción, boolean reverso) {
        if (reverso) {
            Nodo<E> actual = cola.anterior; // Comenzamos por el final
            while (actual != cabeza) { // Mientras no lleguemos al nodo ficticio de cabeza
                acción.accept(actual.elemento);
                actual = actual.anterior;
            }
        } else {
            Nodo<E> actual = cabeza.siguiente; // Comenzamos por el principio
            while (actual != cola) { // Mientras no lleguemos al nodo ficticio de cola
                acción.accept(actual.elemento);
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Elimina el primer elemento que coincide con el predicado dado.
     *
     * @param predicado un predicado para aplicar a cada elemento y determinar si debe ser eliminado
     * @return true si se eliminó un elemento, false en caso contrario
     */
    public boolean eliminarSi(java.util.function.Predicate<E> predicado) {
        Nodo<E> actual = cabeza.siguiente;
        while (actual != cola) {
            if (predicado.test(actual.elemento)) {
                eliminar(actual);
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Convierte esta ListaDobleEnlazada a una lista estándar.
     *
     * @return Una lista estándar que contiene los elementos de la ListaDobleEnlazada.
     */
    public List<E> aLista() {
        List<E> lista = new ArrayList<>();
        this.forEach(lista::add, false);
        return lista;
    }

    private void agregarEntre(E elemento, Nodo<E> predecesor, Nodo<E> sucesor) {
        // Crear y enlazar un nuevo nodo entre el predecesor y el sucesor
        Nodo<E> nuevoNodo = new Nodo<>(elemento, predecesor, sucesor);
        predecesor.siguiente = nuevoNodo;
        sucesor.anterior = nuevoNodo;
        tamaño++;
    }

    private E eliminar(Nodo<E> nodo) {
        Nodo<E> predecesor = nodo.anterior;
        Nodo<E> sucesor = nodo.siguiente;
        predecesor.siguiente = sucesor;
        sucesor.anterior = predecesor;
        tamaño--;
        return nodo.elemento;
    }

    /**
     * Convierte una lista estándar a una ListaDobleEnlazada.
     *
     * @param lista La lista estándar a convertir.
     * @param <E>   El tipo de elementos en la lista.
     * @return Una ListaDobleEnlazada que contiene los elementos de la lista.
     */
    public static <E> ListaDobleEnlazada<E> desdeLista(List<E> lista) {
        ListaDobleEnlazada<E> listaPersonalizada = new ListaDobleEnlazada<>();
        for (E elemento : lista) {
            listaPersonalizada.agregarUltimo(elemento);
        }
        return listaPersonalizada;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListaDobleEnlazadaIterator();
    }

    /**
     * Clase interna que implementa el iterador para la lista doblemente enlazada.
     */
    private class ListaDobleEnlazadaIterator implements Iterator<E> {
        private Nodo<E> actual = cabeza.siguiente; // Comenzamos desde el primer elemento

        @Override
        public boolean hasNext() {
            return actual != cola;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E elemento = actual.elemento;
            actual = actual.siguiente;
            return elemento;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove no está soportado por este iterador");
        }
    }
}
