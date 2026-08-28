package guia.i02_memoria.i03_medio.i02;

/**
 * 2. Mutar en un método. Método que duplique cada elemento de un int[] recibido (in place);
test que el arreglo de afuera quedó duplicado.
 */

public class DuplicarEnArreglo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Antes de duplicar: " + java.util.Arrays.toString(array));
        duplicate(array);
        System.out.println("Después de duplicar: " + java.util.Arrays.toString(array));
    }

    private DuplicarEnArreglo() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    // This method duplicates each element in the array in place
    public static void duplicate(int[] v) {
        for (int i = 0; i < v.length; i++) {
            v[i] *= 2;
        }
    }
}