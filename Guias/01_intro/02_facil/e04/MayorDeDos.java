
/**
 * 4. Mayor de dos. Escribí un método int mayor(int a, int b) que devuelva el mayor.
 */

public class MayorDeDos{

    static final String ERROR_MSG_NON_INT = "Tiene que utilizar numeros enteros";
    static final String ERROR_MSG_NOT_TWO_NUMBERS = "Debe ingresar exactamente dos números";
    public static void main(String[] args) {
        
        //It only works with 2 numbers
        if (args.length != 2) {
            System.out.println(ERROR_MSG_NOT_TWO_NUMBERS);
            return;
        }
        //args gives String so i need to convert them into int
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);

            System.out.println("El mayor de " + a + " y " + b + " es: " + mayor(a, b));
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MSG_NON_INT);
        }
    }

    public static int mayor(int a, int b){
        return (a>=b)? a: b;
    }
}