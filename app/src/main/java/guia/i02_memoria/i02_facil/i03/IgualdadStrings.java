package guia.i02_memoria.i02_facil.i03;
/**
 * 3. == vs equals. Método que reciba dos String y devuelva un boolean[] con {s1 == s2,
s1.equals(s2)}. Testealo con dos new String("x").
 */

public class IgualdadStrings {

    public static boolean[] compareStrings(String s1, String s2) {
        boolean[] results = new boolean[2];
        results[0] = (s1 == s2);
        results[1] = s1.equals(s2);
        return results;
    }

    public static void main(String[] args) {
        String str1 = new String("x");
        String str2 = new String("x");

        boolean[] results = compareStrings(str1, str2);

        System.out.println("s1 == s2: " + results[0]);
        System.out.println("s1.equals(s2): " + results[1]);
    }
}