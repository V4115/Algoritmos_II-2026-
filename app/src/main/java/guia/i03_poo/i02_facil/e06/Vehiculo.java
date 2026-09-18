package guia.i03_poo.i02_facil.e06;
/**
 * 6. Herencia. Vehiculo(marca) con Auto y Moto que redefinan descripcion(); test.
 */

import java.util.Objects;

public class Vehiculo{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String marca;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Vehiculo(String marca){
        this.marca=marca;
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Vehiculo{marca = " + marca + "}";
        }
        //equals
        @Override
        public boolean equals(Object obj){
            if (this == obj){
                return true;
            }
            if(obj == null || getClass() != obj.getClass()){
                return false;
            }
            Vehiculo otro = (Vehiculo) obj;
            return Objects.equals(this.getMarca(), otro.getMarca());
        }

        //hashCode
        @Override
        public int hashCode(){
            return Objects.hash(getMarca());
        }
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public String getMarca(){
        return marca;
    }
    
    public String descripcion(){
        return "Vehiculo de marca: " + getMarca();
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}