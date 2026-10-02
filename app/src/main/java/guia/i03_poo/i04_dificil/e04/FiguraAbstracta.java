package guia.i03_poo.i04_dificil.e04;
/**
 * 4. Jerarquía completa. Interfaz Figura + clase abstracta + tres figuras concretas, Comparable
por área y toString; test de polimorfismo y orden.
 */

public abstract class FiguraAbstracta implements Figura, Comparable<Figura>{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String nombre;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    protected FiguraAbstracta(String nombre){
        this.nombre = nombre;
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return String.format("%s: {area = %.2f, perimetro = %.2f}", 
            this.nombre, 
            area(), 
            perimetro());
        }
        //equals
        @Override
        public int compareTo(Figura otro){
            return Double.compare(this.area(), otro.area());
        }
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public String nombre(){
        return this.nombre;
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}