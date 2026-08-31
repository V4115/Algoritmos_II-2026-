

package tps.tp1;

/**
 *
 * @author jose
 */

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

//FilaDeSocio(int padron, String socio, int prestamos, int diasDeAtraso, int multa, String estado)
public class Reporteador {

    private static final String ESTADO_MSG_DEUDA = "CON_DEUDA";
    private static final String ESTADO_MSG_SIN_DEUDA = "AL_DIA";

    public static FilaDeSocio[] porSocio(RegistroDePrestamos r, LocalDate corte) {
        
        FilaDeSocio[] filas = new FilaDeSocio[r.padrones().length];
        
        //tomo todos los padrones
        int[] padrones = r.padrones();

        for (int i = 0; i < padrones.length; i++){
        
            //Obtengo todos los pedidos de c/u
            Prestamo[] prestamosDe = r.prestamosDe(padrones[i]);

            //todos los prestamos pertenecen a la misma persona
            String nombre = prestamosDe[0].getSocio();

            //obtengo la deuda del padrón i

            int deuda = 0, diasDeAtraso = 0;

            for(int j = 0; j < prestamosDe.length; j++){
                deuda += prestamosDe[j].multa(corte);
                diasDeAtraso += prestamosDe[j].diasDeAtraso(corte);
            }
            filas[i] = new FilaDeSocio(padrones[i], nombre, prestamosDe.length, diasDeAtraso, deuda, (deuda == 0)? ESTADO_MSG_SIN_DEUDA: ESTADO_MSG_DEUDA);
        }

        //Reordeno la el arreglo de filas
        Arrays.sort(filas, Comparator.comparingInt(FilaDeSocio::multa).reversed().thenComparing(FilaDeSocio::socio));
        return filas;
    }
    public static String[] ranking(RegistroDePrestamos r, int n) {
        return r.titulosMasPedidos(n);
    }
}
