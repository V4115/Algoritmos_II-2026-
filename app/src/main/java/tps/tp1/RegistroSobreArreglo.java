package tps.tp1;

/**
 *
 * @author jose
 */

public class RegistroSobreArreglo {
    
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    static final int ARREGLO_LARGO_MIN = 8;
    static final int ARREGLO_INCREMENTO = 2;

    //ERROR Msgs
    static final String ERROR_MSG_FUERA_DE_LIMITES = "Fuera de límites";

    private Prestamo[] prestamos;
    private int largoUtilizado;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    
    public RegistroSobreArreglo() {
        this.prestamos = new Prestamo[ARREGLO_LARGO_MIN];
        this.largoUtilizado = 0;
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    
    public void registrar(Prestamo p){
        
        if (largoUtilizado == prestamos.length){
            
            prestamos = agrandarArregloDePrestamos(
                prestamos, 
                prestamos.length * ARREGLO_INCREMENTO);
        }

        prestamos[largoUtilizado] = p;
        largoUtilizado++;
    }

    public int cantidad(){
        return largoUtilizado;
    }

    public Prestamo obtener(int i){
        if (i < 0 || i >= largoUtilizado){
            throw new IndexOutOfBoundsException(ERROR_MSG_FUERA_DE_LIMITES);
        }

        return prestamos[i];
    }

    private Prestamo[] agrandarArregloDePrestamos(Prestamo[] original, int nuevoTamaño){
        
        Prestamo[] nuevo = new Prestamo[nuevoTamaño];
        
        clonarArregloDePrestamos(original, nuevo);
        
        return nuevo;
    }

    private void clonarArregloDePrestamos(Prestamo[] original, Prestamo[] clon){

        for (int i = 0; i < original.length && i < clon.length; i++){
            clon[i] = original[i];
        }
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
