package TablaOrdenada;

import java.util.Iterator;

/**
 * Implementación de una cola genérica (estructura FIFO) utilizando una lista enlazada.
 * 
 * <p>Los elementos se encolan al final y se desencolan desde el inicio. 
 * Soporta operaciones básicas como {@code enqueue}, {@code dequeue}, 
 * {@code isEmpty}, {@code size}, y permite iteración en orden FIFO.</p>
 * 
 * @param <Item> el tipo de elementos que esta cola almacenará
 * 
 * @author piped
 */
public class Queue<Item> implements Iterable<Item> {

    /** Referencia al primer nodo de la cola (frente). */
    private Node first;
    
    /** Referencia al último nodo de la cola (final). */
    private Node last;
    
    /** Número de elementos en la cola. */
    private int count;

    /**
     * Clase interna privada que representa un nodo en la lista enlazada.
     */
    private class Node {
        Item item;
        Node next;
    }

    /**
     * Inserta un nuevo elemento al final de la cola.
     *
     * @param item el elemento que se desea encolar
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        count++;
    }

    /**
     * Elimina y devuelve el primer elemento de la cola.
     *
     * @return el elemento desencolado, o {@code null} si la cola está vacía
     */
    public Item dequeue() {
        if (isEmpty())
            return null;
        Item item = first.item;
        first.item = null; // ayuda a evitar loitering
        first = first.next;
        if (isEmpty())
            last = null;
        count--;
        return item;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return {@code true} si la cola no contiene elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Devuelve la cantidad de elementos en la cola.
     *
     * @return el número de elementos almacenados
     */
    public int size() {
        return count;
    }

    /**
     * Devuelve un iterador que recorre los elementos de la cola en orden FIFO.
     *
     * @return un iterador para la cola
     */
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    /**
     * Clase interna privada que implementa un iterador sobre la cola.
     */
    private class QueueIterator implements Iterator<Item> {

        /** Nodo actual durante la iteración. */
        private Node current = first;

        /**
         * Verifica si hay un siguiente elemento en la cola.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Devuelve el siguiente elemento de la cola.
         *
         * @return el siguiente elemento en la cola
         */
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
