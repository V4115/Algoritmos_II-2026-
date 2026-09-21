package guia.i03_poo.i03_medio.e07;
/**
 * 7. Genérico acotado. static <T extends Comparable<T>> T maximo(T[] a); test con Integer y
String.
 */


public class Main{
    public static void main(String[] args) {
        Integer[] enteros = {3, 9, 1, 7, 4};
        System.out.println("Máximo entero: " + Maximos.maximo(enteros)); // 9

        String[] palabras = {"banana", "manzana", "kiwi", "durazno"};
        // Orden natural de String es lexicográfico: "manzana" es el mayor.
        System.out.println("Máximo alfabético: " + Maximos.maximo(palabras));
    }
}