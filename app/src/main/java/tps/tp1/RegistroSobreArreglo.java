package tps.tp1;

/**
 *
 * @author jose
 */

import java.util.Arrays;

public class RegistroSobreArreglo implements RegistroDePrestamos {
    
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    static final int ARREGLO_LARGO_MIN = 8;
    static final int ARREGLO_FACTOR = 2;

    //ERROR Msgs
    static final String ERROR_MSG_OBTENER_FUERA_DE_LIMITES = "ERROR: Fuera de límites";
    static final String ERROR_MSG_TITULOS_MAS_PEDIDOS_N_NEG = "ERROR: La cantidad de titulos más pedidos debe ser positiva";
    private Prestamo[] prestamos;
    private int largoUtilizado;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    
    public RegistroSobreArreglo() {
        this.prestamos = new Prestamo[ARREGLO_LARGO_MIN];
        this.largoUtilizado = 0;
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    
    @Override
    public void registrar(Prestamo p){
        
        if (p == null){
            throw new NullPointerException();
        }

        if (largoUtilizado == prestamos.length){
            
            prestamos = Arrays.copyOf(
                prestamos, 
                prestamos.length * ARREGLO_FACTOR);
        }

        prestamos[largoUtilizado] = p;
        largoUtilizado++;
    }

    @Override
    public int cantidad(){
        return largoUtilizado;
    }

    @Override
    public Prestamo obtener(int i){
        if (i < 0 || i >= largoUtilizado){
            throw new IndexOutOfBoundsException(ERROR_MSG_OBTENER_FUERA_DE_LIMITES);
        }

        return prestamos[i];
    }

    @Override
    public int[] padrones(){
        int[] padrones = new int[largoUtilizado]; // Peor caso: Todos distintos

        for (int i=0; i < largoUtilizado; i++){
            padrones[i] = prestamos[i].padron();
        }

        //tomo todos los padrones y los pongo en un arreglo
        int[] unicos = new int[largoUtilizado];
        int len=0;

        //por c/valor del arreglo, lo recorro para ver si existe. Si no existe la pongo en un arreglo "Unicos"
        for(int i = 0; i < padrones.length; i++){
            boolean existe = false;
            for(int j=0; j < i; j++){
                if (padrones[j] == padrones[i]){
                    existe = true;
                    break;
                }
            }
            if(existe == false){
                unicos[len] = padrones[i];
                len++;
            }
        }

        //Return de una copia de "Unicos" con el len justo
        return Arrays.copyOf(unicos, len);
    }

    @Override
    public Prestamo[] prestamosDe(int padron){

        Prestamo[] arreglo = new Prestamo[largoUtilizado]; // Peor caso: todos los prestamos de la misma persona

        int j = 0;
        
        for(int i = 0; i < largoUtilizado; i++){
            if(prestamos[i].padron() == padron){
                arreglo[j] = prestamos[i];
                j++;
            }
        }

        return Arrays.copyOf(arreglo, j);
    }

    @Override
    public String[] titulosMasPedidos(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException(ERROR_MSG_TITULOS_MAS_PEDIDOS_N_NEG);
        }

        //Peor caso: todos los prestamos tienen titulos distintos
        String[] titulos = new String[largoUtilizado];
        int[] cantidadPedidos = new int[largoUtilizado];

        int cantidadTitulos = 0;

        // Contamos cuántas veces aparece cada título.
        for (int i = 0; i < largoUtilizado; i++) {

            String tituloActual = prestamos[i].titulo();

            // posición del titulo no registrado
            int posicion = 0;
            boolean tituloYaExiste = false;

            for (int j = 0; j < cantidadTitulos; j++) {
                if (titulos[j].equals(tituloActual)) {
                    tituloYaExiste = true;
                    posicion = j;
                    break;
                }
            }

            if (tituloYaExiste == false) {
                // Es un título nuevo.
                titulos[cantidadTitulos] = tituloActual;
                cantidadPedidos[cantidadTitulos] = 1;
                cantidadTitulos++;
            } else {
                // El título ya existe: aumenta su contador.
                cantidadPedidos[posicion]++;
            }
        }

        //Ordenamiento de titulos por cantidad de pedidos, en caso de empate, alfabéticamente.
        for (int i = 0; i < cantidadTitulos - 1; i++) {

            for (int j = i + 1; j < cantidadTitulos; j++) {

                boolean debeIntercambiar = false;

                // Se comparan la cantidad de pedidos
                if (cantidadPedidos[j] > cantidadPedidos[i]) {
                    debeIntercambiar = true;
                }

                // Si empatan, compara alfabéticamente.
                else if (cantidadPedidos[j] == cantidadPedidos[i]
                        && titulos[j].compareTo(titulos[i]) < 0) {
                    debeIntercambiar = true;
                }

                if (debeIntercambiar) {

                    // Intercambiar títulos.
                    String auxTitulo = titulos[i];
                    titulos[i] = titulos[j];
                    titulos[j] = auxTitulo;

                    // Intercambiar cantidades.
                    int auxCantidad = cantidadPedidos[i];
                    cantidadPedidos[i] = cantidadPedidos[j];
                    cantidadPedidos[j] = auxCantidad;
                }
            }
        }

        // Si n es mayor que la cantidad de títulos,
        // devuelve todos los títulos.
        int len = Math.min(n, cantidadTitulos);

        String[] titulosMasPedidos = new String[len];

        //No solo copia sino que también le agrega la cant de pedidos al final 
        for (int i = 0; i < len; i++) {
            titulosMasPedidos[i] = String.format("%-30s%d", titulos[i], cantidadPedidos[i]);
        }
        return titulosMasPedidos;
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
