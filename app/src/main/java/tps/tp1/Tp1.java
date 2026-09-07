package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

public class Tp1 {
    //Archivos
    private static final String ENTRADA_POR_DEFECTO = "src/main/java/tps/tp1/datos/prestamos.csv";
    private static final String SALIDA_POR_DEFECTO = "src/main/java/tps/tp1/salida";
    private static final String NOMBRE_ARCHIVO_REPORTE = "reporte";
    private static final String NOMBRE_ARCHIVO_RANKING = "ranking";
    //fechas
    private static final String CORTE_POR_DEFECTO = "2026-05-04";

    //Msg
    private static final String USR_MSG_RESUMEN_DE_CARGA = "Lineas de datos: %d | validas: %d | descartadas: %d%n";
    //Error MSG
    private static final String ERROR_MSG_VALIDAR_ENTRADAS_DEMASIADAS_ENTRADAS = "Se permiten como máximo 2 argumentos";
    private static final String ERROR_MSG_VALIDAR_ENTRADAS_FECHA_INVALIDA = "La fecha de corte debe tener formato ISO (YYYY-MM-DD).";

    //CTES
    private static final int RANKING_CANT_TITULOS = 3;

    //Record para manejar los datos con los que se ejecuta el programa
    private record Entradas(Path archivo, LocalDate fecha) {}

    public static void main(String[] args) throws IOException {
        
        Entradas entrada = validarEntradas(args, ENTRADA_POR_DEFECTO, CORTE_POR_DEFECTO);

        ResultadoDeCarga carga = LectorDePrestamos.cargar(entrada.archivo);
    
        //tomo el registro de carga
        RegistroDePrestamos registro = carga.registro();

        //Print resumen de carga
        System.out.printf(
                    USR_MSG_RESUMEN_DE_CARGA,
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
                entrada.fecha
        );

        // Exportador TXT
        ExportadorDeReporte exportadorTxt = new ExportadorTXT();
        exportarDatos(exportadorTxt, filas, registro);

        // Exportador CSV
        ExportadorDeReporte exportadorCsv = new ExportadorCSV();
        exportarDatos(exportadorCsv, filas, registro);

        // Exportador Xlsx
        ExportadorDeReporte exportadorXlsx = new ExportadorXlsx();
        exportarDatos(exportadorXlsx, filas, registro);        
    }

    //Metodos privados

    private static void exportarDatos(
        ExportadorDeReporte exportador, 
        FilaDeSocio[] filas, 
        RegistroDePrestamos registro) throws IOException{
        
        exportador.exportar(
                filas,
                Path.of(SALIDA_POR_DEFECTO+ '/' + NOMBRE_ARCHIVO_REPORTE + '.' + exportador.extension())
        );

        //Imprimo los rankings
        exportador.exportarRanking(registro.titulosMasPedidos(RANKING_CANT_TITULOS), 
                                        Path.of(SALIDA_POR_DEFECTO + 
                                        '/' + 
                                        NOMBRE_ARCHIVO_RANKING + 
                                        '.' + 
                                        exportador.extension()
                                    )
                                );
    }

    private static Entradas validarEntradas(String[] args, String entradaPorDefecto, String cortePorDefecto){
        // args[0] = archivo de entrada (opcional)
        // args[1] = fecha de corte ISO (opcional)

        if (args.length > 2){
            throw new IllegalArgumentException(ERROR_MSG_VALIDAR_ENTRADAS_DEMASIADAS_ENTRADAS);
        }

        Path archivo = Path.of(entradaPorDefecto);
        LocalDate fechaDeCorte = LocalDate.parse(cortePorDefecto);

        //No valida que el archivo exista, eso lo hace LectorDePrestamo
        if (args.length >= 1){
            archivo = Path.of(args[0]);
        }

        if (args.length >= 2) {
        try {
            fechaDeCorte = LocalDate.parse(args[1]);
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(ERROR_MSG_VALIDAR_ENTRADAS_FECHA_INVALIDA);
        }
    }

        return new Entradas(archivo, fechaDeCorte);
    }
}