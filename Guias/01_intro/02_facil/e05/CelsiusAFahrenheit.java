/**
 * Conversión de temperatura. Escribí un método que convierta grados Celsius a Fahrenheit
(F = C * 9/5 + 32). Cuidado con la división entera.
 */

public class CelsiusAFahrenheit{

    static final Double CELSIUS_MIN_VALUE = -273.15;
    static final String ERROR_MSG_CELSIUS_MIN_VALUE = "La temperatura en Celsius no puede valer menor a " + CELSIUS_MIN_VALUE;

    public static void main(String[] args) {
        
    }

    public static double CelsiusAFahrenheit(double c){
        if (c <  CELSIUS_MIN_VALUE) {
            throw new IllegalArgumentException(ERROR_MSG_CELSIUS_MIN_VALUE);
        }
        return c * (9.0 / 5.0) + 32.0;
    }
}