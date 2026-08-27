package guia.i01_intro.i02_facil.e06;
/**
 * 6. Tabla de multiplicar. Con un for, imprimí la tabla del número que se pase por args.
 */

public class TablaDeMultiplicar{

    static final String ERROR_MSG_NON_NUMBERS = "Tiene que utilizar números enteros";
    static final String ERROR_MSG_INCORRECT_AMOUNT_INPUTS = "Debe ingresar 2 números";
    static final String ERROR_MSG_NEGATIVE_TABLE = "El número de tabla no puede ser negativo";

    public static void main(String[] args){
        
        //It only accepts 2 number2
        if (args.length != 2) {
            System.out.println(ERROR_MSG_INCORRECT_AMOUNT_INPUTS);
            return;
        }
        //args gives String so i need to convert them into int
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);

            printMultiplicationTable(a, b);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_MSG_NON_NUMBERS);
        } catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public static void printMultiplicationTable(int n, int len){
        if(len < 0){
            throw new IllegalArgumentException(ERROR_MSG_NEGATIVE_TABLE);
        }
        for(int i = 0; i <= len; i++){
                System.out.print(n*i + "  ");
            }
            System.out.print('\n');
    }
}