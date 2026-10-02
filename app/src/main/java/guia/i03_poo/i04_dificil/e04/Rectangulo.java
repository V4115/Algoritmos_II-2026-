package guia.i03_poo.i04_dificil.e04;
/**
 * 4. Jerarquía completa. Interfaz Figura + clase abstracta + tres figuras concretas, Comparable
por área y toString; test de polimorfismo y orden.
*/

public class Rectangulo extends FiguraAbstracta{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double base;
    private double altura;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Rectangulo(double base, double altura){
        super("Revtangulo");

        if(base <= 0){
            throw new IllegalArgumentException("Base Invalida");
        }
        if(altura <= 0){
            throw new IllegalArgumentException("Altura Invalida");
        }

        this.base=base;
        this.altura=altura;
    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public double area(){
        return this.altura * this.base;
    }
    public double perimetro(){
        return 2*this.altura + 2*this.base; 
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public double getBase(){
        return this.base;
    }
    public double getAltura(){
        return this.altura;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}