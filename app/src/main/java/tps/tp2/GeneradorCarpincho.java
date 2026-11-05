package tps.tp2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
* Genera un plano de la mazmorra a partir de los parametros que recibe.
* Recibe un JSON con el formato de B.2 y devuelve un JSON con el formato
* de B.3. Con el mismo JSON de entrada devuelve siempre el mismo plano.
* Lanza IllegalArgumentException si la entrada es invalida: un campo que
* falta, filas o columnas menores a 5, cofres o monstruos negativos, o mas
* cofres y monstruos de los que entran en el plano.
*/
public class GeneradorCarpincho implements GeneradorDeLaberinto{
    
    private static final String NOMBRE_GRUPO = "Grupo Carpinchos";

    //Constantes de la mazmorra
    private static final char PARED = '#';
    private static final char PISO = '.';
    private static final char AGUA = '~';
    private static final char LAVA = '^';
    private static final char COFRE = 'C';
    private static final char MONSTRUO = 'M';
    private static final char ENTRADA = 'E';
    private static final char SALIDA = 'S';

    //Direcciones para moverse en la grilla: arriba, abajo, izquierda, derecha.
    private static final int[][] DIRECCIONES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //Clase para guardar los parametros leidos del JSON de entrada
    private record Parametros(JsonObject original, String dificultad, int filas,
                              int columnas, long semilla, int cofres,
                              int monstruos, boolean conSalida) { }
    
    //Clase para guardar las ubicaciones de la entrada y salida
    private record Ubicaciones(int[] entrada, int[] salida) { }

    /**
     * Genera un plano de la mazmorra a partir de los parametros que recibe.
     * Recibe un JSON con el formato de B.2 y devuelve un JSON con el formato de B.3. Con el mismo JSON de entrada devuelve siempre el mismo plano.
     */
    @Override
    public String generar(String parametrosJson) {
        Parametros p = LectorDeParametros.leer(parametrosJson);
        Random random = new Random(p.semilla());

        //se crean los caminos iniciales del laberinto y se abren bifurcaciones
        char [][] grilla = Trazador.trazar(p.filas(), p.columnas(), random);

        //se decoran los caminos con cofres, monstruos, agua y lava, y se ubican la entrada y salida
        Ubicaciones ubicaciones = Decorador.decorar(grilla, p, random);

        //se arma el JSON de salida con el formato de B.3
        return ArmadorDeJson.armar(grilla, p, ubicaciones.entrada(), ubicaciones.salida());
    }

