package guia.i04_complejidad.i02_facil.e02;
/**
 * 2. Pares iguales O(n²). int paresIguales(int[] a) con dos bucles anidados; test.
 */
public class ParesIguales{
    private ParesIguales(){

    }

    public static int paresIguales(int[] arreglo){ //O(n²)
        if(arreglo == null){
            throw new IllegalArgumentException("Arreglo nulo");
        }
        int suma=0; //O(1)

        for(int i=0; i < arreglo.length; i++){ //O(n)
            for(int j=i+i; j<arreglo.length; j++){ //O(n) * O(n) = O(n²)
                if(arreglo[i] == arreglo[j]){ //O(1)
                    suma++;    //O(1)
                }
            }
        }

        return suma;
    }

    public static void main(String[] args) {
        int[] datos = {1, 2, 2, 3, 3, 3};
        // 1 par de doses + 3 pares de treses = 4
        System.out.println("Pares iguales de {1,2,2,3,3,3} = " + paresIguales(datos));
    }
}