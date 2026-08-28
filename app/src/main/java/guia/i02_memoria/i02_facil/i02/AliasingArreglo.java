package guia.i02_memoria.i02_facil.i02;

/**
 * 2. Aliasing de arreglo. Método que reciba un int[], cree un alias y cambie el primer elemento;
verificá con un test que el arreglo original también cambió.
 */

public class AliasingArreglo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] alias = createAlias(array);
        int[] copy = createCopy(array);

        System.out.println("Arreglo original:");
        printArray(array);

        alias[0] = 99;

        System.out.println("Arreglo original después de modificar el alias:");
        printArray(array);

        copy[0] = 100;

        System.out.println("Copia del arreglo después de modificar la copia:");
        printArray(copy);

        System.out.println("Arreglo original después de modificar la copia:");
        printArray(array);
    }

    public static int[] createAlias(int[] arreglo) {
        return arreglo; // Return the same reference, creating an alias
    }

    public static int[] createCopy(int[] arreglo) {
        int[] copia = new int[arreglo.length];
        System.arraycopy(arreglo, 0, copia, 0, arreglo.length);
        return copia; // Return a new array, creating a copy
    }

    public static void printArray(int[] arreglo) {
        for (int i : arreglo) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}