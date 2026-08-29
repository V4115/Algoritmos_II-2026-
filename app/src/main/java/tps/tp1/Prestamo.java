package tps.tp1;

/**
 *
 * @author jose
 */

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un préstamo de un libro en una biblioteca.
 * Contiene información sobre la fecha de retiro, padrón del socio, nombre del socio, ISBN del libro, título del libro y fecha de devolución.
 * Proporciona métodos para verificar si el préstamo está pendiente, calcular la fecha de vencimiento, los días de atraso y la multa correspondiente.
 */

public record Prestamo(LocalDate retiro, int padron, String socio, 
String isbn, String titulo, LocalDate devolucion) {

    // Constantes para la lógica de negocio
    private static final int DIAS_PLAZO_PRESTAMO = 14;
    private static final int MULTA_POR_DIA = 150;
    private static final int MULTA_MAXIMA = 3000;
    
    //Msg de error
    private static final String ERROR_MSG_RETIRO_NULO = "La fecha de retiro es obligatoria.";
    private static final String ERROR_MSG_DEVOLUCION_ANTERIOR_RETIRO = "La fecha de devolución no puede ser anterior a la fecha de retiro.";
    private static final String ERROR_MSG_PADRON_NEGATIVO = "El padrón debe ser un número positivo.";
    private static final String ERROR_MSG_SOCIO_NULO = "El nombre del socio es obligatorio.";
    private static final String ERROR_MSG_ISBN_NULO = "El ISBN es obligatorio.";
    private static final String ERROR_MSG_TITULO_NULO = "El título es obligatorio.";


    // Constructor con validación de campos
    public Prestamo {
        validarCampos(retiro, padron, socio, isbn, titulo, devolucion);
    }
    
    /**
     * Valida los campos del préstamo y lanza excepciones si alguno de ellos no cumple con las condiciones establecidas.
     * @param retiro la fecha de retiro del libro.
     * @param padron el padrón del socio.
     * @param socio el nombre del socio.
     * @param isbn el ISBN del libro.
     * @param titulo el título del libro.
     * @param devolucion la fecha de devolución del libro.
     * @throws IllegalArgumentException si algún campo no cumple con las condiciones establecidas.
     */
    private static void validarCampos(LocalDate retiro, int padron, String socio, String isbn, String titulo, LocalDate devolucion) {
        
        if (retiro == null){
            throw new IllegalArgumentException(ERROR_MSG_RETIRO_NULO);
        }
        if (padron <= 0) {
            throw new IllegalArgumentException(ERROR_MSG_PADRON_NEGATIVO);
        }
        if (socio == null || socio.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MSG_SOCIO_NULO);
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MSG_ISBN_NULO);
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_MSG_TITULO_NULO);
        }
        if (devolucion != null && devolucion.isBefore(retiro)) {
            throw new IllegalArgumentException(ERROR_MSG_DEVOLUCION_ANTERIOR_RETIRO);
        }

    }

    /**
     * Verifica si el préstamo está pendiente de devolución.
     * @return true si el préstamo está pendiente, false en caso contrario.
     */
    public boolean estaPendiente() {
        return devolucion == null;
    }

    /**
     * Obtiene la fecha de vencimiento del préstamo.
     * @return la fecha de vencimiento.
     */
    public LocalDate vencimiento() {
        return retiro.plusDays(DIAS_PLAZO_PRESTAMO);
    }

    /**
     * Calcula los días de atraso del préstamo en relación a la fecha de corte.
     * @param corte la fecha de corte para calcular los días de atraso.
     * @return los días de atraso; si no hay atraso, devuelve 0.
     */
    public int diasDeAtraso(LocalDate corte) {
        
        LocalDate fechaFinal = estaPendiente() ? corte : devolucion;
        long diasAtraso = ChronoUnit.DAYS.between(vencimiento(), fechaFinal);
        return diasAtraso > 0 ? (int) diasAtraso : 0;
    }

    /**
     * Calcula la multa correspondiente al préstamo en relación a la fecha de corte.
     * @param corte la fecha de corte para calcular la multa.
     * @return la multa calculada; si no hay multa, devuelve 0.
     */
    public int multa(LocalDate corte) {
        int multa = diasDeAtraso(corte) * MULTA_POR_DIA;
        
        return multa > MULTA_MAXIMA ? MULTA_MAXIMA : multa;
    }
}
