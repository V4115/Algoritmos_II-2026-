package guia.i02_memoria.i02_facil.i04;

/**
 * 4. Seguro ante null. String seguro(String s) que devuelva s, o "" si s es null. Testealo con null
y con un texto.
 */

public class SeguroNull {

    public static void main(String[] args) {
        String str1 = null;
        String str2 = "Hello, World!";

        System.out.println("Seguro con null: '" + seguro(str1) + "'");
        System.out.println("Seguro con texto: '" + seguro(str2) + "'");
    }

    public static String seguro(String s) {
        return (s != null) ? s : "";
    }
}