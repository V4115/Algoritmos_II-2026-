package guia.i03_poo.i02_facil.e05;

/**
 *
 * @author jose
 */

/**
 * Implementation of the Saludador interface for informal greetings.
 */
public class Informal implements Saludador {

    @Override
    public void saludar() {
        System.out.println("¡Hola! ¿Qué tal?");
    }   

}
