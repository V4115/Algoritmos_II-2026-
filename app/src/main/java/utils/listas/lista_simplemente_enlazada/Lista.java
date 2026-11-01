package utils.listas.lista_simplemente_enlazada;

/**
 * TDA <b>Lista</b>: una secuencia de elementos con posición (índice 0..n−1).
 * Es el <b>contrato</b>; puede implementarse con un arreglo (vector dinámico) o
 * con nodos enlazados, y quien la usa no debería notar la diferencia.
 *
 * @param <T> tipo de los elementos
 */
public interface Lista<T>{

    /**
     * Agrega un elemento al final de la lista.
     * @param dato elemento a agregar.
     */
    void agregar(T dato);

    /**
     * Agrega un elemento al inicio de la lista.
     * @param dato elemento a agregar.
     */
    void agregarAlInicio(T dato);

    /**
     * Inserta un elemento en la posición especificada.
     * @param indice posición donde insertar el elemento.
     * @param dato elemento a insertar.
     */
    void insertar(int indice, T dato);

    /**
     * Devuelve el elemento en la posición especificada.
     * @param indice posición del elemento a devolver.
     * @return elemento en la posición especificada.
     */
    T obtener(int indice);

    /**
     * Elimina el elemento en la posición especificada.
     * @param indice posición del elemento a eliminar.
     */
    T eliminar(int indice);

    /**
     * Primera posición donde aparece el dato especificado por equals, o -1 si no se encuentra.
     * @param dato elemento a buscar.
     * @return índice del elemento encontrado, o -1 si no se encuentra.
     */
    int indiceDe(T dato);

    /**
     * Devuelve la cantidad de elementos en la lista.
     * @return cantidad de elementos en la lista.
     */
    int getTamanio();

    /**
     * Indica si la lista contiene el elemento especificado por equals.
     * @param dato elemento a buscar.
     * @return true si la lista contiene el elemento, false en caso contrario.
     */
    boolean contiene(T dato);

    /**
     * Indica si la lista está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    boolean estaVacia();

    /**
     * Agrega todos los elementos de otra lista al final de esta lista.
     * @param otraLista lista cuyos elementos se agregarán a esta lista.
     */
    void agregarTodos(Lista<T> otraLista);
}