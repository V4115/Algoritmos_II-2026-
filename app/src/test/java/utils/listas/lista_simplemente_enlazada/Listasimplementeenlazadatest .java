package utils.listas.lista_simplemente_enlazada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListaSimplementeEnlazadaTest {

    private ListaSimplementeEnlazada<String> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaSimplementeEnlazada<>();
    }

    private ListaSimplementeEnlazada<String> de(String... datos) {
        ListaSimplementeEnlazada<String> l = new ListaSimplementeEnlazada<>();
        for (String d : datos) {
            l.agregar(d);
        }
        return l;
    }

    private void assertContenido(ListaSimplementeEnlazada<String> l, String... esperado) {
        assertEquals(esperado.length, l.getTamanio());
        for (int i = 0; i < esperado.length; i++) {
            assertEquals(esperado[i], l.obtener(i), "posicion " + i);
        }
    }

    // ---------- estado inicial ----------

    @Test
    void listaNuevaEstaVacia() {
        assertTrue(lista.estaVacia());
        assertEquals(0, lista.getTamanio());
    }

    // ---------- agregar / agregarAlInicio ----------

    @Test
    void agregarMantieneOrden() {
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        assertContenido(lista, "A", "B", "C");
        assertFalse(lista.estaVacia());
    }

    @Test
    void agregarAlInicioInvierteOrden() {
        lista.agregarAlInicio("A");
        lista.agregarAlInicio("B");
        lista.agregarAlInicio("C");
        assertContenido(lista, "C", "B", "A");
    }

    @Test
    void agregarAlInicioEnVaciaActualizaUltimo() {
        lista.agregarAlInicio("A");
        lista.agregar("B"); // si ultimo no se actualizo, esto falla
        assertContenido(lista, "A", "B");
    }

    @Test
    void agregarNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> lista.agregar(null));
        assertThrows(NullPointerException.class, () -> lista.agregarAlInicio(null));
        assertEquals(0, lista.getTamanio());
    }

    // ---------- insertar ----------

    @Test
    void insertarAlPrincipio() {
        lista = de("A", "B");
        lista.insertar(0, "X");
        assertContenido(lista, "X", "A", "B");
    }

    @Test
    void insertarAlFinal() {
        lista = de("A", "B");
        lista.insertar(2, "X");
        assertContenido(lista, "A", "B", "X");
        lista.agregar("Y"); // ultimo debe seguir bien
        assertContenido(lista, "A", "B", "X", "Y");
    }

    @Test
    void insertarEnElMedio() {
        lista = de("A", "B", "C");
        lista.insertar(1, "X");
        assertContenido(lista, "A", "X", "B", "C");
    }

    @Test
    void insertarEnListaVaciaEnCero() {
        lista.insertar(0, "X");
        assertContenido(lista, "X");
    }

    @Test
    void insertarFueraDeRangoLanzaExcepcion() {
        lista = de("A", "B");
        assertThrows(IndexOutOfBoundsException.class, () -> lista.insertar(-1, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.insertar(3, "X"));
        assertEquals(2, lista.getTamanio());
    }

    @Test
    void insertarNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> lista.insertar(0, null));
    }

    // ---------- obtener ----------

    @Test
    void obtenerFueraDeRangoLanzaExcepcion() {
        lista = de("A", "B");
        assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(2));
    }

    @Test
    void obtenerEnListaVaciaLanzaExcepcion() {
        assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(0));
    }

    // ---------- eliminar ----------

    @Test
    void eliminarElPrimero() {
        lista = de("A", "B", "C");
        assertEquals("A", lista.eliminar(0));
        assertContenido(lista, "B", "C");
    }

    @Test
    void eliminarElUltimoActualizaUltimo() {
        lista = de("A", "B", "C");
        assertEquals("C", lista.eliminar(2));
        assertContenido(lista, "A", "B");
        lista.agregar("D"); // si ultimo quedo mal, D se pierde
        assertContenido(lista, "A", "B", "D");
    }

    @Test
    void eliminarEnElMedio() {
        lista = de("A", "B", "C");
        assertEquals("B", lista.eliminar(1));
        assertContenido(lista, "A", "C");
    }

    @Test
    void eliminarUnicoElementoDejaListaVaciaYUtilizable() {
        lista = de("A");
        assertEquals("A", lista.eliminar(0));
        assertTrue(lista.estaVacia());
        lista.agregar("B");
        assertContenido(lista, "B");
    }

    @Test
    void eliminarFueraDeRangoLanzaExcepcion() {
        lista = de("A", "B");
        assertThrows(IndexOutOfBoundsException.class, () -> lista.eliminar(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.eliminar(2));
        assertEquals(2, lista.getTamanio());
    }

    @Test
    void eliminarEnListaVaciaLanzaExcepcion() {
        assertThrows(IndexOutOfBoundsException.class, () -> lista.eliminar(0));
    }

    // ---------- indiceDe / contiene ----------

    @Test
    void indiceDeDevuelveLaPrimeraAparicion() {
        lista = de("A", "B", "A");
        assertEquals(0, lista.indiceDe("A"));
        assertEquals(1, lista.indiceDe("B"));
    }

    @Test
    void indiceDeInexistenteDevuelveMenosUno() {
        lista = de("A", "B");
        assertEquals(-1, lista.indiceDe("Z"));
        assertEquals(-1, new ListaSimplementeEnlazada<String>().indiceDe("A"));
    }

    @Test
    void indiceDeUsaEquals() {
        lista = de("hola");
        assertEquals(0, lista.indiceDe(new String("hola")));
    }

    @Test
    void contiene() {
        lista = de("A", "B");
        assertTrue(lista.contiene("A"));
        assertTrue(lista.contiene("B"));
        assertFalse(lista.contiene("Z"));
        assertFalse(new ListaSimplementeEnlazada<String>().contiene("A"));
    }

    @Test
    void indiceDeYContieneConNullLanzanExcepcion() {
        assertThrows(NullPointerException.class, () -> lista.indiceDe(null));
        assertThrows(NullPointerException.class, () -> lista.contiene(null));
    }

    // ---------- agregarTodos ----------

    @Test
    void agregarTodosAgregaAlFinalEnOrden() {
        lista = de("A", "B");
        lista.agregarTodos(de("C", "D"));
        assertContenido(lista, "A", "B", "C", "D");
    }

    @Test
    void agregarTodosDeListaVaciaNoCambiaNada() {
        lista = de("A");
        lista.agregarTodos(new ListaSimplementeEnlazada<>());
        assertContenido(lista, "A");
    }

    @Test
    void agregarTodosEnVaciaCopiaElContenido() {
        lista.agregarTodos(de("A", "B"));
        assertContenido(lista, "A", "B");
    }

    @Test
    void agregarTodosNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> lista.agregarTodos(null));
    }

    // Si corregis agregarTodos para guardar el tamanio antes del for,
    // este test pasa; con el codigo actual se cuelga (bucle infinito).
    @Test
    void agregarTodosConSigoMismaDuplicaElContenido() {
        lista = de("A", "B");
        assertTimeoutPreemptively(java.time.Duration.ofSeconds(2), () -> lista.agregarTodos(lista));
        assertContenido(lista, "A", "B", "A", "B");
    }
}