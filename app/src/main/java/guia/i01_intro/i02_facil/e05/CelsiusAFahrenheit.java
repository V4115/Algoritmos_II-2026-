package guia.i01_intro.i02_facil.e05;
/**
 * Conversión de temperatura. Escribí un método que convierta grados Celsius a Fahrenheit
(F = C * 9/5 + 32). Cuidado con la división entera.
 */

public class CelsiusAFahrenheit{

    static final double CELSIUS_MIN_VALUE = -273.15;
    static final String ERROR_MSG_CELSIUS_MIN_VALUE = "La temperatura en Celsius no puede valer menor a " + CELSIUS_MIN_VALUE;
    static final String ERROR_MSG_NON_NUMBERS = "Tiene que utilizar un número";
    static final String ERROR_MSG_TOO_MANY_INPUTS = "Debe ingresar un solo número";

    public static void main(String[] args) {
        //It only accepts 1 number
        if (args.length != 1) {
            System.out.println(ERROR_MSG_TOO_MANY_INPUTS);
            return;
        }
        //args gives String so i need to convert them into double
        try {
            double a = Double.parseDouble(args[0]);

            System.out.println(
                a + " Celsius -> " +
                celsiusAFahrenheit(a) +
                " Fahrenheit"
            );

        } catch (NumberFormatException e) {
            System.out.println(ERROR_MSG_NON_NUMBERS);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double celsiusAFahrenheit(double c){
        if (c <  CELSIUS_MIN_VALUE) {
            throw new IllegalArgumentException(ERROR_MSG_CELSIUS_MIN_VALUE);
        }
        return c * (9.0 / 5.0) + 32.0;
    }
}