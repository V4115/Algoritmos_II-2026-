package guia.i03_poo.i03_medio.e08;
/**
 * 8. Método plantilla. Clase abstracta con un método concreto que llama a pasos abstractos que
definen las subclases; test.
 */
/** Subclase concreta que completa los pasos abstractos para preparar un té. */
public class Te extends Bebida {

    @Override
    protected String agregarPrincipal() {
        return "Poner saquito de té en infusión";
    }

    @Override
    protected String servir() {
        return "Servir en taza con rodaja de limón";
    }

    public static void main(String[] args) {
        Bebida te = new Te();
        System.out.println(te.preparar());
    }
}
