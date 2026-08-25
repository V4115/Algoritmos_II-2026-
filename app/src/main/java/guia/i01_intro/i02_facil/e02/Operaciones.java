/**
 * 2. Suma de dos números. Leé dos enteros desde args y mostrá su suma, resta, producto y
división.
 */


public class Operaciones{

    static final String ERROR_MSG_CANT_DIVIDE_BY_CERO = "No se puede dividir por cero";
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

            System.out.println(a + "+" + b + " = " + sum(a, b));
            System.out.println(a + "-" + b + " = " + substract(a, b));
            System.out.println(a + "*" + b + " = " + multiply(a, b));
            System.out.println(a + "/" + b + " = " + divide(a, b));
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MSG_NON_INT);
        }
    }

    public static int sum(int a, int b){
        return a + b;
    }
    public static int substract(int a, int b){
        return a - b;
    }
    public static int multiply(int a, int b){
        return a * b;
    }

    //Verify if this is okey
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException(ERROR_MSG_CANT_DIVIDE_BY_CERO);
        }

        return (double) a / b;
    }
}
