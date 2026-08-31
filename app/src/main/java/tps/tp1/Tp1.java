package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

public class Tp1 {
    private static final String ENTRADA_POR_DEFECTO = "src/main/java/tps/tp1/datos/prestamos.csv";
    private static final String SALIDA_POR_DEFECTO = "src/main/java/tps/tp1/salida";
    private static final LocalDate CORTE_POR_DEFECTO = LocalDate.parse("2026-05-04");

    public static void main(String[] args) throws IOException {
    // args[0] = archivo de entrada (opcional)
    // args[1] = fecha de corte ISO (opcional)

    ResultadoDeCarga carga = LectorDePrestamos.cargar(Path.of(ENTRADA_POR_DEFECTO));
    
    //tomo el registro de carga
    RegistroDePrestamos registro = carga.registro();

    //Print resumen de carga
    System.out.printf(
                "Lineas de datos: %d | validas: %d | descartadas: %d%n",
                carga.lineasDeDatos(),
                registro.cantidad(),
                carga.errores().length
        );

        // Muestro errores
        for (String error : carga.errores()) {
            System.out.println(error);
        }
    
    // Genero las filas del reporte
        FilaDeSocio[] filas = Reporteador.porSocio(
                registro,
                CORTE_POR_DEFECTO
        );

        // Exportador TXT
        ExportadorDeReporte exportadorTxt = new ExportadorTXT();

        exportadorTxt.exportar(
                filas,
                Path.of(SALIDA_POR_DEFECTO+ '/' + "reporte." + exportadorTxt.extension())
        );

        // Exportador CSV
        ExportadorDeReporte exportadorCsv = new ExportadorCSV();

        exportadorCsv.exportar(
                filas,
                Path.of(SALIDA_POR_DEFECTO + '/' + "reporte." + exportadorCsv.extension())
        );
    }
}