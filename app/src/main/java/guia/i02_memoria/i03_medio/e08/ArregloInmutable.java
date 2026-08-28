package guia.i02_memoria.i03_medio.e08;

/**
 * 8. Envoltura inmutable. Clase ArregloInmutable que reciba un int[], lo copie, y sólo permita
leer (getter que devuelve copia); test de inmutabilidad.
 */

public class ArregloInmutable {
    private final int[] arreglo;

    //Constructor
    public ArregloInmutable(int[] arreglo) {
        // Defensive copy of the input array to prevent external modification
        this.arreglo = arreglo.clone();
    }
    
    //Getters
    public int[] getArreglo() {
        // Return a copy of the internal array to prevent external modification
        return arreglo.clone();
    }
    public int getElemento(int index) {
        if (index < 0 || index >= arreglo.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + arreglo.length);
        }
        return arreglo[index];
    }
    public int getLength() {
        return arreglo.length;
    }

    @Override
    public String toString() {
        return "Arreglo["+java.util.Arrays.toString(arreglo)+']';
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        ArregloInmutable arregloInmutable = new ArregloInmutable(original);
        int[] copy = arregloInmutable.getArreglo();

        System.out.println("Antes de mutar el arreglo externo: " + arregloInmutable);
        
        // Mutate the external array
        original[0] = 99;
        //Mutate the copy of the internal array
        copy[1] = 88;

        System.out.println("Después de mutar el arreglo externo y la copia: " + arregloInmutable);
        for (int i = 0; i < arregloInmutable.getLength(); i++) {
            System.out.println("Elemento en índice " + i + ": " + arregloInmutable.getElemento(i));
        }
        System.out.println("Longitud del arreglo: " + arregloInmutable.getLength());    
    }
}