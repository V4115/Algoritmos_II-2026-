package guia.i02_memoria.i02_facil.e07;

/**
 * 7. Record inmutable. record Punto(int x, int y) con conX(int) que devuelva un nuevo Punto;
test que el original no cambia.
 */

public record Punto(int x, int y) {

    public static void main(String[] args) {
        Punto original = new Punto(1, 2);
        System.out.println("Original: " + original);

        Punto modificado = original.conX(10);
        System.out.println("Modificado: " + modificado);
        System.out.println("Original después de modificar: " + original);
    }

    /**
     * Devuelve un nuevo Punto con la coordenada x cambiada.
     *
     * @param nx nueva coordenada x
     * @return nuevo Punto(nx, y); el original no se modifica
     */
    
    public Punto conX(int nuevoX) {
        return new Punto(nuevoX, this.y);
    }
}