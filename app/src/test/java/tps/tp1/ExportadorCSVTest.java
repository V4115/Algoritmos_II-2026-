package tps.tp1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author jose
 */

public class ExportadorCSVTest{

    private static final String TEST_EXPORTADOR_CSV_CABECERA = "Padron;Socio;Prestamos;DiasAtraso;Multa;Estado";

    @Test 
    public void testEscrituraYLecturaDeArchivos() throws IOException{
        
        //Path del archivo temporal
        Path archivoTemp = Files.createTempFile("test-reporte-", ".csv");

        try {
            //Exportador
            ExportadorDeReporte exportadorCsv = new ExportadorCSV();
            

            //Filas a escribir en el archivo temporal
            FilaDeSocio[] filas = {
                new FilaDeSocio(12345, "Juan Perez", 2, 3, 150, "ATRASADO"),
                new FilaDeSocio(67890, "Ana Lopez", 1, 0, 0, "AL DIA")
            };

            //Escribir en el archivo
            exportadorCsv.exportar(filas, archivoTemp);

            //leo las lineas del archivo temporal
            List<String> contenido = Files.readAllLines(archivoTemp);

            //Lineas esperadas del archivo
            List<String> esperado = List.of(
                TEST_EXPORTADOR_CSV_CABECERA,
                "12345;Juan Perez;2;3;150;ATRASADO",
                "67890;Ana Lopez;1;0;0;AL DIA"
            );

            //cpmaración obtenido con esperado
            assertEquals(esperado, contenido);
        } finally {
            //Files.deleteIfExists(archivoTemp);
            System.out.println("Archivo temporal: " + archivoTemp.toAbsolutePath());
        }
    }
}