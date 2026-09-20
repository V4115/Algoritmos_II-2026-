package guia.i03_poo.i03_medio.e03;
/**
 * 3. Polimorfismo con figuras. Interfaz Figura + Circulo/Rectangulo; método que sume las áreas
de un Figura[]; test.
 */

public class Rectangulo implements Figura{

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double base;
    private double altura;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Rectangulo(double base, double altura){
        this.setBase(base);
        this.setAltura(altura);
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Rectangulo = { base = " + this.base + ", altura = " + this.altura + " }";
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    @Override
    public double area(){
        return this.base * this.altura;
    }
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setBase(double nuevaBase){
        if(nuevaBase <= 0){
            throw new IllegalArgumentException("Base Inválida");
        }

        this.base = nuevaBase;
    }

    public void setAltura(double nuevaAltura){
        if(nuevaAltura <= 0){
            throw new IllegalArgumentException("Altura Inválida");
        }

        this.altura = nuevaAltura;
    }

}