package guia.i03_poo.i02_facil.e07;
/**
 * 7. Clase abstracta. Forma abstracta con area() abstracto; Cuadrado la implementa; test.
 */

public class Cuadrado extends Forma{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final double base;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Cuadrado(double base){
        this.base = base;
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    @Override
    public double area(){
        return base * base;
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}