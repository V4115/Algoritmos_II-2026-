package guia.i02_memoria.i03_medio.e06;

/**
 * 6. Optional encadenado. int largoOrCero(Optional<String> o) con map y orElse; test con
presente y vacío.
 */

public class LargoDeTexto {

    public static void main(String[] args) {
        java.util.Optional<String> presente = java.util.Optional.of("Hola");
        java.util.Optional<String> vacio = java.util.Optional.empty();

        System.out.println("Largo de 'hola': " + largoOrCero(presente)); // Prints 4
        System.out.println("Largo de '': " + largoOrCero(vacio)); // Prints 0
    }

    private LargoDeTexto() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    public static int largoOrCero(java.util.Optional<String> o) {
        return o.map(String::length).orElse(0);
    }
}