package guia.i04_complejidad.i02_facil.e01;
/**
 * 1. Suma O(n). long sumar(int[] a); comentá que es 𝑂(𝑛). Test.
 */

public final class SumaDeArreglo{

    private SumaDeArreglo(){

    }

    public static long sumar(int[] arreglo){ //O(n) = O(1) + O(n)*O(1) + O(1)
        if(arreglo == null){
            throw new IllegalArgumentException("Arreglo nulo");
        }
        long suma=0; //O(1)

        for(int actual: arreglo){ //O(n)
            suma += actual; //O(1)
        }

        return suma; //O(1)
    }

    public static void main(String[] args){
        int[] datos = {1, 2, 3, 4, 5};
        System.out.println("Suma de {1,2,3,4,5} = " + sumar(datos)); // 15
        System.out.println("Suma de {} = " + sumar(new int[0])); // 0
    }
}