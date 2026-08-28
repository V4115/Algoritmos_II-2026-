package guia.i02_memoria.i03_medio.e01;

/**
 * 1. Swap que no anda. Mostrá que un swap(int a, int b) no intercambia nada afuera, y escribí
swap(int[] v, int i, int j) que sí lo haga. Testealo.
 */

public class IntercambioEnArreglo {

    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        System.out.println("Antes del swap: a = " + a + ", b = " + b);
        swap(a, b);
        System.out.println("Después del swap: a = " + a + ", b = " + b);

        int[] arreglo = {1, 2, 3, 4, 5};
        System.out.println("Antes del swap en arreglo: " + java.util.Arrays.toString(arreglo));
        swap(arreglo, 1, 3);
        System.out.println("Después del swap en arreglo: " + java.util.Arrays.toString(arreglo));
    }

    private IntercambioEnArreglo() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    // This method does not swap the values outside of its scope
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    // This method swaps the values in the array at indices i and j
    public static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}