/**
 * 8. Promedio de un arreglo. Dado un double[], calculá el promedio con un for-each.
 */

public class PromedioArreglo{
    
    static final String ERROR_MSG_EMPTY_ARRAY = "EL arreglo no puee estar vacío";
    public static void main(String[] args){

        double[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Arreglo: ");
        for(double value : array){
            System.out.print(value + " ");
        }
        System.out.print('\n');
        
        System.out.println("Promedio: " + doubleArrayGetMean(array));
    }

    public static double doubleArrayGetMean(double[] array){
        
        if(array == null || array.length == 0){
            throw new IllegalArgumentException(ERROR_MSG_EMPTY_ARRAY);
        }

        double total = 0;
        for(double value : array){
            total += value;
        }

        return total / array.length;
    }
}