/**
 * 3. Par o impar. Dado un entero, indicá si es par o impar usando el operador %.
 */

public class ParOImpar{

    public static void main(String[] args) {
        
        int a = 22;

        if(esPar(a)){
            System.out.println(a + " Es par");
        }else{
            System.out.println(a + " Es impar");
        }
    }

    public static boolean esPar(int a){
        return a%2 == 0;
    }
}