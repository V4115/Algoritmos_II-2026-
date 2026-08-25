/**
 * 1. Máximo de un arreglo. Escribí int maximo(int[] v) y testealo con JUnit (incluí el caso de un
solo elemento).
 */

import java.util.Random;

public class Maximo{
    
    static final String ERROR_MSG_EMPTY_ARRAY = "ERROR: Arreglo vacío";
    static final int ARRAY_MAX_LENGTH = 10;
    static final int ARRAY_MIN_LENGTH = 0;
    static final int ARRAY_MAX_VALUE = 33;
    static final int ARRAY_MIN_VALUE = -28;
    
    public static void main(String[] args){
        
        int[] array = generateRandomIntArray(
            ARRAY_MIN_LENGTH, 
            ARRAY_MAX_LENGTH, 
            ARRAY_MIN_VALUE, 
            ARRAY_MAX_VALUE);

        //Print de random array
        System.out.print("Arreglo: ");
        for(int value : array){
            System.out.print("[" + value + "]" + " ");
        }
        System.out.println();

        try{
           System.out.println("El máximo es: " + maximo(array)); 
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    //Methods--------------------------------------------------------------------------------------------------
    public static int maximo(int[] v){

        if(v == null || v.length == 0){
            throw new IllegalArgumentException(ERROR_MSG_EMPTY_ARRAY);
        }

        int max = v[0];

        for(int i = 1; i < v.length; i++){
            if(v[i] > max){
                max = v[i];
            }
        }

        return max;
    }

    public static int[] generateRandomIntArray(int lenMin, int lenMax, int valueMin, int valueMax){
        
        Random random = new Random();

        int len = random.nextInt(lenMax - lenMin + 1) + lenMin;

        int [] array = new int [len];

        for (int i=0; i < array.length; i++){
            array[i] = random.nextInt(valueMax - valueMin + 1) + valueMin;
        }

        return array;
    }



}
