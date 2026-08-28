package guia.i02_memoria.i03_medio.e09;

/**
 * 9. Contar inalcanzables. Dado un boolean[] que indica qué objetos son alcanzables, devolvé
cuántos serían recolectados por el GC; testealo.
 */

public class ContadorInalcanzables {

    public static void main(String[] args) {
        boolean[] alcanzables = {true, false, true, false, false, true};
        int cant_inalcanzables = contarInalcanzables(alcanzables);
        System.out.println("Cantidad de objetos inalcanzables: " + cant_inalcanzables);
    }

    private ContadorInalcanzables() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    public static int contarInalcanzables(boolean[] alcanzables) {
        int n = 0;
        for (boolean alcanzable : alcanzables) {
            if (!alcanzable) {
                n++;
            }
        }
        return n;
    }
}