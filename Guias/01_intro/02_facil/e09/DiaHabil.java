/**
 * 9. Día hábil. Usando un switch expression, dado un char ('L', 'M', …) devolvé “hábil” o “fin de
semana”.
 */

public class DiaHabil{
    public static void main(String[] args){

        char[] week= {'L', 'M', 'X', 'J', 'V', 'S', 'D'};

        for (char day : week){
            System.out.println(day + " es: " + esDiaHabil(day));
        }
    }

    public static String esDiaHabil(char day){

        return switch (day) {
            case 'L', 'M', 'X', 'J', 'V' -> "hábil";
            case 'S', 'D' -> "fin de semana";
            default -> "desconocido";
        }; 
    }
}