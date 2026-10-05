package guia.i04_complejidad.i02_facil.e03;
/**
 * 3. Máximo O(n). int maximo(int[] a); test (incluí un solo elemento).
 */


public class MaximoDeArreglos{

    private MaximoDeArreglos(){

    }

    public static  int maximo(int[] a){ //O(n)
        if(a==null){
            throw new IllegalArgumentException("Puntero nulo");
        }
        if(a.length <= 0){
            throw new IllegalArgumentException("Arreglo vacío");
        }

        int maximo = a[0]; //O(1)

        for(int i = 1; i <a.length; i++){ //O{n}
            if(a[i] > maximo){  //O(1)
                maximo = a[i]; //O(1)
            }
        }

        return maximo; //O(1)
    }

    public static void main(String[] args) {
        System.out.println("Maximo de {3,9,1,9,2} = " + maximo(new int[]{3, 9, 1, 9, 2})); // 9
        System.out.println("Maximo de {-7} = " + maximo(new int[]{-7})); // -7
    }
}