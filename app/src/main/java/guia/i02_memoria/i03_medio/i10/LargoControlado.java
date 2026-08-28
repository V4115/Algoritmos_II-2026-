package guia.i02_memoria.i03_medio.i10;

/**
 * 10. NPE controlada. Método que lance NullPointerException ante un null, y una versión segura
que devuelva un valor por defecto; test con assertThrows y con la versión segura.
 */

public class LargoControlado {

    public static void main(String[] args) {
        String str = null;

        // Test the method that throws NullPointerException
        try {
            int length = getLengthOrThrow(str);
            System.out.println("Length: " + length);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException as expected: " + e.getMessage());
        }

        // Test the safe version that returns a default value
        int safeLength = getLengthOrDefault(str, 0);
        System.out.println("Safe Length: " + safeLength);
    }

    private LargoControlado() {
        // Private constructor to prevent instantiation
        //This class is not meant to create objects, it only contains tools to be use through the class name.
    }

    public static int getLengthOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException("String is null");
        }
        return str.length();
    }

    public static int getLengthOrDefault(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        return str.length();
    }
}