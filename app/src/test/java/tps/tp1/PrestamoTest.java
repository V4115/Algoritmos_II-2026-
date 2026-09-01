package tps.tp1;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class PrestamoTest {

    //Msg de error
    private static final String ERROR_MSG_PRESTAMO_RETIRO_NULO = "La fecha de retiro es obligatoria.";
    private static final String ERROR_MSG_PRESTAMO_DEVOLUCION_ANTERIOR_RETIRO = "La fecha de devolución no puede ser anterior a la fecha de retiro.";
    private static final String ERROR_MSG_PRESTAMO_PADRON_NEGATIVO = "El padrón debe ser un número positivo.";
    private static final String ERROR_MSG_PRESTAMO_SOCIO_NULO = "El nombre del socio es obligatorio.";
    private static final String ERROR_MSG_PRESTAMO_ISBN_NULO = "El ISBN es obligatorio.";
    private static final String ERROR_MSG_PRESTAMO_TITULO_NULO = "El título es obligatorio.";

    @Test
    public void testPrestamoEntradasInvalidas() {
        
        //Rechazo de datos invaálidos

        //Caso 1: Fecha de retiro nula
        try {
            Prestamo prestamo = new Prestamo(null, 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_RETIRO_NULO);
        }

        //Caso 2: Fecha de padrón negativo
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , -12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_PADRON_NEGATIVO);
        }

        //Caso 3: Fecha de Socio nulo/vacío
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, null, "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_SOCIO_NULO);
        }
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "   ", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_SOCIO_NULO);
        }

        //Caso 4: Fecha de ISBN nulo/vacío
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", null, "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_ISBN_NULO);
        }
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "   ", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_ISBN_NULO);
        }

        //Caso 5: Fecha de Título nulo/vacío
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", null, null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_TITULO_NULO);
        }
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "   ", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_TITULO_NULO);
        }

        //caso 6: Fecha de devolución anterior a la fecha de retiro
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-05"));
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_DEVOLUCION_ANTERIOR_RETIRO);
        }
        
    }
}
