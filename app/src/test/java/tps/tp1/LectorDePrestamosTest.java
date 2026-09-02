package tps.tp1;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 *
 * @author jose
 */

public class LectorDePrestamosTest{

    //archivos
    private static final String ENTRADA_POR_DEFECTO = "src/main/java/tps/tp1/datos/prestamos.csv";
    private static final int CANT_SALIDA_VALIDA = 18;
    private static final int CANT_SALIDA_INVALIDA = 4;

    @Test
    public void testLectorDePrestamosSalida() throws IOException{
        ResultadoDeCarga carga = LectorDePrestamos.cargar(Path.of(ENTRADA_POR_DEFECTO));
        RegistroDePrestamos registro = carga.registro();

        assert carga.lineasDeDatos() == CANT_SALIDA_INVALIDA + CANT_SALIDA_VALIDA;
        assert registro.cantidad() == CANT_SALIDA_VALIDA;
        assert carga.errores().length == CANT_SALIDA_INVALIDA;
    }
}