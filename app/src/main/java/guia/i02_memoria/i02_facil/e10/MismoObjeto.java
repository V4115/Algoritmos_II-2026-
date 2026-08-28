package guia.i02_memoria.i02_facil.e10;

/**
 * 10. ¿Mismo objeto? boolean mismoObjeto(Object a, Object b) usando ==; testealo con un alias y
con dos objetos distintos de igual contenido.
 */

public class MismoObjeto {

    public static boolean mismoObjeto(Object a, Object b) {
        return a == b;
    }

    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = str1; // Alias
        String str3 = new String("Hello"); // Different object with same content

        System.out.println("str1 y str2 son el mismo objeto: " + mismoObjeto(str1, str2));
        System.out.println("str1 y str3 son el mismo objeto: " + mismoObjeto(str1, str3));
    }
}