package estructura.main.nodos;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Custom implementation of a doubly linked list.
 * It provides methods to add, remove, and iterate over elements.
 *
 * @param <E> the type of elements in this list
 */
public class ListaDobleEnlazada<E> {
    // Reference to the head node of the list
    private Node<E> head;

    // Reference to the tail node of the list
    private Node<E> tail;

    // The size of the list
    private int size;

    /**
     * A node within the doubly linked list.
     * This inner class is static because it does not need access to the instance variables of ListaDobleEnlazada.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        /**
         * Constructor for creating a new node with previous and next node references.
         *
         * @param element the element to store in this node
         * @param prev    the previous node in the list
         * @param next    the next node in the list
         */
        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Constructor for creating a new node with no element and no next and previous node references.
         */
        Node() {
            this(null, null, null);
        }
    }
    public ListaDobleEnlazada() {
        head = new Node<>();
        tail = new Node<>(null, head, null);
        head.next = tail;
        size = 0;
    }

    /**
     * Adds an element to the beginning of the list.
     *
     * @param element the element to add
     */
    public void addFirst(E element) {
        addBetween(element, head, head.next);
    }


    public void addLast(E element) {
        addBetween(element, tail.prev, tail);
    }
    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(head.next);
    }
    public E removeLast() {
        if (isEmpty()) return null;
        return remove(tail.prev);
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }


    /**
     * Finds the first element that matches the given predicate.
     *
     * @param predicate a predicate to apply to each element to determine if it should be returned
     * @return the first matching element, or null if no element matches
     */
    public E findFirst(java.util.function.Predicate<E> predicate) {
        Node<E> current = head.next;
        while (current != tail) {
            if (predicate.test(current.element)) {
                return current.element;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Performs the given action for each element of the list until all elements have been processed or the action throws an exception.
     * The order of iteration is determined by the reverse parameter.
     *
     * @param action  the action to be performed for each element
     * @param reverse if true, the list is iterated in reverse order
     */
    public void forEach(Consumer<E> action, boolean reverse) {
        if (reverse) {
            Node<E> current = tail.prev; // Comenzamos por el final
            while (current != head) { // Mientras no lleguemos al nodo ficticio de cabeza
                action.accept(current.element);
                current = current.prev;
            }
        } else {
            Node<E> current = head.next; // Comenzamos por el principio
            while (current != tail) { // Mientras no lleguemos al nodo ficticio de cola
                action.accept(current.element);
                current = current.next;
            }
        }
    }

    /**
     * Removes the first element that matches the given predicate.
     *
     * @param predicate a predicate to apply to each element to determine if it should be removed
     * @return true if an element was removed, false otherwise
     */
    public boolean removeIf(java.util.function.Predicate<E> predicate) {
        Node<E> current = head.next;
        while (current != tail) {
            if (predicate.test(current.element)) {
                remove(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Converts this ListaDobleEnlazada to a standard List.
     * @return A standard List containing the elements of the ListaDobleEnlazada.
     */
    public List<E> toList() {
        List<E> list = new ArrayList<>();
        this.forEach(list::add,false);
        return list;
    }
    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        // Crear y enlazar un nuevo nodo entre el predecesor y el sucesor
        Node<E> newNode = new Node<>(element, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }
    private E remove(Node<E> node) {
        Node<E> predecessor = node.prev;
        Node<E> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        return node.element;
    }

    /**
     * Converts a standard List to a ListaDobleEnlazada.
     *
     * @param list The standard List to convert.
     * @param <E>  The type of elements in the List.
     * @return A ListaDobleEnlazada containing the elements of the List.
     */
    public static <E> ListaDobleEnlazada<E> fromList(List<E> list) {
        ListaDobleEnlazada<E> customList = new ListaDobleEnlazada<>();
        for (E element : list) {
            customList.addLast(element);
        }
        return customList;
    }

}
