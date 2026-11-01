package guia.i05_lineales.i02_facil.e04;
/**
 * 4. Palíndromo. boolean esPalindromo(List<Character> l). Test.
 */

import java.util.ArrayList;
import java.util.List;

public class Palindromo{

    public static <T> boolean esPalindromo(List<T> original){
        List<T> copia = new ArrayList<>(original); // O(n), garantiza get(i) en O(1)
        int i = 0;
        int j = copia.size() - 1;
        while (i < j) {
            if (!copia.get(i).equals(copia.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Character> palabra = List.of('n', 'e', 'u', 'q', 'u', 'e', 'n');
        List<Character> palabra2 = List.of('e', 'l', 'e', 'f', 'a', 'n', 't', 'e');
        System.out.println(palabra + " es palindromo? " + esPalindromo(palabra));
        System.out.println(palabra2 + " es palindromo? " + esPalindromo(palabra2));
    }
}    