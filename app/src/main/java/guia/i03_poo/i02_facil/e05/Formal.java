package guia.i03_poo.i02_facil.e05;

/**
 *
 * @author jose
 */

/**
 * Implementation of the Saludador interface for formal greetings.
 */
public class Formal implements Saludador {

    @Override
    public void saludar() {
        System.out.println("Buenos días, ¿cómo está usted?");
    }
    
}
