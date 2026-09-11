package utils.pilas.pila_arreglo_dinamica;

import java.util.Arrays;

public class PilaArregloDinamica<T>{

    //MSG
    //ERROR MSG
    private static final String ERROR_MSG_PILA_VACIA = "ERROR: La pila está vacía";
    private static final String ERROR_MSG_TAMANIO_INICIAL_NO_POSITIVO = "El tamaño inicial de la pila debe ser positivo";
    private static final String ERROR_MSG_FACTOR_REDIMENSION_ILEGAL = "El factor de crecimiento de la pila debe ser mayor a 1";

    //Variables privadas
    private Object[] datos;
    private int cantidad;
    private final int factorRedimension;

    //Constructor
    public PilaArregloDinamica(int capacidad, int factor){
        if(capacidad <= 0){
            throw new IllegalArgumentException(ERROR_MSG_TAMANIO_INICIAL_NO_POSITIVO);
        }
        if(factor < 1){
            throw new IllegalArgumentException(ERROR_MSG_FACTOR_REDIMENSION_ILEGAL);
        }

        this.datos = new Object[capacidad];
        this.cantidad = 0;
        this.factorRedimension = factor;
    }

    //Getters
    public boolean estaVacia(){
        return (cantidad == 0);
    }

    public int getCantidad(){
        return cantidad;
    }

    //Metodos publicos
    public void apilar(T dato){
        if (cantidad >= datos.length) {
            redimensionar();
        }

        datos[cantidad++] = dato;   
    }

    public T desapilar(){
        if(estaVacia()){
            throw new IllegalArgumentException(ERROR_MSG_PILA_VACIA);
        }
        T dato = (T) datos[--cantidad];
        
        datos[cantidad] = null; //No es necesario, pero me quito tener basura en el vector
        
        return dato;
    }

    public T tope(){
        if( estaVacia()){
            throw new IllegalArgumentException(ERROR_MSG_PILA_VACIA);
        }
        return (T)datos[cantidad-1];
    }

    //Metodos privados
    private void redimensionar(){
        datos = Arrays.copyOf(datos, datos.length*factorRedimension);
    }

}