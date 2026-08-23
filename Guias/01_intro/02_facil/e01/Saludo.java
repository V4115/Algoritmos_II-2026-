/**
 * Ejercicio 1 (facil): Saludo personal.
 * Devuelve un saludo formado a partir del nombre y el legajo del alumno.
 */

/*Posibles cambios: 
Verificar que el nombre no esté compuesto por characteres especiales: ?*¡]$%&, etc
Verificar que el nombre no esté compuesto por solo puntos, comas, etc
Verifcar que no esté compuesto por una sola letra o similares.
*/

import java.util.Scanner;

public class Saludo{

    //I leave the message as an inmutable string to it is easier to change later in a single line
    static final String EXCEPTION_MSG_EMPTY_NAME = "El nombre no puede estar vacío";
    static final String EXCEPTION_MSG_DIGIT_TRUE = "El nombre no puede contener dígitos";
    static final String USR_MSG_GREETING = "Hola ";

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String name;
        boolean nameAccepted = false;
        do { 
            try {
                //gets a name from the keyboard and erases any spaces
                name = sc.nextLine().strip();
                //It verifies if there is a name at all 
                //name == null might be redundant in this case but i leave it as an example for future use
                if (name  == null || name.isBlank()) {
                    throw new IllegalArgumentException(EXCEPTION_MSG_EMPTY_NAME);
                }
                //Verifies if there are numbers in the given name
                //Possible change to avoid creating a char
                //for(int i = 0; i < name.length(); i++) {if (Character.isDigit(name.charAt(i))) }
                for(char c: name.toCharArray()){
                    if(Character.isDigit(c)){
                        throw new IllegalArgumentException(EXCEPTION_MSG_DIGIT_TRUE);
                    }
                }

                
                System.out.println(USR_MSG_GREETING + name);
                nameAccepted = true;
                //Close scanner before ending the program
                sc.close();

            } catch (IllegalArgumentException e) {
                //prints of errors trhough console
                System.out.println(e.getMessage());
            }
        } while (!nameAccepted);
    }
}