package guia.i02_memoria.i02_facil.e05;
/**
 * 5. Optional con default. String orDefault(Optional<String> o) que devuelva el valor o "N/A".
Testealo con Optional.of y Optional.empty.
 */

public class OptionalDefault {

    public static void main(String[] args) {
        java.util.Optional<String> optionalWithValue = java.util.Optional.of("Hello, Optional!");
        java.util.Optional<String> emptyOptional = java.util.Optional.empty();

        System.out.println("Optional con valor: '" + orDefault(optionalWithValue) + "'");
        System.out.println("Optional vacío: '" + orDefault(emptyOptional) + "'");
    }

    public static String orDefault(java.util.Optional<String> o) {
        return o.orElse("N/A");
    }
}