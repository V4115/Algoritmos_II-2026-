package utils.pilas.pila_arreglo_dinamica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PilaArregloDinamicaTest<T>{

    @Test
    @DisplayName ("Pila recién creada está vacía y tiene tamaño 0")
    void pilaNuevaEstaVacia(){
        int lenInicial = 4, factorRedim = 2;

        PilaArregloDinamica<Integer> pila = new PilaArregloDinamica<>(lenInicial, factorRedim);

        assertTrue (pila.estaVacia() , "La pila debería estar vacía");
        assertEquals(0, pila.getCantidad() , "La pila debería tener cantidad utilizada de valor 0"); 
    }

    @Test 
    @DisplayName ("Verificar LIFO")
    void verificaLifo(){
        int lenInicial = 4, factorRedim = 2;

        PilaArregloDinamica<Integer> pila = new PilaArregloDinamica<>(
                                                    lenInicial, 
                                                    factorRedim
                                                    );
        for (int i = 0; i < 10; i++) {
            pila.apilar(i);
        }

        for(int i=9; i >= 0; i-- ){
            assertEquals(i, pila.desapilar());
        }
    }

    @Test
    @DisplayName ("Verificar funcionamiento de redimension")
    void pilaRedimension(){
        int lenInicial = 4, factorRedim = 2;

        PilaArregloDinamica<Integer> pila = new PilaArregloDinamica<>(
                                                    lenInicial, 
                                                    factorRedim
                                                    );

        for (int i = 0; i < 10; i++) {
            pila.apilar(i);
        }

        assertEquals(10, pila.getCantidad());


        for(int i=9; i >= 0; i-- ){
            assertEquals(i, pila.desapilar());
        }

        assertTrue(pila.estaVacia());

        pila.apilar(1);

        assertEquals(1, pila.getCantidad());

        assertTrue(!pila.estaVacia());
    }
}