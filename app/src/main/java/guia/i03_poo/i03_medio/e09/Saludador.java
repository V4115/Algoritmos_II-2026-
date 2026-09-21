package guia.i03_poo.i03_medio.e09;
/**
 * 9. Método default. Interfaz con un método default; una implementación; test.
 */

/**
 * Interfaz con un método DEFAULT. El método default provee una implementación
 * ya lista que usa el método abstracto nombre(): las clases que implementan
 * la interfaz sólo deben definir nombre() y heredan gratis saludar().
 */
public interface Saludador {

    /** Método abstracto: cada implementación aporta su nombre. */
    String nombre();

    /** Método default: comportamiento reutilizable basado en nombre(). */
    default String saludar() {
        return "Hola, soy " + nombre();
    }
}
