package guia.i05_lineales.i02_facil.e01;
/**
 * 1. Invertir. List<Integer> invertir(List<Integer> l) que devuelva una nueva lista invertida.
Test.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InvertirLista{

    public static void main(String[] args) {
        List<String> nombres = List.of("José", "María", "Jorge", "Juani", "Victoria", "Fede");
        List<String> invertidos = new LinkedList<>();

        for(String actual : nombres){
            invertidos.addFirst(actual);
        }

        System.out.println("Lista Original: " + Arrays.toString(nombres.toArray()));
        System.out.println("Lista Invertida: " + Arrays.toString(invertidos.toArray()));
        System.out.println("Lista invertida: " + nombres.reversed());
    }

}