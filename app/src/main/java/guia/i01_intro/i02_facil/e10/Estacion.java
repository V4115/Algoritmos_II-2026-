/**
 * 10. Enum de estaciones. Definí un enum Estacion con las cuatro estaciones y un método que
imprima todas con values().
 */

public class Estacion{

    static final String MSG_AUTUMN = "Otoño";
    static final String MSG_WINTER = "Invierno";
    static final String MSG_SPRING = "Primavera"; 
    static final String MSG_SUMMER = "Verano";
    
    public enum Seasons{
        AUTUMN(MSG_AUTUMN), 
        WINTER(MSG_WINTER), 
        SPRING(MSG_SPRING), 
        SUMMER(MSG_SUMMER);

        private final String message;

        //asignes the message to the enum value
        Seasons(String message){
            this.message = message;
        }

        //allows to get the message from each enum
        public String getMessage(){
            return message;
        }
    }

    public static void main(String[] args){

        PrintSeasons();
    }

    public static void PrintSeasons(){
        for(var station : Seasons.values()){
            System.out.println(station.getMessage());
        }
    }
}