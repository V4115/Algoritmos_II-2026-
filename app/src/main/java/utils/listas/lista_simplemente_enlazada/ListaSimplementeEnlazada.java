package utils.listas.lista_simplemente_enlazada;

/**
 * Implementación del TDA {@link Lista} con <b>nodos simplemente enlazados</b>:
 * cada nodo guarda su valor y una referencia al siguiente. Mantenemos punteros
 * al {@code primero} y al {@code ultimo} para agregar al final en O(1).
 * <p>
 * Costos: agregar al principio/final es O(1); {@code obtener}/{@code insertar}/
 * {@code eliminar} en una posición cualquiera son O(n) porque hay que recorrer
 * hasta ella. No hay acceso directo por índice como en el arreglo.
 */

import java.util.Objects;

public class ListaSimplementeEnlazada<T> implements Lista<T>{
    
    private static final class Nodo<T> {
        T valor;
        Nodo<T> siguiente = null;
        
        Nodo(T valor) { 
            this.valor = valor; 
        }
    }

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private int len = 0;
    private Nodo<T> primero = null;
    private Nodo<T> ultimo = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    //No hace falta un constructor explicito

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    /**
     * Agrega un elemento al final de la lista.
     * @param dato elemento a agregar.
     * @throws NullPointerException si {@code dato} es {@code null}.
     */

    @Override
    public void agregar(T dato){

        Objects.requireNonNull(dato);

        Nodo<T> actual = new Nodo<>(dato);

        if(this.primero == null){
            this.primero = this.ultimo = actual;
        }else{
            this.ultimo.siguiente = actual; 
            this.ultimo = actual;
        }

        this.len++;
    }
    
    /**
     * Agrega un elemento al inicio de la lista.
     * @param dato elemento a agregar.
     * @throws NullPointerException si {@code dato} es {@code null}.
     */
    @Override
    public void agregarAlInicio(T dato){
        Objects.requireNonNull(dato);

        Nodo<T> actual = new Nodo<>(dato);
        actual.siguiente = this.primero;
        this.primero = actual;

        if(this.ultimo == null){
            this.ultimo = actual;
        }

        this.len++;
    }
    /**
     * Inserta un elemento en la posición especificada.
     * @param indice posición donde insertar el elemento.
     * @param dato elemento a insertar.
     * @throws NullPointerException si {@code dato} es {@code null}.
     * @throws IndexOutOfBoundsException si {@code indice} es menor que 0 o mayor  que {@code getTamanio()}.
     */
    @Override
    public void insertar(int indice, T dato){
        Objects.requireNonNull(dato);


        if(indice == 0){
            agregarAlInicio(dato);
            return;
        }else if (indice == getTamanio()) {
            agregar(dato);
            return;
        }

        Nodo<T> anterior = buscarNodoAnterior(indice);
        
        Nodo<T> nuevoDato = new Nodo<>(dato);
        nuevoDato.siguiente = anterior.siguiente;
        anterior.siguiente = nuevoDato;
        this.len++;
    }
    /**
     * Devuelve el elemento en la posición especificada.
     * @param indice posición del elemento a devolver.
     * @return elemento en la posición especificada.
     * @throws IndexOutOfBoundsException si {@code indice} es menor que 0 o mayor o igual que {@code getTamanio()}.
     */
    @Override
    public T obtener(int indice){
        return buscarNodo(indice).valor;
    }

    /**
     * Elimina el elemento en la posición especificada.
     * @param indice posición del elemento a eliminar.
     * @return elemento eliminado.
     * @throws IndexOutOfBoundsException si {@code indice} es menor que 0 o mayor o igual que {@code getTamanio()}. 
     */
    @Override
    public T eliminar(int indice){

        if(indice < 0 || indice >= getTamanio()){
            throw new IndexOutOfBoundsException();
        }
        
        Nodo<T> eliminado;

        if (indice == 0){
            eliminado = this.primero;
            this.primero = this.primero.siguiente;
            if(this.primero == null){
                this.ultimo = null;
            }
        }else{
            Nodo<T> anterior = buscarNodoAnterior(indice);
            eliminado = anterior.siguiente;
            anterior.siguiente = eliminado.siguiente;
            if(eliminado == this.ultimo){
                this.ultimo = anterior;
            }
        }

        this.len--;
        return eliminado.valor;
    }

    /**
     * Primera posición donde aparece el dato especificado por equals, o -1 si no se encuentra.
     * @param dato elemento a buscar.
     * @return índice del elemento encontrado, o -1 si no se encuentra.
     * @throws NullPointerException si {@code dato} es {@code null}.
     */
    @Override
    public int indiceDe(T dato){
        Objects.requireNonNull(dato);
        int i = 0;
        for(Nodo<T> actual = this.primero; actual != null; actual = actual.siguiente, i++){
            if(Objects.equals(actual.valor, dato)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Indica si la lista contiene el elemento especificado por equals.
     * @param dato elemento a buscar.
     * @return true si la lista contiene el elemento, false en caso contrario.
     * @throws NullPointerException si {@code dato} es {@code null}.
     */
    @Override
    public boolean contiene(T dato){
        Objects.requireNonNull(dato);

        for(Nodo<T> actual = this.primero; actual != null; actual = actual.siguiente){
            if(Objects.equals(actual.valor, dato)){
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si la lista está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     * @see #getTamanio()
     */
    @Override
    public boolean estaVacia(){
        return getTamanio() == 0;
    }

    /**
     * Agrega todos los elementos de otra lista al final de esta lista.
     * @param otraLista lista cuyos elementos se agregarán.
     * @throws NullPointerException si {@code otraLista} es {@code null}.
     */
    @Override
    public void agregarTodos(Lista<T> otraLista){
        Objects.requireNonNull(otraLista);
        int n = otraLista.getTamanio(); //tengo que tomar el tamanio antes de agregar
        for (int i = 0; i < n; i++) {
            agregar(otraLista.obtener(i));
        }
    }

    //Medotodos privados
    //Manejo de nodos
    private Nodo<T> buscarNodo(int indice){
        if(indice < 0 || indice >= getTamanio()){
            throw new IndexOutOfBoundsException();
        }
        
        Nodo<T> actual = this.primero;
        for(int i = 0; i < indice; i++){
            actual = actual.siguiente;
        }

        return actual;

    }

    private Nodo<T> buscarNodoAnterior(int indice){
        if(indice < 1 || indice > getTamanio()){
            throw new IndexOutOfBoundsException();
        }
        
        Nodo<T> actual = this.primero;
        for(int i = 0; i < indice - 1; i++){
            actual = actual.siguiente;
        }

        return actual;

    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    @Override
    public int getTamanio(){
        return this.len;
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}