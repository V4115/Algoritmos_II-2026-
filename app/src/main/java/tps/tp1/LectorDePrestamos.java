package tps.tp1;

/**
 *
 * @author jose
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class LectorDePrestamos {

    private static final int ERRORES_TAMANO_INICIAL = 10;
    private static final int ERRORES_FACTOR= 2;
    private static final String DELIM = ";";
    private static final String LINEA_MSG = "Linea: ";

    private static final String ERROR_MSG_PARSE_CAMPOS_MIN = "Faltan campos obligatorios";
    private static final String ERROR_MSG_PARSE_FECHA_INVALIDA = "Fecha inválida";
    private static final String ERROR_MSG_PARSE_PADRON = "Valor de padrón inválido";

    //PARSE ctes de validación
    private static final int PARSE_CAMPOS_MIN = 5;

    public static ResultadoDeCarga cargar(Path archivo) throws IOException {
        
        if (archivo == null){
            throw new NullPointerException();
        }

        String[] errores = new String[ERRORES_TAMANO_INICIAL];
        int cantErrores = 0;
        int cantDatos = 0;

        //abre el archivo y hace una lista con c/linean c/linea
        List<String> lineas = Files.readAllLines(archivo);

        RegistroDePrestamos registro = new RegistroSobreArreglo();

        //Recorro c/linea de la lista del archivo
        for(int i = 0; i < lineas.size(); i++){

            String linea = lineas.get(i);

            //Ignoro comentarios y lineas en blanco
            if(linea.isBlank() || linea.trim().startsWith("#")){
                continue;
            }
            //Lineas no ignoradas, independiente de los errores
            cantDatos++;
            
            try {
                registro.registrar(parse(linea, DELIM, i));
            } catch (LineaInvalidaException e) {
                if(errores.length == cantErrores){
                    errores = Arrays.copyOf(errores, errores.length*ERRORES_FACTOR);
                }
                //Guarda la linea del error + el mensaje de error
                errores[cantErrores] = LINEA_MSG + (e.numeroDeLinea()+1)+ ": " + e.getMessage();
                cantErrores++;
            }
        }
        //Devuelvo un arreglo con el tamaño justo
        errores = Arrays.copyOf(errores, cantErrores);
        return new ResultadoDeCarga(registro, errores, cantDatos);
    }

    private static Prestamo parse(String linea, String delim, int numeroDeLinea){
        
        try {
            //es importante el -1 en el split, porqur le dice que mantenga los campos vacíos.
            //evita error out of bound cuando llama al campo[5], en una línea sin fecha de devolución.
            String[] campos = linea.split(delim, -1);

            //Validaciones

            if (campos.length < PARSE_CAMPOS_MIN){
                throw new IllegalArgumentException(ERROR_MSG_PARSE_CAMPOS_MIN);
            }

            LocalDate fechaRetiro = validarFecha(campos[0].trim());
            int padron = validarPadron(campos[1].trim());
            String socio = campos[2].trim();
            String isbn = campos[3].trim();
            String titulo = campos[4].trim();
            LocalDate fechaDevolucion = campos[5].trim().isBlank() ? null : validarFecha(campos[5].trim());

            return new Prestamo(fechaRetiro, padron, socio, isbn, titulo, fechaDevolucion);
            }catch (IllegalArgumentException e) {
            throw new LineaInvalidaException(numeroDeLinea, e.getMessage());
        }
    }

    //Metodos de validación en el parse para luego crear un prestamo
    private static LocalDate validarFecha(String linea){
        try {
            return LocalDate.parse(linea);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ERROR_MSG_PARSE_FECHA_INVALIDA);
        }
    }

    //valida que sea efectivamente un numero int, luego prestamo verifica si es positivo
    private static int validarPadron(String linea){
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MSG_PARSE_PADRON);
        }
    }

}

