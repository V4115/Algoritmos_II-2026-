package guia.i04_complejidad.i02_facil.e04;
/**
 * 4. ¿Hay duplicado? O(n²). boolean hayDuplicado(int[] a) comparando todos con todos; test.
 */

import java.util.Objects;

public class HayDuplicado{
    private HayDuplicado(){

    }

    public static boolean hayDuplicado(int[] a){ //O(n²)
        Objects.requireNonNull(a, "el arreglo no puede ser nulo");

        for(int i = 0; i < a.length; i++){ //O(n)
            for(int j = i + 1; j < a.length; j++){ //O(n) -> O(n²)
                if(a[i] == a[j]){ //O(1)
                    return true; //O(1)
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("hayDuplicado {1,2,3,2} = " + hayDuplicado(new int[]{1, 2, 3, 2})); // true
        System.out.println("hayDuplicado {1,2,3,4} = " + hayDuplicado(new int[]{1, 2, 3, 4})); // false
    }
}