package guia.i02_memoria.i03_medio.e07;

/**
 * 7. Identidad vs igualdad. boolean sonElMismo(Object,Object) (==) y boolean sonIguales(Object,Object)
(equals); test que distingan ambos casos.
 */

public class IdentidadIgualdad {

    public static void main(String[] args) {
        String a = new String("test");
        String b = new String("test");
        String c = a;

        System.out.println("a == b: " + sonElMismo(a, b)); // false
        System.out.println("a.equals(b): " + sonIguales(a, b)); // true
        System.out.println("a == c: " + sonElMismo(a, c)); // true
        System.out.println("a.equals(c): " + sonIguales(a, c)); // true
    }

    private IdentidadIgualdad() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    public static boolean sonElMismo(Object o1, Object o2) {
        return o1 == o2;
    }

    public static boolean sonIguales(Object o1, Object o2) {
        return o1 != null && o1.equals(o2);
    }
}