package tps.tp1;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RegistroSobreArregloTest{

    @Test
    public void testRegistroSobreArreglo() {
        
        //Verificar que crece más allá de la capacidad inicial
        RegistroSobreArreglo registro = new RegistroSobreArreglo();
        for (int i = 0; i < 100; i++) {
            registro.registrar(new Prestamo(
                LocalDate.parse("2026-04-06"), 
                i%10 + 1, 
                "Martin Perez", 
                "978-3-16-148410-0", 
                "El Gran Gatsby", null
                ));
        }
        assert registro.cantidad() == 100 : "Debería ser 100";
        
        //Verificar que no hay padrones repetidos
        int []padrones = registro.padrones();
        HashSet <Integer> vistos = new HashSet<>();

        for (int actual : padrones) {
            assert (vistos.add(actual) == true) : "Elemento repetido en el arreglo";
        }

        //PrestamoDe con padrón inexistenete devuelve un arreglo vacío
        //-1 siempre es un padron inexistente
        Prestamo[] prestamosPadronInexistente = registro.prestamosDe(-1);
        assert prestamosPadronInexistente.length == 0;

        //Ante un empate en el ranking desempata alfabéticamente

        registro = new RegistroSobreArreglo();

        //3 Prestamos de "Programación en C"
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                101, 
                "Martin Perez", 
                "978-3-16-148410-0", 
                "Programación en C", null
                ));
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                102, 
                "Gimena Caccia", 
                "978-3-16-148510-0", 
                "Programación en C", null
                ));
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                103, 
                "Lautaro Varessi", 
                "978-3-16-248410-0", 
                "Programación en C", null
                ));

        //3 registros de "Algoritmos"
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                101, 
                "Martin Perez", 
                "978-3-16-148410-0", 
                "Algoritmos", null
                ));
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                102, 
                "Gimena Caccia", 
                "978-3-16-148510-0", 
                "Algoritmos", null
                ));
        registro.registrar(new Prestamo(LocalDate.parse("2026-04-06"), 
                103, 
                "Lautaro Varessi", 
                "978-3-16-248410-0", 
                "Algoritmos", null
                ));

        String[] titulosMasPedidos = registro.titulosMasPedidos(2);

        assert titulosMasPedidos[0].equals("Algoritmos");
    }
}