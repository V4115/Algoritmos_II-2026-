package guia.i03_poo.i02_facil.e05;

/**
 * 5. Interfaz simple. Interfaz Saludador con saludar(); dos implementaciones (Formal, Informal);
test de polimorfismo.
 */

/**
 *
 * @author jose
 */
public class Main {

    public static void main(String[] args) {
        // create instances of Formal and Informal
        Formal formal = new Formal();
        Informal informal = new Informal();

        // direct calls to the saludar() method of each implementation
        formal.saludar();
        informal.saludar();

        // calls to the saludar() method through the interface reference
        nombreMetodo(formal);
        nombreMetodo(informal);

    }

    /**
     * calls the saludar() method of the passed Saludador implementation
     * @param s the Saludador implementation to call
     */
    public static void nombreMetodo(Saludador s) {
        s.saludar();
    }
}
