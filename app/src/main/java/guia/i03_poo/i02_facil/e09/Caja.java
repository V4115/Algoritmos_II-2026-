package guia.i03_poo.i02_facil.e09;
/**
 * 9. Constructor copia. Clase con constructor copia; test que la copia es independiente.
 */

public class Caja<T>{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private T contenido;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Caja(T contenido){
        this.contenido = contenido;
    }

    public Caja(Caja<T> otro){
        this.contenido = otro.getContenido();
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public T getContenido(){
        return this.contenido;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setContenido(T contenido){
        this.contenido = contenido;
    }

    public static void main(String[] args){
        Caja<String> original = new Caja<>("Hola qué tal?");
        Caja<String> copia = new Caja<>(original);

        System.out.println("Contenido de la caja original: " + original.getContenido());
        System.out.println("Contenido de la copia: " + copia.getContenido());

        original.setContenido("Todo bien");

        System.out.println("Contenido de la caja original después de setear: " + original.getContenido());
        System.out.println("Contenido de la copia después de setear: " + copia.getContenido());

    }
}