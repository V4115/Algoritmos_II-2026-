package guia.i03_poo.i03_medio.e08;
/**
 * 8. Método plantilla. Clase abstracta con un método concreto que llama a pasos abstractos que
definen las subclases; test.
 */
/** Otra subclase concreta: prepara un café reutilizando el mismo esqueleto. */
public class Cafe extends Bebida {

    @Override
    protected String agregarPrincipal() {
        return "Filtrar café molido";
    }

    @Override
    protected String servir() {
        return "Servir en pocillo con azúcar aparte";
    }
}
