package guia.i05_lineales.i02_facil.e03;
/**
 * 3. Unión. Unión de dos listas sin duplicados. Test
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public final class UnionListas{

    public UnionListas() {
    }

    public static <T> List<T> unionSinDuplicado(List<T> a, List<T> b){
        LinkedHashSet<T> rta = new LinkedHashSet<>(a);

        rta.addAll(b);

        return new ArrayList<>(rta);
    }

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3);
        List<Integer> b = List.of(3, 4, 5);
        System.out.println("a:     " + a);
        System.out.println("b:     " + b);
        System.out.println("union: " + unionSinDuplicado(a, b));
    }
}