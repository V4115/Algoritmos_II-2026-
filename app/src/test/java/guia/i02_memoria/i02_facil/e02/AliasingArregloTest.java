package guia.i02_memoria.i02_facil.e02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;  

public class AliasingArregloTest {

    @Test
    @DisplayName("Test de aliasing de arreglo")
    void testAliasingArreglo() {
        int[] array = {1, 2, 3, 4, 5};
        int[] alias = AliasingArreglo.createAlias(array);

        // Change the first element of the alias
        alias[0] = 99;

        // Verify that the original array has also changed
        assertEquals(99, array[0], "El primer elemento del arreglo original debería ser 99 después de modificar el alias.");
    }

    @Test
    @DisplayName("Test de copia de arreglo")
    void testCopiaArreglo() {
        int[] array = {1, 2, 3, 4, 5};
        int[] copy = AliasingArreglo.createCopy(array);

        // Change the first element of the copy
        copy[0] = 100;

        // Verify that the original array has not changed
        assertEquals(1, array[0], "El primer elemento del arreglo original debería ser 1 después de modificar la copia.");
    }
}