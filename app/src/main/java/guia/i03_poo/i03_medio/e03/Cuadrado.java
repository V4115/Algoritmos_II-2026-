package guia.i03_poo.i03_medio.e03;
/**
 * 3. Polimorfismo con figuras. Interfaz Figura + Circulo/Rectangulo; método que sume las áreas
de un Figura[]; test.
 */

public class Cuadrado implements Figura{

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double base;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Cuadrado(double base){
        this.setBase(base);
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Cuadrado = { base = " + this.base + " }";
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    @Override
    public double area(){
        return this.base * this.base;
    }
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setBase(double nuevaBase){
        if(nuevaBase <= 0){
            throw new IllegalArgumentException("Base Inválida");
        }

        this.base = nuevaBase;
    }

}