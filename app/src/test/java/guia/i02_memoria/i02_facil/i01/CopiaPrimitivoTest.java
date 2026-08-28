package guia.i02_memoria.i02_facil.i_01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import guia.i02_memoria.i02_facil.i01.CopiaPrimitivo;

class CopiaPrimitivoTest {

    @Test
    @DisplayName("Test copiaPrimitivo() con valor positivo")
    void testCopiaPrimitivoPositivo() {
        int a = 5;
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(5, resultado[0]);
        assertEquals(99, resultado[1]);
    }

    @Test
    @DisplayName("Test copiaPrimitivo() con valor negativo")
    void testCopiaPrimitivoNegativo() {
        int a = -10;
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(-10, resultado[0]);
        assertEquals(99, resultado[1]);
    }

    @Test
    @DisplayName("Test copiaPrimitivo() con valor cero")
    void testCopiaPrimitivoCero() {
        int a = 0;
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(0, resultado[0]);
        assertEquals(99, resultado[1]);
    }

    @Test
    @DisplayName("Test copiaPrimitivo() con valor máximo de int")
    void testCopiaPrimitivoMaxInt() {
        int a = Integer.MAX_VALUE;
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(Integer.MAX_VALUE, resultado[0]);
        assertEquals(99, resultado[1]);
    }

    @Test
    @DisplayName("Test copiaPrimitivo() con valor mínimo de int")
    void testCopiaPrimitivoMinInt() {
        int a = Integer.MIN_VALUE;
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(Integer.MIN_VALUE, resultado[0]);
        assertEquals(99, resultado[1]); 
    }

    @Test
    @DisplayName("Test copiaPrimitivo() con valor aleatorio")
    void testCopiaPrimitivoAleatorio() {
        int a = (int) (Math.random() * 1000);
        int[] resultado = CopiaPrimitivo.copiaPrimitivo(a);
        assertEquals(a, resultado[0]);
        assertEquals(99, resultado[1]);
    }

}