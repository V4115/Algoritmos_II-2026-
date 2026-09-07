package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Path;
import librerias.excel.ExcelUtils;

public class ExportadorXlsx implements ExportadorDeReporte{
    
    private static final String RANKING_MSG = "Ranking";
    private static final String EXTENSION_MSG = "Xlsx";
    private static final String PADRON_MSG = "Padron";
    private static final String SOCIO_MSG = "Socio";
    private static final String PRESTAMOS_MSG = "Prestamos";
    private static final String DIAS_ATRASO_MSG = "DiasAtraso";
    private static final String MULTA_MSG = "Multa";
    private static final String ESTADO_MSG = "Estado";
    private static final String RANKING_TITULOS_MAS_POPULARES_MSG = "Titulos más populares";
    private static final String RANKING_CANTIDAD_MSG = "Cantidad de pedidos";

    @Override 
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {
        
        String[] encabezados = {PADRON_MSG, SOCIO_MSG, PRESTAMOS_MSG, DIAS_ATRASO_MSG, MULTA_MSG, ESTADO_MSG};
        Object[][] datos = new Object[filas.length][];
        for (int f = 0; f < filas.length; f++) {
        FilaDeSocio fila = filas[f];
        datos[f] = new Object[]{fila.padron(), fila.socio(), fila.prestamos(),
        fila.diasDeAtraso(), fila.multa(), fila.estado()};
        }
        ExcelUtils.escribir(destino, "Multas", encabezados, datos);
    }

    @Override
    public String extension() {
        return EXTENSION_MSG;
    }

    @Override
    public void exportarRanking(String[]ranking, Path destino) throws IOException{
        String[] encabezados = {
            RANKING_TITULOS_MAS_POPULARES_MSG, RANKING_CANTIDAD_MSG
        };

        Object [][] datos = obtenerDatosDelRanking(ranking);

        ExcelUtils.escribir(destino, RANKING_MSG, encabezados, datos);
    }
    
    //Obtiene los datos del string Rankings 
    @Override
    public Object[][] obtenerDatosDelRanking(String[] ranking) {

        Object[][] datos = new Object[ranking.length][];

        for (int i = 0; i < ranking.length; i++) {
            String actual = ranking[i];

            // Busco el último espacio
            int posCant = actual.lastIndexOf(" ");

            // Obtengo los datos de la línea
            datos[i] = new Object[]{
                actual.substring(0, posCant).trim(),
                Integer.parseInt(actual.substring(posCant + 1).trim())
            };
        }

        return datos;
    }
}