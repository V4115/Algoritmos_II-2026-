package guia.i03_poo.i04_dificil.e01;

/**
 * 1. TDA Zoologico. Animal (nombre, especie, peso) + Zoologico sobre arreglo con capacidad
por constructor: ingresar(animal), buscarPorNombre(nombre), elMasPesado() (con null si no
hay animales — checklist 13), pesoTotal() CALCULADO (checklist 15), toString con todo el
contenido. Pasale el checklist completo antes de entregar; el test incluye los casos inválidos
(checklist 20).
 */

public class Zoologico{

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Animal[] animales;
    private int cantidad;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Zoologico(int capacidad){
        if(capacidad < 0){
            throw new IllegalArgumentException("No se puede ingresar una capacidad negativa");
        }
        this.animales = new Animal[capacidad];
        this.cantidad = 0;
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------        
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //-toString
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("Zoologico{cantidad: ").append(this.cantidad)
            .append(", capacidad: ").append(this.animales.length)
            .append(", animales: [");
            for (int i = 0; i < this.cantidad; i++){
                sb.append(this.animales[i]);
                if (i < this.cantidad - 1){
                    sb.append(", ");
                }
            }
            sb.append("]}");
            return sb.toString();
            }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public void ingresar(Animal animal){
        if(animal == null){
            throw new IllegalArgumentException("Puntero nulo");
        }

        if(this.cantidad == animales.length){
            throw new IllegalArgumentException("Zoologico lleno");
        }

        this.animales[this.cantidad++] = animal;
    }

    public Animal buscarPorNombre(String nombre){
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre vacío");
        }

        for (int i = 0; i < this.cantidad; i++){
            if (nombre.equals(animales[i].getNombre())) {
                return animales[i];
            }
        }

        return null;
    }

    public Animal elMasPesado(){
        if(this.cantidad == 0){
            return null;
        }

        Animal actualMasPesado = this.animales[0];

        for (int i = 1; i < this.cantidad; i++){
            if (this.animales[i].getPeso() > actualMasPesado.getPeso()){
                actualMasPesado = this.animales[i];
            }
        }
        return actualMasPesado;
    }

    public double pesoTotal(){
        double pesoTotal = 0;

        for (int i = 0; i < this.cantidad; i++){
            pesoTotal += this.animales[i].getPeso();
        }

        return pesoTotal;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public int getCantidad(){
        return this.cantidad;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}

