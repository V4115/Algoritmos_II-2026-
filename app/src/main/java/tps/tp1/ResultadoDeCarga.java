package tps.tp1;

/**
 *
 * @author jose
 */

/**
 * Mantiene los resultados de la lectura de los prestamos
 * @param registro de prestamos 
 * @param errores: arreglo con todos los mensajes de errores que se recibieron durante la ejecución, con el numero de 
 * linea y nombre del error en cuestión
 * @param lineasDeDatos: la cantidad de datos tenidos en cuenta durante la ejecución del preograma, es decir,
 *inlcuyendo errores, pero sin tener en cuenta comentarios o líneas en blanco.
 */
public record ResultadoDeCarga(RegistroDePrestamos registro, String[] errores, int lineasDeDatos) { 
    
}