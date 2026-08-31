package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportadorTXT implements ExportadorDeReporte {

    private static final String EXTENSION_MSG = ".txt";
    private static final String PADRON_MSG = "Padron";
    private static final String SOCIO_MSG = "Socio";
    private static final String PRESTAMOS_MSG = "Prestamos";
    private static final String DIAS_ATRASO_MSG = "DiasAtraso";
    private static final String MULTA_MSG = "Multa";
    private static final String ESTADO_MSG = "Estado";
    private static final String SEPARACION_MSG = "--------------------------------------------------------------------\n";
    
    @Override
    public void exportar(FilaDeSocio[] filas, Path destino) throws IOException {

        StringBuilder texto = new StringBuilder();

        texto.append(String.format("%-8s %-20s %10s %12s %10s %-12s%n", 
                PADRON_MSG, 
                SOCIO_MSG, 
                PRESTAMOS_MSG, 
                DIAS_ATRASO_MSG, 
                DIAS_ATRASO_MSG, 
                MULTA_MSG, 
                ESTADO_MSG
                ));
        
        for (FilaDeSocio fila : filas) { 
            texto.append(String.format( "%-8d %-20s %10d %12d %10d %-12s%n", 
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
    public String extension(){
        return EXTENSION_MSG;
    }
}
