package guia.i03_poo.i03_medio.e07;
/**
 * 7. Genérico acotado. static <T extends Comparable<T>> T maximo(T[] a); test con Integer y
String.
 */

public class Maximos{

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    private Maximos() {
        // Clase de utilidad: no se instancia.
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * Devuelve el elemento máximo del arreglo según su orden natural.
     *
     * @param a   arreglo no vacío de elementos comparables
     * @param <T> tipo comparable consigo mismo
     * @return el mayor elemento
     * @throws IllegalArgumentException si el arreglo es null o vacío
     */
    public static <T extends Comparable<T>> T maximo(T[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser null ni vacío");
        }
        T maximo = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maximo) > 0) {
                maximo = a[i];
            }
        }
        return maximo;
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}