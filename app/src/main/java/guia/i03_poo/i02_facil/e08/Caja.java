package guia.i03_poo.i02_facil.e08;

/**
 * 8. Genérico Caja<T>. Con get/set; test usándola con String y con Integer.
 */

public class Caja<T>{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private T contenido;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Caja(T contenido){
        this.contenido = contenido;
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
        String nombre = "Pablo";
        Integer edad = 20;

        Caja<String> caja1 = new Caja<>(nombre);
        Caja<Integer> caja2 = new Caja<>(edad);

        System.out.println("Contenido de la caja1: " + caja1.getContenido());

        System.out.println("Contenido de la caja2: " + caja2.getContenido());

    }
}