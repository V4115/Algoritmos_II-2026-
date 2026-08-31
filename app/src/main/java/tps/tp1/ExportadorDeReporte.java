package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Path;

public interface ExportadorDeReporte {
    void exportar(FilaDeSocio[] filas, Path destino) throws IOException;
    String extension(); // "txt", "csv", ...
}
