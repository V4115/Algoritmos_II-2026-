package guia.i01_intro.i03_medio.e01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MaximoTest{

    @Test
    @DisplayName("Test maximo() con valores positivos")
    void testMaximoPositivos() {
        assertEquals(5, Maximo.maximo(new int[]{3, 5, 2, 2, 5, 3}));
        assertEquals(10, Maximo.maximo(new int[]{10, 7, 0, 2, 1, 2, 5}));
        assertEquals(15, Maximo.maximo(new int[]{15, 15, 15, 15}));
    }

    @Test
    @DisplayName("Test maximo() con valores negativos")
    void testMaximoNegativos() {
        assertEquals(-1, Maximo.maximo(new int[]{-3, -1, - 3, -2, -5}));
        assertEquals(-5, Maximo.maximo(new int[]{-10, -5, -13, -20}));
        assertEquals(-2, Maximo.maximo(new int[]{-2, -2, -2, -2}));
    }

    @Test
    @DisplayName("Test maximo() con valores mixtos")
    void testMaximoMixtos() {
        assertEquals(0, Maximo.maximo(new int[]{-1, 0, -3, -2}));
        assertEquals(3, Maximo.maximo(new int[]{3, -3, 3, -3, 3, -3}));
        assertEquals(19, Maximo.maximo(new int[]{-7, 7, 19, 10, -2}));
    }

    @Test
    @DisplayName("Test maximo() con un solo elemento")
    void testMaximoUnSoloElemento() {
        assertEquals(5, Maximo.maximo(new int[]{5}));
        assertEquals(-1, Maximo.maximo(new int[]{-1}));
        assertEquals(0, Maximo.maximo(new int[]{0}));
    }

    @Test
    @DisplayName("Test maximo() con arreglo vacío")
    void testMaximoArregloVacio() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            Maximo.maximo(new int[]{});
        });
        assertEquals(Maximo.ERROR_MSG_EMPTY_ARRAY, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            Maximo.maximo(null);
        });
        assertEquals(Maximo.ERROR_MSG_EMPTY_ARRAY, e.getMessage()); 
    }
}