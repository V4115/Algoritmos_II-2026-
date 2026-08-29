package tps.tp1;

/**
 *
 * @author jose
 */

/**
 * Interfaz que define las operaciones para registrar 
 * y consultar préstamos de libros en una biblioteca.
 */
public interface RegistroDePrestamos {
    void registrar(Prestamo p);
    int cantidad();
    Prestamo obtener(int i);                // IndexOutOfBoundsException si i es invalido
    int[] padrones();                       // sin repetidos, en orden de aparicion
    Prestamo[] prestamosDe(int padron);     // arreglo vacio si no hay ninguno
    String[] titulosMasPedidos(int n);      // los n mas pedidos, desempate alfabetico
}