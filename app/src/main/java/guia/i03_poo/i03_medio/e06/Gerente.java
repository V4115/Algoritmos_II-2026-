package guia.i03_poo.i03_medio.e06;

import java.math.BigDecimal;

/**
 * 6. Herencia con super. Empleado(sueldoBase) con sueldo(); Gerente que agrega un bono
redefiniendo sueldo() y usando super; test.
 */

public class Gerente extends Empleado{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private BigDecimal bono;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Gerente(String nombre, BigDecimal sueldoBase, BigDecimal bono){
        super(nombre, sueldoBase);
        setBono(bono);
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public BigDecimal getBono(){
        return this.bono;
    }
    @Override
    public BigDecimal sueldo(){
        //Suma en bigDecimal
        return getBono().add(super.getSueldoBase());
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setBono(BigDecimal bono){
        if(bono == null || bono.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Bono invalido");
        }
        this.bono = bono;
    }
}