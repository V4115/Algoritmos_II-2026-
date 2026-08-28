package guia.i02_memoria.i04_dificil.i01;

/**
 * 1. Lista enlazada simple. Nodo(int valor, Nodo siguiente) y una ListaEnlazada con agregar,
tamanio y aArreglo; test.
 */

public class LinkedList {
    Node head;
    int size;

    // Linked List Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Add a new node to the end of the linked list
    public void add(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.nextNode != null) {
                current = current.nextNode;
            }
            current.nextNode = newNode;
        }
        size++;
    }
    
    // Get the size of the linked list
    public int getSize() {
        return size;
    }

    // Convert the linked list to an array
    public int[] toArray() {
        int[] array = new int[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.value;
            current = current.nextNode;
        }
        return array;
    }
}