package guia.i02_memoria.i02_facil.i06;

/**
 * 6. Reasignar no afecta. Método que reciba un int[] y lo reasigne a otro adentro; verificá con
un test que el original no cambió.
 */

public class ReasignarNoAfecta {

    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3};

        System.out.println("Antes de reasignar: ");
        printArray(originalArray);

        reassignArray(originalArray);

        System.out.println("Después de reasignar: ");
        printArray(originalArray);
    }

    public static void reassignArray(int[] arr) {
        //Reasingn the array to a new array
        arr = new int[]{4, 5, 6};
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}