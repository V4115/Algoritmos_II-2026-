package guia.i05_lineales.i02_facil.e02;

/**
 * 2. Sin duplicados. List<Integer> sinDuplicados(List<Integer> l) preservando el orden (usá
LinkedHashSet). Test.
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class SinDuplicados<T>{

    public static <T> List<T> sinDuplicados(List<T> original){
        // LinkedHashSet descarta repetidos y conserva el orden de insercion.
        return new ArrayList<>(new LinkedHashSet<>(original));
    }

    public static void main(String[] args) {
        List<Integer> original = List.of(3, 1, 3, 2, 1, 5);
        System.out.println("Original:       " + original);
        System.out.println("Sin duplicados: " + sinDuplicados(original));
    }
}