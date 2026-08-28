package guia.i02_memoria.i02_facil.e01;

/**
 * 1. Copia de primitivo. Escribí un método que muestre que asignar int b = a; b = 99; no cambia
a. Devolvé ambos valores y testealo.
 */

public class CopiaPrimitivo {

    public static void main(String[] args) {
        int a = 5;
        int[] resultado = copiaPrimitivo(a);
        System.out.println("Valor de a: " + resultado[0]);
        System.out.println("Valor de b: " + resultado[1]);
    }

    public static int[] copiaPrimitivo(int a) {
        int b = a;
        b = 99;
        return new int[]{a, b};
    }
}