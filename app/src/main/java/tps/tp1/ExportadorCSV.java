
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
    private static final String MULTA_ESTADO_MSG = "Multa Estado";
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
            MULTA_ESTADO_MSG + '\n');

        for (FilaDeSocio fila : filas) {
            texto.append(String.format(
                    "%d" + DELIM + 
                    "%s" + DELIM + 
                    "%d" + DELIM + 
                    "%d" + DELIM + 
                    "%d" + DELIM + 
                    "%s" + DELIM + 
                    "%n",
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
         StringBuilder texto = new StringBuilder();

        texto.append(RANKING_TITULOS_MAS_POPULARES_MSG + DELIM + RANKING_CANTIDAD_MSG +  '\n');

        //entiendo que no es lo ideal, pero me limite el formato de ranking que me piden

        for (String elemento : ranking) {

        // Buscar el último espacio
        int posicionCantidad = elemento.lastIndexOf(" ");

        // Separar título y cantidad
        String titulo = elemento.substring(0, posicionCantidad).trim();
        String cantidad = elemento.substring(posicionCantidad + 1).trim();

        // Escribir como CSV
        texto.append(titulo)
             .append(";")
             .append(cantidad)
             .append(System.lineSeparator());
    }

        Files.writeString(destino, texto.toString());
    }
}