    // Lectura y validación de los parámetros del JSON de entrada----------------
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
        static Parametros leer(String origen){
            
            JsonObject datos;

            //Leo el string y lo convierto en un Json
            try {
                datos = JsonParser.parseString(origen).getAsJsonObject();
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(ERROR_MSG_JSON_INVALIDO, e);
            }

            //Reviso que el String contenga todos los campos especificados en el enum Campo
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

    // Creacíon del laberinto -----------------------------------

    /** Traza el laberinto (backtracking con pila) y le abre bifurcaciones. */
    private static final class Trazador{
        private static final double MIN_PORCENTAJE_BIFURCACIONES = 0.15;
        private static final double MAX_PORCENTAJE_BIFURCACIONES = 0.50;

        /**
         * Traza el laberinto (backtracking con pila) y le abre bifurcaciones.
         * @param filas Cantidad de filas del laberinto
         * @param columnas Cantidad de columnas del laberinto
         * @param azar Generador de numeros aleatorios
         * @return Grilla del laberinto trazado
         */
        static char[][] trazar(int filas, int columnas, Random azar) {
            char[][] grilla = new char[filas][columnas];

            //Llena todo el laberinto con paredes
            for (char[] fila : grilla) {
                java.util.Arrays.fill(fila, PARED);
            }

            definirCeldas(grilla, azar);
            bifurcaciones(grilla, azar);

            return grilla;

        }
        
        /**
         * Define las celdas del laberinto a partir de un algoritmo de backtracking. Se parte de una grilla llena de paredes y se van abriendo pasillos hasta que todas las celdas sean alcanzables.
         * @param grilla Grilla del laberinto a definir
         * @param azar Generador de numeros aleatorios
         */
        private static void definirCeldas(char[][] grilla, Random azar) {
            Deque<int[]> pila = new ArrayDeque<>();

            //Verifico que las celdas exteriores no son válidas para ser pasillos
            int cantFilas = (grilla.length - 2 + 1) / 2;       // cuántas filas impares hay
            int cantCols  = (grilla[0].length - 2 + 1) / 2;    // cuántas columnas impares hay
            int inicioF = 1 + 2 * azar.nextInt(cantFilas);
            int inicioC = 1 + 2 * azar.nextInt(cantCols);

            //celda de inicio
            grilla[inicioF][inicioC] = PISO;
            pila.push(new int[]{inicioF, inicioC});

            while(!pila.isEmpty()){

                int[] tope = pila.peek();
                
                //se guardan las candidatas a apilarse en una lista
                List<int[]> candidatas = new ArrayList<>();

                //Se miran todas las direcciones posibles y se toman la 2da celda, si es una pared es valida y se suma a las candidatas
                for(int[] dirActual : DIRECCIONES){
                    int sigFila = tope[0] + 2 * dirActual[0];
                    int sigColumna = tope[1] + 2 * dirActual[1];

                    if( (sigFila > 0 && sigFila < grilla.length - 1) && 
                        (sigColumna > 0 && sigColumna < grilla[0].length - 1) && 
                        (grilla[sigFila][sigColumna] == PARED)){
                            candidatas.add(new int[] {sigFila, sigColumna, dirActual[0], dirActual[1]}); //guardo la celda y der de la que viene
                        }
                }

                if(candidatas.isEmpty()){
                    pila.pop(); //Si no hay candidatas, desapila
                }else{
                    int[] elegida = candidatas.get(azar.nextInt(candidatas.size())); //se elige una candidata al azar
                    grilla[elegida[0]][elegida[1]] = PISO;   //El destino elegido se trnasforma en piso
                    grilla[tope[0] + elegida[2]][tope[1] + elegida[3]] = PISO;   //El destino se transforma en piso. dir = (elegida[2], elegida[3])
                    pila.push(new int[]{elegida[0], elegida[1]}); //Apilo destino
                }
            }
        }

        /**
         * Abre bifurcaciones en la grilla del laberinto. Una bifurcacion es una celda que estaba entre 2 celdas de piso y se transforma en piso, generando un cruce.
         * @param grilla Grilla del laberinto a definir
         * @param azar Generador de numeros aleatorios
         */
        private static void bifurcaciones(char[][] grilla, Random azar) {

            List<int[]> candidatas = new ArrayList<>();

            //pasa por la grilla interior
            for (int fila = 1; fila < grilla.length - 1; fila++) {
                for (int col = 1; col < grilla[0].length - 1; col++) {
                    
                    //Si la celda actual es una pared y está entre 2 pisos (ya sea vertcal u horizontal), lo agrego como candidato a una bifurcacion
                    if( grilla[fila][col] == PARED && 
                        ( (grilla[fila-1][col] == PISO && grilla[fila+1][col] == PISO) || (grilla[fila][col-1] == PISO && grilla[fila][col+1] == PISO)) ){
                            
                            candidatas.add(new int[] {fila, col});
                    }
                }
            }

            //se mezclan las candidatas
            Collections.shuffle(candidatas, azar);

            //Se eligen al azar la cantidad de candidatas qiue efectivamente se abren
            double porcentaje = MIN_PORCENTAJE_BIFURCACIONES
                                + azar.nextDouble() * (MAX_PORCENTAJE_BIFURCACIONES - MIN_PORCENTAJE_BIFURCACIONES);
            int cantidadAAbrir = (int) (candidatas.size() * porcentaje);

            for(int i=0; i < cantidadAAbrir; i++){
                int[] celda = candidatas.get(i);
                grilla[celda[0]][celda[1]] = PISO;
            }
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
                    if (grilla[f][c] != PARED && dist[f][c] == -1) {    //Si se llega a una celda que figura como inalcanzable, se actualiza el resultado
                        dist[f][c] = dist[actual[0]][actual[1]] + 1;
                        cola.addLast(new int[]{f, c});  //Se añade a la cola al final
                    }
                }
            }
            return dist;
        }
    }

    //decorado de laberinto ---------------------------------------------------

    private static final class Decorador{
        //Constantes de decorado
        private static final int DISTANCIA_MINIMA_MONSTRUO_ENTRADA = 3;

        //Msg de error
        private static final String ERROR_MSG_CELDAS_INSUFICIENTES = "No hay suficientes celdas transitables para ubicar los cofres, monstruos y la salida";
        private static final String ERROR_MSG_MONSTRUOS_LEJOS = "No hay suficientes celdas transitables a una distancia de al menos 3 de la entrada para ubicar los monstruos";

        //Dificultad de la mazmorra
        private enum Dificultad {
            //nombre, porcentaje del agua, porcentaje de la lava
            FACIL("FACIL", 0.05, 0.00),
            MEDIO("MEDIO", 0.08, 0.03),
            DIFICIL("DIFICIL", 0.10, 0.06);

            //Msg de error
            private static final String ERROR_MSG_DIFICULTAD = "Dificultad invalida: ";

            private final String nombreDificultad;
            private final double porcentajeAgua;
            private final double porcentajeLava;

            Dificultad(String nombreDificultad, double porcentajeAgua, double porcentajeLava) {
                this.nombreDificultad = nombreDificultad;
                this.porcentajeAgua = porcentajeAgua;
                this.porcentajeLava = porcentajeLava;
            }

            public String nombreDificultad() {
                return nombreDificultad;
            }

            public double porcentajeAgua() {
                return porcentajeAgua;
            }

            public double porcentajeLava() {
                return porcentajeLava;
            }
            
            //Obtiene la dificultad a partir de su nombre. Lanza IllegalArgumentException si el nombre no es valido
            public static Dificultad desdeNombre(String nombre) {
                for (Dificultad actual : values()) {
                    if (actual.nombreDificultad.equals(nombre)) {
                        return actual;
                    }
                }
                throw new IllegalArgumentException(ERROR_MSG_DIFICULTAD + nombre);
            }
        }

        /**
         * Decora la grilla del laberinto con elementos como cofres y monstruos.
         * @param grilla Grilla del laberinto a decorar
         * @param p Parámetros del generador
         * @param azar Generador de números aleatorios
         */
        static Ubicaciones decorar(char[][] grilla, Parametros parametros, Random azar){
            Dificultad dificultad = Dificultad.desdeNombre(parametros.dificultad());

            List<int[]> celdasPiso = new ArrayList<>();
            for (int fila = 1; fila < grilla.length - 1; fila++) {
                for (int col = 1; col < grilla[0].length - 1; col++) {
                    if (grilla[fila][col] == PISO) {
                        celdasPiso.add(new int[]{fila, col});
                    }
                }
            }

            int transitables = celdasPiso.size();

            int minNecesarios = parametros.cofres() + parametros.monstruos() + (parametros.conSalida() ? 1 : 0) + 1; // +1 para la entrada
            if (transitables < minNecesarios) {
                throw new IllegalArgumentException(ERROR_MSG_CELDAS_INSUFICIENTES + " (transitables: " + transitables + ", necesarios: " + minNecesarios + ")");
            }

            //Entrada al azar
            int[] entrada = celdasPiso.get(azar.nextInt(transitables));

            //Salida mas lejana a la entrada
            int[] salida = null;
            if (parametros.conSalida()) {
                // Se calcula la distancia desde la entrada a todas las celdas de piso
                int[][] distancia = Bfs.calcularDistancia(grilla, entrada);
                //se inicia con la salida mas lejana siendo la entrada misma
                salida = entrada;
                // Se busca la celda de piso más lejana a la entrada recorriendo todas las celdas de piso
                for (int[] celda : celdasPiso) {
                    if (distancia[celda[0]][celda[1]] > distancia[salida[0]][salida[1]]) {
                        salida = celda;
                    }
                }
            }

            //Se marcan entrada y salida
            grilla[entrada[0]][entrada[1]] = ENTRADA;
            if (salida != null) {
                grilla[salida[0]][salida[1]] = SALIDA;
            }

            //Se mezclan las celdas de piso para ubicar los elementos decorativos
            List<int[]> libres = new ArrayList<>(celdasPiso);
            Collections.shuffle(libres, azar);

            //se ubican los monstruos a una distancia mínima de la entrada
            int monstruosColocados = ubicar(grilla, libres, MONSTRUO, parametros.monstruos(),
                    entrada, DISTANCIA_MINIMA_MONSTRUO_ENTRADA);
            if (monstruosColocados < parametros.monstruos()) {
                throw new IllegalArgumentException(ERROR_MSG_MONSTRUOS_LEJOS);
            }

            //Se ubican los cofres sin restricción de distancia
            int cofresColocados = ubicar(grilla, libres, COFRE, parametros.cofres(), entrada, 0);
            if (cofresColocados < parametros.cofres()) {
                throw new IllegalArgumentException(ERROR_MSG_CELDAS_INSUFICIENTES);
            }

            //Se ubican agua y lava según la dificultad
            ubicar(grilla, libres, AGUA, (int) (transitables * dificultad.porcentajeAgua()), entrada, 0);
            ubicar(grilla, libres, LAVA, (int) (transitables * dificultad.porcentajeLava()), entrada, 0);

            return new Ubicaciones(entrada, salida);
        }

        /**
         * Ubica elementos en la grilla a partir de una lista de celdas libres, un símbolo, una cantidad y una distancia mínima desde la entrada.
         * @param grilla Grilla del laberinto a decorar
         * @param libres Lista de celdas libres donde se pueden ubicar los elementos
         * @param simbolo Símbolo del elemento a ubicar
         * @param cantidad Cantidad de elementos a ubicar
         * @param entrada Celda de entrada del laberinto
         * @param distanciaMinima Distancia mínima desde la entrada para ubicar los elementos
         * @return Cantidad de elementos efectivamente ubicados
         */
        private static int ubicar(char[][] grilla, List<int[]> libres, char simbolo, int cantidad,
                    int[] entrada, int distanciaMinima) {
            int colocadas = 0;
            for (int[] celda : libres) {
                if (colocadas == cantidad) {
                    break;
                }
                boolean estaLibre = grilla[celda[0]][celda[1]] == PISO;
                int manhattan = Math.abs(celda[0] - entrada[0]) + Math.abs(celda[1] - entrada[1]);
                if (estaLibre && manhattan >= distanciaMinima) {
                    grilla[celda[0]][celda[1]] = simbolo;
                    colocadas++;
                }
            }
            return colocadas;
        }
    }

    // Armado del JSON de salida -----------------------------------
    private static final class ArmadorDeJson{
        /**
        * Arma el JSON de salida con el formato de B.3. Fila y columna van de 1 a n.
        */
        static String armar(char[][] grilla, Parametros parametros, int[] entrada, int[] salida) {
            JsonObject resultado = new JsonObject();
            resultado.addProperty("version", 1);
            resultado.addProperty("generador", NOMBRE_GRUPO);
            resultado.add("parametros", parametros.original());
            resultado.add("entrada", posicion(entrada));
            resultado.add("salida", salida == null ? JsonNull.INSTANCE : posicion(salida));

            JsonArray plano = new JsonArray();
            for (char[] fila : grilla) {
                plano.add(new String(fila));
            }
            resultado.add("plano", plano);

            return new GsonBuilder().serializeNulls().create().toJson(resultado);
        }

        //Pasa una celda base 0 {fila, columna} al formato base 1 del JSON. 
        private static JsonObject posicion(int[] celda) {
            JsonObject posicion = new JsonObject();
            posicion.addProperty("fila", celda[0] + 1);
            posicion.addProperty("columna", celda[1] + 1);
            return posicion;
        }
    }
}