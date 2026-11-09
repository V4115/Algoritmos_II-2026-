package tps.tp2;

import java.util.ArrayDeque;
import java.util.Deque;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ValidadorDePlano {
    
    //Constructor privado para que no se pueda instanciar la clase
    private ValidadorDePlano() { }

    //Clase para guardar los parametros leidos del JSON de entrada
    private record Parametros(JsonObject original, String dificultad, int filas,
                            int columnas, long semilla, int cofres,
                            int monstruos, boolean conSalida) { }

    // Lectura de parametros --------------------------------------------------------------
    private static final class LectorDeParametros{

        //Mensajes de ERROR
        private static final String ERROR_MSG_JSON_INVALIDO = "JSON de entrada invalido";
        private static final String ERROR_MSG_FALTA_CAMPO = "Falta el campo: ";
        private static final String ERROR_MSG_FILAS_COLUMNAS = "Filas y columnas deben ser mayores o iguales a 5";
        private static final String ERROR_MSG_COFRES_MONSTRUOS = "Cofres y monstruos deben ser mayores o iguales a 0";
        private static final String ERROR_MSG_COFRES_MONSTRUOS_PLANO = "Hay mas cofres y monstruos de los que entran en el plano";

        //Campos a leer del Json
        private enum Campo {
            VERSION("version"),
            DIFICULTAD("dificultad"),
            FILAS("filas"),
            COLUMNAS("columnas"),
            SEMILLA("semilla"),
            COFRES("cofres"),
            MONSTRUOS("monstruos"),
            CON_SALIDA("conSalida");

            private final String nombreCampo;

            Campo(String nombreCampo) {
                this.nombreCampo = nombreCampo;
            }

            public String nombreCampo() {
                return nombreCampo;
            }

            int comoInt(JsonObject obj) {
                return obj.get(nombreCampo).getAsInt();
            }

            long comoLong(JsonObject obj){
                return obj.get(nombreCampo).getAsLong(); 
            }

            String comoString(JsonObject obj) {
                return obj.get(nombreCampo).getAsString();
            }

            boolean comoBoolean(JsonObject obj) {
                return obj.get(nombreCampo).getAsBoolean();
            }
            
        }

        /**
         * Lee los parametros del JSON de entrada y los valida. Lanza IllegalArgumentException si el JSON es invalido.
         * @param origen JSON de entrada
         * @return Parametros leidos del JSON
         */
        static Parametros leer(JsonObject datos){


            //Reviso que el Json contenga todos los campos especificados en el enum Campo
            for(Campo actual : Campo.values()){
                if(!datos.has(actual.nombreCampo()) || datos.get(actual.nombreCampo()).isJsonNull()){
                    throw new IllegalArgumentException(ERROR_MSG_FALTA_CAMPO + actual.nombreCampo());
                }
            }

            //Obtengo los valores de los campos
            int filas     = Campo.FILAS.comoInt(datos);
            int columnas  = Campo.COLUMNAS.comoInt(datos);
            int cofres    = Campo.COFRES.comoInt(datos);
            int monstruos = Campo.MONSTRUOS.comoInt(datos);

            //Validaciones
            if(filas < 5 || columnas < 5){
                throw new IllegalArgumentException(ERROR_MSG_FILAS_COLUMNAS);
            }
            if(cofres < 0 || monstruos < 0){
                throw new IllegalArgumentException(ERROR_MSG_COFRES_MONSTRUOS);
            }
            if(cofres + monstruos > (filas - 2) * (columnas - 2)){
                throw new IllegalArgumentException(ERROR_MSG_COFRES_MONSTRUOS_PLANO);
            }

            return new Parametros(datos,
                    Campo.DIFICULTAD.comoString(datos),
                    filas,
                    columnas,
                    Campo.SEMILLA.comoLong(datos),
                    cofres,
                    monstruos,
                    Campo.CON_SALIDA.comoBoolean(datos));
        }
    }

    // Lectura de plano
    private record Plano(Parametros parametros, char[][] grilla, JsonObject entradaDeclarada, JsonObject salidaDeclarada) { }

    /**
     * Lee un plano en formato B.3 y lo valida contra las reglas de B.4.
     */
    private static final class LectorDePlano {
        
        private enum SimbolosValidos {
            PARED('#'),
            PISO('.'),
            AGUA('~'),
            LAVA('^'),
            COFRE('C'),
            MONSTRUO('M'),
            ENTRADA('E'),
            SALIDA('S');

            private final char caracter;

            //Constructor
            SimbolosValidos(char caracter) {
                this.caracter = caracter;
            }

            char caracter() {
                return caracter;
            }

            /** Indica si el caracter es uno de los ocho simbolos de B.3. */
            static boolean esValido(char caracter) {
                for (SimbolosValidos actual : values()) {
                    if (actual.caracter == caracter) {
                        return true;
                    }
                }
                return false;
            }
        }

        //Error Msg
        private static final String ERROR_MSG_JSON_INVALIDO = "El plano no es un JSON valido: ";
        private static final String ERROR_MSG_FALTA_CAMPO = "Falta el campo: ";
        private static final String ERROR_MSG_R1 = "R1: dimensiones incorrectas";
        private static final String ERROR_MSG_R2 = "R2: simbolo invalido ";
        private static final String ERROR_MSG_R3 = "R3: el borde no es todo pared";
        private static final String ERROR_MSG_R4 = "R4: entrada y salida incorrectas";
        private static final String ERROR_MSG_R5 = "R5: el plano no es conexo";
        private static final String ERROR_MSG_R6 = "R6: faltan bifurcaciones";
        private static final String ERROR_MSG_R7 = "R7: proporcion de celdas transitables fuera de rango";
        private static final String ERROR_MSG_R8 = "R8: cantidad de cofres o monstruos incorrecta";
        private static final String ERROR_MSG_R9 = "R9: la salida no es la celda mas lejana";
        private static final String ERROR_MSG_R10 = "R10: demasiada agua y lava";
        private static final String ERROR_MSG_R11 = "R11: monstruo pegado a la entrada";

        /**
         * Lee la grilla del plano desde el JSON y valida que todas las filas tengan el mismo largo y solo contengan caracteres válidos.
         */
        private static char[][] leerGrilla(JsonArray plano) {
            if (plano.isEmpty()) {
                throw new IllegalArgumentException(ERROR_MSG_R1 + " (el plano no tiene filas)");
            }
            char[][] grilla = new char[plano.size()][];
            //Se recorre al arreglo Json, se verifica que sea una cadena y se transforma en una matriz de caracteres.
            //luego se verifica que todas las filas tengan el mismo largo y solo contengan caracteres válidos.
            for (int f = 0; f < plano.size(); f++) {
                JsonElement elemento = plano.get(f);
                if (!elemento.isJsonPrimitive() || !elemento.getAsJsonPrimitive().isString()) {
                    throw new IllegalArgumentException(ERROR_MSG_R1 + " (la fila " + (f + 1) + " no es una cadena)");
                }
                grilla[f] = elemento.getAsString().toCharArray();
                if (grilla[f].length != grilla[0].length) {
                    throw new IllegalArgumentException(ERROR_MSG_R1 + " (la fila " + (f + 1) + " tiene otro largo)");
                }
                for (int c = 0; c < grilla[f].length; c++) {
                    if (!SimbolosValidos.esValido(grilla[f][c])) {
                        throw new IllegalArgumentException(ERROR_MSG_R2 + "'" + grilla[f][c] + "' en fila "
                                + (f + 1) + ", columna " + (c + 1));
                    }
                }
            }
            return grilla;
        }

        private static final String CLAVE_PARAMETROS = "parametros";
        private static final String CLAVE_ENTRADA = "entrada";
        private static final String CLAVE_SALIDA = "salida";
        private static final String CLAVE_PLANO = "plano";

        static Plano leer(JsonObject raiz) {
            for (String clave : new String[]{CLAVE_PARAMETROS, CLAVE_ENTRADA, CLAVE_SALIDA, CLAVE_PLANO}) {
                if (!raiz.has(clave)) {
                    throw new IllegalArgumentException(ERROR_MSG_FALTA_CAMPO + clave);
                }
            }
            try {
                Parametros pedido = LectorDeParametros.leer(raiz.getAsJsonObject(CLAVE_PARAMETROS));
                char[][] grilla = leerGrilla(raiz.getAsJsonArray(CLAVE_PLANO));
                return new Plano(pedido, grilla, raiz.getAsJsonObject(CLAVE_ENTRADA), raiz.getAsJsonObject(CLAVE_SALIDA));
            } catch (ClassCastException | IllegalStateException e) {
                //un campo con el tipo equivocado (por ejemplo "plano" que no es un arreglo)
                throw new IllegalArgumentException(ERROR_MSG_JSON_INVALIDO, e);
            }
        }

        //Umbrales de las reglas
        private static final int MIN_LEN = 5;                              //R1
        private static final double MIN_PORCENTAJE_BIFURCACIONES = 0.10;   //R6
        private static final double MIN_PORCENTAJE_TRANSITABLE = 0.30;     //R7
        private static final double MAX_PORCENTAJE_TRANSITABLE = 0.60;     //R7
        private static final double MAX_PORCENTAJE_AGUA_LAVA = 0.25;       //R10
        private static final int DISTANCIA_MINIMA_MONSTRUO = 3;            //R11

        private static final int[][] DIRECCIONES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        /**
         * Valida un plano en formato B.3 contra las once reglas de B.4.
         * @param planoJson JSON de salida del generador
         * @throws IllegalArgumentException si alguna regla falla; el mensaje empieza con el numero de regla
         */
        public static void validar(String planoJson) {
            Plano plano = LectorDePlano.leer(parsear(planoJson));   //R2 y la forma de R1 se verifican al leer
            char[][] grilla = plano.grilla();
            Parametros parametros = plano.parametros();

            validarDimensiones(grilla, parametros);        //R1
            validarBorde(grilla);                      //R3
            validarEntradaYSalida(plano);              //R4

            //A partir de aca hay una E y el borde es pared: el BFS no se sale de la grilla
            int[] entrada = buscarCaracter(grilla, SimbolosValidos.ENTRADA.caracter);
            int[][] distancia = Bfs.calcularDistancia(grilla, entrada);
            int transitables = contarTransitables(grilla);

            validarConexo(distancia, transitables);                    //R5
            validarBifurcaciones(grilla, transitables);                //R6
            validarProporcion(grilla, transitables);                   //R7
            validarCantidades(grilla, parametros);                         //R8
            if (parametros.conSalida()) {
                validarSalidaMasLejana(grilla, distancia);             //R9
            }
            validarAguaYLava(grilla, transitables);                    //R10
            validarMonstruos(grilla, entrada);                         //R11
        }

        
        /**
         * R1
         * Valida que las dimensiones de la grilla coincidan con las del pedido y que sean mayores o iguales a 5.
         * @param grilla grilla del plano
         * @param pedido parametros leidos del JSON de entrada
         * @throws IllegalArgumentException si las dimensiones no coinciden o son menores a 5
         */
        private static void validarDimensiones(char[][] grilla, Parametros parametros) {
            boolean grillaValida = grilla.length == parametros.filas() && grilla[0].length == parametros.columnas();
            if (!grillaValida || grilla.length < MIN_LEN || grilla[0].length < MIN_LEN) {
                throw new IllegalArgumentException(ERROR_MSG_R1 + " (plano de " + grilla.length + "x"
                        + grilla[0].length + ", pedido " + parametros.filas() + "x" + parametros.columnas() + ")");
            }
        }

        /**
         * R3
         * Valida que el borde de la grilla sea todo pared.
         * @param grilla grilla del plano
         * @throws IllegalArgumentException si el borde no es todo pared
         */
        private static void validarBorde(char[][] grilla) {
            int ultimaFila = grilla.length - 1;
            int ultimaColumna = grilla[0].length - 1;
            for (int c = 0; c <= ultimaColumna; c++) {
                if ((grilla[0][c] != SimbolosValidos.PARED.caracter) || (grilla[ultimaFila][c] != SimbolosValidos.PARED.caracter)) {
                    throw new IllegalArgumentException(ERROR_MSG_R3);
                }
            }
            for (int f = 0; f <= ultimaFila; f++) {
                if (grilla[f][0] != SimbolosValidos.PARED.caracter || grilla[f][ultimaColumna] != SimbolosValidos.PARED.caracter) {
                    throw new IllegalArgumentException(ERROR_MSG_R3);
                }
            }
        }

        /**
         * R4
         * Valida que haya exactamente una E y, si corresponde, una S, y que coincidan con los campos declarados en el JSON.
         * @param plano plano leido del JSON
         * @throws IllegalArgumentException si no hay exactamente una E y, si corresponde, una S, o si no coinciden con los campos declarados en el JSON
         */
        private static void validarEntradaYSalida(Plano plano) {
            char[][] grilla = plano.grilla();
            boolean conSalida = plano.parametros().conSalida();

            if (contarCaracter(grilla, SimbolosValidos.ENTRADA.caracter) != 1 || contarCaracter(grilla, SimbolosValidos.SALIDA.caracter) != (conSalida ? 1 : 0)) {
                throw new IllegalArgumentException(ERROR_MSG_R4 + " (cantidad de E o S)");
            }
            if (!coincide(plano.entradaDeclarada(), buscarCaracter(grilla, SimbolosValidos.ENTRADA.caracter))) {
                throw new IllegalArgumentException(ERROR_MSG_R4 + " (el campo entrada no coincide con la E)");
            }
            if (conSalida) {
                if (!coincide(plano.salidaDeclarada(), buscarCaracter(grilla, SimbolosValidos.SALIDA.caracter))) {
                    throw new IllegalArgumentException(ERROR_MSG_R4 + " (el campo salida no coincide con la S)");
                }
            } else if (!plano.salidaDeclarada().isJsonNull()) {
                throw new IllegalArgumentException(ERROR_MSG_R4 + " (salida deberia ser null)");
            }
        }

        /** R5
         * Valida que el plano sea conexo, es decir, que todas las celdas transitables sean alcanzables desde la E.
         * @param distancia grilla con la cantidad de pasos desde la E a cada celda
         * @param transitables cantidad de celdas transitables en el plano
         * @throws IllegalArgumentException si el plano no es conexo
        */
        private static void validarConexo(int[][] distancia, int transitables) {
            int alcanzadas = 0;
            for (int[] fila : distancia) {
                for (int d : fila) {
                    if (d != -1) {
                        alcanzadas++;
                    }
                }
            }
            if (alcanzadas != transitables) {
                throw new IllegalArgumentException(ERROR_MSG_R5 + " (alcanzadas: " + alcanzadas
                        + ", transitables: " + transitables + ")");
            }
        }

        /** R6
         * Valida que al menos el 10 % de las celdas transitables tengan 3 o mas vecinas transitables.
         * @param grilla grilla del plano
         * @param transitables cantidad de celdas transitables en el plano
         * @throws IllegalArgumentException si menos del 10 % de las celdas transit
        */
        private static void validarBifurcaciones(char[][] grilla, int transitables) {
            int conTresVecinas = 0;
            for (int f = 1; f < grilla.length - 1; f++) {
                for (int c = 1; c < grilla[0].length - 1; c++) {
                    if (grilla[f][c] == SimbolosValidos.PARED.caracter) {
                        continue;
                    }
                    int vecinas = 0;
                    for (int[] dir : DIRECCIONES) {
                        if (grilla[f + dir[0]][c + dir[1]] != SimbolosValidos.PARED.caracter) {
                            vecinas++;
                        }
                    }
                    if (vecinas >= 3) {
                        conTresVecinas++;
                    }
                }
            }
            if ((double) conTresVecinas / transitables < MIN_PORCENTAJE_BIFURCACIONES) {
                throw new IllegalArgumentException(ERROR_MSG_R6 + " (" + conTresVecinas + " de " + transitables + ")");
            }
        }

        /** R7
         * Valida que entre el 30 % y el 60 % de TODAS las celdas del plano sean transitables.
         * @param grilla grilla del plano
         * @param transitables cantidad de celdas transitables en el plano
         * @throws IllegalArgumentException si la proporcion de celdas transitables no esta entre el 30 % y el 60 %
        */
        private static void validarProporcion(char[][] grilla, int transitables) {
            double proporcion = (double) transitables / (grilla.length * grilla[0].length);
            if (proporcion < MIN_PORCENTAJE_TRANSITABLE || proporcion > MAX_PORCENTAJE_TRANSITABLE) {
                throw new IllegalArgumentException(ERROR_MSG_R7 + " (" + Math.round(proporcion * 100) + " %)");
            }
        }

        /** R8
         * Valida que haya tantas C como cofres y tantas M como monstruos.
         * @param grilla grilla del plano
         * @param pedido parametros leidos del JSON de entrada
         * @throws IllegalArgumentException si la cantidad de C o M no coincide con la del pedido
         */
        private static void validarCantidades(char[][] grilla, Parametros pedido) {
            if (contarCaracter(grilla, SimbolosValidos.COFRE.caracter) != pedido.cofres() || contarCaracter(grilla, SimbolosValidos.MONSTRUO.caracter) != pedido.monstruos()) {
                throw new IllegalArgumentException(ERROR_MSG_R8);
            }
        }

        /** R9
         * Valida que la distancia BFS de la S sea la maxima de todo el plano (si hay empate, vale).
         * @param grilla grilla del plano
         * @param distancia grilla con la cantidad de pasos desde la E a cada celda
         * @throws IllegalArgumentException si la distancia de la S no es la maxima
        */
        private static void validarSalidaMasLejana(char[][] grilla, int[][] distancia) {
            int[] salida = buscarCaracter(grilla, SimbolosValidos.SALIDA.caracter);
            int maxima = 0;
            for (int[] fila : distancia) {
                for (int d : fila) {
                    maxima = Math.max(maxima, d);
                }
            }
            if (distancia[salida[0]][salida[1]] != maxima) {
                throw new IllegalArgumentException(ERROR_MSG_R9 + " (la S esta a "
                        + distancia[salida[0]][salida[1]] + " y la maxima es " + maxima + ")");
            }
        }

        /** R10
         * Valida que la cantidad de agua y lava no supere el 25 % de las celdas transitables.
         * @param grilla grilla del plano
         * @param transitables cantidad de celdas transitables en el plano
         * @throws IllegalArgumentException si la cantidad de agua y lava supera el 25 %
        */
        private static void validarAguaYLava(char[][] grilla, int transitables) {
            int aguaYLava = contarCaracter(grilla, SimbolosValidos.AGUA.caracter) + contarCaracter(grilla, SimbolosValidos.LAVA.caracter);
            if ((double) aguaYLava / transitables > MAX_PORCENTAJE_AGUA_LAVA) {
                throw new IllegalArgumentException(ERROR_MSG_R10 + " (" + aguaYLava + " de " + transitables + ")");
            }
        }

        /** R11
         * Valida que toda M este a distancia Manhattan >= 3 de la E.
         * @param grilla grilla del plano
         * @param entrada posicion de la E en la grilla
         * @throws IllegalArgumentException si alguna M esta a distancia Manhattan < 3 de la
        */
        private static void validarMonstruos(char[][] grilla, int[] entrada) {
            for (int f = 0; f < grilla.length; f++) {
                for (int c = 0; c < grilla[0].length; c++) {
                    if (grilla[f][c] == SimbolosValidos.MONSTRUO.caracter) {
                        int manhattan = Math.abs(f - entrada[0]) + Math.abs(c - entrada[1]);
                        if (manhattan < DISTANCIA_MINIMA_MONSTRUO) {
                            throw new IllegalArgumentException(ERROR_MSG_R11 + " (M en fila " + (f + 1)
                                    + ", columna " + (c + 1) + ")");
                        }
                    }
                }
            }
        }

        //Auxiliares-------------------------------------------------------------------------------------

        private static int contarCaracter(char[][] grilla, char caracter){
            int total = 0;
            for(char[] fila : grilla){
                for(char celda : fila){
                    if(celda == caracter){
                        total++;
                    }
                }
            }

            return total;
        }

        private static int[] buscarCaracter(char[][] grilla, char caracter){

            for(int f = 0; f < grilla.length; f++){
                for(int c = 0; c < grilla[0].length; c++){
                    if(grilla[f][c] == caracter){
                        return new int[]{f,c};
                    }
                }
            }

            return null;
        }

        private static boolean coincide(JsonObject declarada, int[] posicion){
            return declarada.get("fila").getAsInt() == posicion[0] && declarada.get("columna").getAsInt() == posicion[1];
        }

        private static int contarTransitables(char[][] grilla){
            int total = 0;

            for (int fila = 1; fila < grilla.length - 1; fila++) {
                for (int col = 1; col < grilla[0].length - 1; col++) {
                    if (grilla[fila][col] == SimbolosValidos.PISO.caracter) {
                        total++;
                    }
                }
            }

            return total;
        }

        /** Convierte el texto en JsonObject. Lanza IllegalArgumentException si no es JSON valido. */
        private static JsonObject parsear(String json) {
            try {
                return JsonParser.parseString(json).getAsJsonObject();
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(LectorDeParametros.ERROR_MSG_JSON_INVALIDO, e);
            }
        }

        // Algoritmo de búsqueda de caminos -----------------------------------
        private static final class Bfs{

            /**
             * Realiza un BFS desde la celda origen y devuelve una grilla con la cantidad de pasos desde el origen a cada celda. Las celdas inalcanzables quedan con valor -1.
             * @param grilla Grilla del laberinto
             * @param origen Celda de origen del BFS
             * @return Grilla con la cantidad de pasos desde el origen a cada celda
             */
            static int[][] calcularDistancia(char[][] grilla, int[] origen) {
                //se crea una grilla donde el valor de c/celda es la cant de pasos del origen a la celda (-1 es inalcanzable)
                int[][] dist = new int[grilla.length][grilla[0].length];

                for (int[] fila : dist) {
                    java.util.Arrays.fill(fila, -1);    //se inicia con todas las celdas siendo "inalcanzables"
                }

                Deque<int[]> cola = new ArrayDeque<>(); //cola para el bfs
                dist[origen[0]][origen[1]] = 0; //origen = 0
                cola.addLast(origen);   //se encolan al final
                while (!cola.isEmpty()) {
                    int[] actual = cola.pollFirst();    //Se elimina el más viejo de la cola
                    for (int[] dir : DIRECCIONES) { //Se verifican todas las direcciones actuales
                        int f = actual[0] + dir[0]; 
                        int c = actual[1] + dir[1];
                        if (grilla[f][c] != SimbolosValidos.PARED.caracter && dist[f][c] == -1) {    //Si se llega a una celda que figura como inalcanzable, se actualiza el resultado
                            dist[f][c] = dist[actual[0]][actual[1]] + 1;
                            cola.addLast(new int[]{f, c});  //Se añade a la cola al final
                        }
                    }
                }
                return dist;
            }
        }
    }
}