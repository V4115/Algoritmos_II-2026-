package guia.i03_poo.i03_medio.e03;
/**
 * 3. Polimorfismo con figuras. Interfaz Figura + Circulo/Rectangulo; método que sume las áreas
de un Figura[]; test.
 */

public class Circulo implements Figura{

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double radio;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Circulo(double radio){
        this.setRadio(radio);
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Circulo = { radio = " + this.radio + " }";
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    @Override
    public double area(){
        return Math.PI * this.radio * this.radio;
    }
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setRadio(double nuevoRadio){
        if(nuevoRadio <= 0){
            throw new IllegalArgumentException("Radio Inválido");
        }

        this.radio = nuevoRadio;
    }

}