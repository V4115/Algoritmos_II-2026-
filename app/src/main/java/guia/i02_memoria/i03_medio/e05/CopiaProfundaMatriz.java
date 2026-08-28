package guia.i02_memoria.i03_medio.e05;

/**
 * 5. copy profunda de matriz. int[][] copiaProfunda(int[][] m); test que modificar la copy no
toca el original.
 */

public class CopiaProfundaMatriz {

    public static void main(String[] args) {
        int[][] original = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] copy = copiaProfunda(original);

        System.out.println("Antes de modificar la copia:");

        System.out.println("Original:");
        printMatriz(original);
        System.out.println();
        System.out.println("Copia:");
        printMatriz(copy);
        System.out.println();

        // Modify the copy and show that the original remains unchanged
        copy[0][0] = 99;

        System.out.println("Después de modificar la copia:");
        System.out.println("Original:");
        printMatriz(original);
        System.out.println();
        System.out.println("Copia:");
        printMatriz(copy);
        System.out.println();
    }

    private CopiaProfundaMatriz() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    public static int[][] copiaProfunda(int[][] m) {
        if (m == null) {
            return null;
        }
        int[][] copy = new int[m.length][];
        for (int i = 0; i < m.length; i++) {
            copy[i] = m[i].clone();
        }
        return copy;
    }

    private static void printMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            System.out.println(java.util.Arrays.toString(fila));
        }
    }
}