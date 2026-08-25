/**
 * 7. Cuenta regresiva. Con un while, imprimí desde n hasta 1 y luego “¡fin!”.
 */

public class CuentaRegresiva{
    static final String USR_MSG_END = "Fin!";
    static final String ERROR_MSG_NON_NUMBERS = "Tiene que utilizar un número entero";
    static final String ERROR_MSG_TOO_MANY_INPUTS = "Debe ingresar un solo número";
    static final String ERROR_MSG_NON_POSITIVE_NUMBER = "El numero tiene que ser positivo";
    public static void main(String[] args) {
        
        //It only accepts 1 number
        if (args.length != 1) {
            System.out.println(ERROR_MSG_TOO_MANY_INPUTS);
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);

            printCountDown(n);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_MSG_NON_NUMBERS);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printCountDown(int n){
        if (n <= 0){
            throw new IllegalArgumentException(ERROR_MSG_NON_POSITIVE_NUMBER);
        }

        while (n > 0) { 
            System.out.print(n + " ");
            n--;
        }
        System.out.println(USR_MSG_END);
    }
}