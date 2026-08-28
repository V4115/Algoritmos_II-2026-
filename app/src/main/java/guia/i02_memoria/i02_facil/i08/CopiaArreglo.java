package guia.i02_memoria.i02_facil.i08;

/**
 * 8. Copiar arreglo. Copiá un int[] con Arrays.copyOf, modificá la copia y verificá con un test que
el original quedó intacto.
 */

public class CopiaArreglo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3};

        System.out.println("Antes de copiar: ");
        printArray(array);

        int[] copy = java.util.Arrays.copyOf(array, array.length);
        copy[0] = 10; // Modify the copy

        System.out.println("Después de modificar la copia: ");
        printArray(copy);

        System.out.println("Original después de modificar la copia: ");
        printArray(array);
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}