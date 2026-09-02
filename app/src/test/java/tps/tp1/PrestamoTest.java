package tps.tp1;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class PrestamoTest {

    // Constantes para la lógica de negocio
    private static final int DIAS_PLAZO_PRESTAMO = 14;
    private static final int MULTA_POR_DIA = 150;
    private static final int MULTA_MAXIMA = 3000;
    
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

        //Fecha de retiro nula
        try {
            Prestamo prestamo = new Prestamo(null, 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_RETIRO_NULO);
        }

        //Fecha de padrón negativo
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , -12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_PADRON_NEGATIVO);
        }

        //Fecha de Socio nulo/vacío
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

        //Fecha de ISBN nulo/vacío
        try {
            @SuppressWarnings("unused")
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", null, "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_ISBN_NULO);
        }
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "   ", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_ISBN_NULO);
        }

        //Fecha de Título nulo/vacío
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
    }

    @Test void testPrestamoFechas(){
        //Fecha de devolución nula
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción al crear un prestamo con fecha de devolución nula.";
        }
        
        //Fecha de devolución anterior a la fecha de retiro
        try {
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_DEVOLUCION_ANTERIOR_RETIRO);
        }

        //Verificación de vencimiento
        //Fecha de vencimiento después de la fecha de retiro
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", null);
            assert prestamo.vencimiento().equals(LocalDate.parse("2026-04-20"));
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción con vencimiento después de la fecha de retiro.";
        }
        
        //Mismo día de retiro
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-20"));
            assert prestamo.vencimiento().equals(LocalDate.parse("2026-04-20"));
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción con vencimiento después de la fecha de retiro.";
        }

        //Entre dia de retiro y fecha de vencimiento
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-20").plusDays(DIAS_PLAZO_PRESTAMO/2));
            assert prestamo.vencimiento().equals(LocalDate.parse("2026-04-20"));
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción con devolución antes de la fecha de vencimiento.";
        }

        //Mismo día de vencimiento
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-20").plusDays(DIAS_PLAZO_PRESTAMO));
            assert prestamo.vencimiento().equals(LocalDate.parse("2026-04-20"));
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción con devolución en la fecha de vencimiento.";
        }

        //Después de la fecha de vencimiento
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO + 1));
            assert prestamo.diasDeAtraso(LocalDate.parse("2026-04-06")) == 1 : "Debería ser 1 día de atraso";
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción aqui";
        }

        //Fecha de vencimiento antes de la fecha de retiro
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-19"));
        }catch (IllegalArgumentException e) {
            assert e.getMessage().equals(ERROR_MSG_PRESTAMO_DEVOLUCION_ANTERIOR_RETIRO);
        }

        //Multas
        //Multa 0 para devolución en regla
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO/2));
            assert prestamo.multa(LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO/2)) == 0 : "Debería ser 0 multa";
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción";
        }

        //Valor de multa para un día de atraso
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO + 1));
            assert prestamo.multa(LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO + 1)) == MULTA_POR_DIA : "Debería ser 150 multa";
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción";
        }

        //Valor de multa Maximo no debe superar el valor de MULTA_MAXIMA
        try{
            Prestamo prestamo = new Prestamo(LocalDate.parse("2026-04-06") , 12345, "Juan Perez", "978-3-16-148410-0", "El Gran Gatsby", LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO*(MULTA_MAXIMA/MULTA_POR_DIA + 10)));
            assert prestamo.multa(LocalDate.parse("2026-04-06").plusDays(DIAS_PLAZO_PRESTAMO*(MULTA_MAXIMA/MULTA_POR_DIA + 10))) == MULTA_MAXIMA : "Debería ser 3000 multa";
        }catch (IllegalArgumentException e) {
            assert false : "No se esperaba una excepción";
        }
    }
}
