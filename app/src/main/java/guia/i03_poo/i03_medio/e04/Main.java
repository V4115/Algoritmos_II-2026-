package guia.i03_poo.i03_medio.e04;

import java.util.Arrays;

/**
 * 4. Ordenar por criterio. Clase Producto(precio) Comparable; ordená un arreglo con Arrays.sort;
test.
 */

public class Main{
    public static void main(String[] args) {
        Producto monitor = new Producto("Monitor", 400);
        Producto mouse = new Producto("Mouse", 120);
        Producto consola = new Producto("Consola", 5400);

        Producto[] arreglo = {consola, monitor, mouse};

        //Antes de ordenar
        System.out.println("Antes de ordenar");
        for (Producto actual : arreglo){
            System.out.println(actual.toString());
        }
        System.out.println();

        //Ordeno con Arrays.sort
        Arrays.sort(arreglo);

        //despues de ordenar
        System.out.println("Despues de ordenar");
        for (Producto actual : arreglo){
            System.out.println(actual.toString());
        }
        System.out.println();
    }
}