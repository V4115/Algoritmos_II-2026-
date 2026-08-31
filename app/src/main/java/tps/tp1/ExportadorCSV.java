
package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportadorCSV implements ExportadorDeReporte {
    private static final String EXTENSION_MSG = ".csv";
    private static final String PADRON_MSG = "Padron";
    private static final String SOCIO_MSG = "Socio";
    private static final String PRESTAMOS_MSG = "Prestamos";
    private static final String DIAS_ATRASO_MSG = "DiasAtraso";
    private static final String MULTA_ESTADO_MSG = "Multa Estado";
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
        return "csv";
    }
}
