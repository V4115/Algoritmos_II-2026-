package guia.i03_poo.i03_medio.e06;
/**
 * 6. Herencia con super. Empleado(sueldoBase) con sueldo(); Gerente que agrega un bono
redefiniendo sueldo() y usando super; test.
 */

import java.math.BigDecimal;

public class Empleado{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre;
    private BigDecimal sueldoBase;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Empleado(String nombre, BigDecimal sueldoBase){
        setNombre(nombre);
        setSueldoBase(sueldoBase);
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public BigDecimal getSueldoBase(){
        return this.sueldoBase;   
    }
    public String getNombre(){
        return this.nombre;
    }
    public BigDecimal sueldo(){
        return getSueldoBase();
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setSueldoBase(BigDecimal nuevoSueldoBase){
        if (nuevoSueldoBase == null || nuevoSueldoBase.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Sueldo Invalido");
        }
        this.sueldoBase = nuevoSueldoBase;
    }
    public void setNombre(String nuevoNombre){
        if(nuevoNombre == null || nuevoNombre.isBlank()){
            throw new IllegalArgumentException("Nombre Invalido");
        }

        this.nombre = nuevoNombre;
    }
}
