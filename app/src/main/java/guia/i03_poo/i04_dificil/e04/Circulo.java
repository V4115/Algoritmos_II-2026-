package guia.i03_poo.i04_dificil.e04;

/**
 * 4. Jerarquía completa. Interfaz Figura + clase abstracta + tres figuras concretas, Comparable
por área y toString; test de polimorfismo y orden.
 */

public class Circulo extends FiguraAbstracta{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double radio;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Circulo(double radio){
        super("Circulo");
        if(radio <= 0){
            throw new IllegalArgumentException("Radio Invalido");
        }
        this.radio = radio;
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
        return Math.PI * this.radio * this.radio;
    }

    @Override
    public double perimetro(){
        return Math.PI * this.radio * 2;
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public double getRadio(){
        return this.radio;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}