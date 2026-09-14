package guia.i03_poo.i03_medio.e02;

public interface Tablero<T>{
    /**
     * @param fila: fila donde se quiere poner el valor. de 1 a n.
     * @param columna: coumna donde se quiere poner el valor. de 1 a n.
     * @param valor: el valor a poner.
     */
    void colocar(int fila, int columna, T valor);
    
    /**
     * Obtiene el valor en la posición indicada.
     * @param fila: fila de la posición a obtener. De 1 a n
     * @param columna: columna de la posición a obtener. De 1 a n
     * @return el valor en la posición indicada
     */
    T obtener(int fila, int columna);
    
    /*
     * Devuelve true si el tablero está lleno, false en caso contrario.
     */
    boolean estaLleno();
    
    /**
     * Devuelve la cantidad de filas del tablero.
     * @return la cantidad de filas del tablero
     */
    int getCantidadDeFilas();
    /**
     * Devuelve la cantidad de columnas del tablero.
     * @return la cantidad de columnas del tablero
     */
    int getCantidadDeColumnas();

    /**
     * Devuelve true si la posición indicada está vacía, false si está ocupada.
     * @param fila: fila de la posición a consultar. De 1 a n
     * @param columna: columna de la posición a consultar. De 1 a n
     * @return true si la posición está vacía, false en caso contrario
     */
    boolean estaVacio(int fila, int columna);
}