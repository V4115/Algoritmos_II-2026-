
package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportadorCSV implements ExportadorDeReporte {
    private static final String EXTENSION_MSG = "csv";
    private static final String PADRON_MSG = "Padron";
    private static final String SOCIO_MSG = "Socio";
    private static final String PRESTAMOS_MSG = "Prestamos";
    private static final String DIAS_ATRASO_MSG = "DiasAtraso";
    private static final String MULTA_MSG = "Multa";
    private static final String ESTADO_MSG = "Estado";
    private static final String RANKING_TITULOS_MAS_POPULARES_MSG = "Titulos más populares";
    private static final String RANKING_CANTIDAD_MSG = "Cantidad de pedidos";
    private static final String DELIM = ";";

    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {

        StringBuilder texto = new StringBuilder();

        texto.append(
            PADRON_MSG + DELIM + 
            SOCIO_MSG + DELIM  + 
            PRESTAMOS_MSG + DELIM + 
            DIAS_ATRASO_MSG + DELIM + 
            MULTA_MSG + DELIM +
            ESTADO_MSG + '\n');

        for (FilaDeSocio fila : filas) {
            texto.append(String.format(
                    "%d" + DELIM + 
                    "%s" + DELIM + 
                    "%d" + DELIM + 
                    "%d" + DELIM + 
                    "%d" + DELIM + 
                    "%s" + "%n",
                    fila.padron(),
                    fila.socio(),
                    fila.prestamos(),
                    fila.diasDeAtraso(),
                    fila.multa(),
                    fila.estado()
            ));
        }

        Files.writeString(destino, texto.toString());
    }

    @Override
    public String extension() {
        return EXTENSION_MSG;
    }

    @Override
    public void exportarRanking(String[]ranking, Path destino) throws IOException{

        Object[][] datos = obtenerDatosDelRanking(ranking);

         StringBuilder texto = new StringBuilder();

        texto.append(RANKING_TITULOS_MAS_POPULARES_MSG + DELIM + RANKING_CANTIDAD_MSG +  '\n');

        //entiendo que no es lo ideal, pero me limite el formato de ranking que me piden

        for (Object[] fila : datos) {
        // Escribir como CSV
        texto.append(fila[0])
             .append(";")
             .append(fila[1])
             .append(System.lineSeparator());
    }

        Files.writeString(destino, texto.toString());
    }
    
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
