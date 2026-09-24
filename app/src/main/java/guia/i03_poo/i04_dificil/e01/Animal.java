package guia.i03_poo.i04_dificil.e01;

/**
 * 1. TDA Zoologico. Animal (nombre, especie, peso) + Zoologico sobre arreglo con capacidad
por constructor: ingresar(animal), buscarPorNombre(nombre), elMasPesado() (con null si no
hay animales — checklist 13), pesoTotal() CALCULADO (checklist 15), toString con todo el
contenido. Pasale el checklist completo antes de entregar; el test incluye los casos inválidos
(checklist 20).
 */

import java.util.Objects;

public abstract class Animal {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre;
    private String especie;
    private double peso;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Animal(String nombre, String especie, double peso){
        setNombre(nombre);
        setEspecie(especie);
        setPeso(peso);
    }
    
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Animal{nombre: " + this.getNombre() + 
                    ", especie: " + this.getEspecie() + 
                    ", peso: " + this.getPeso() + "}";
        }
        //equals
        @Override
        public boolean equals(Object obj){
            if (this == obj){
                return true;
            }
            if(!(obj instanceof Animal otro)){
                return false;
            }

            if(!(otro.getNombre().equals(this.getNombre()))){
                return false;
            }
            if(!(otro.getEspecie().equals(this.getEspecie()))){
                return false;
            }

            return Double.compare(otro.getPeso(), this.getPeso()) == 0;
        }
        //hashCode
        @Override
        public int hashCode() {
            return Objects.hash(this.nombre, this.especie, this.peso);
        }   
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    public abstract String sonido();  // sin cuerpo, sin implementación por defecto

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public String getNombre(){
        return this.nombre;
    }
    public String getEspecie(){
        return this.especie;
    }
    public double getPeso(){
        return this.peso;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public final void setNombre(String nuevoNombre){
        if (nuevoNombre == null || nuevoNombre.isBlank()){
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nuevoNombre;
    }

    public final void setEspecie(String nuevaEspecie){
        if (nuevaEspecie == null || nuevaEspecie.isBlank()){
            throw new IllegalArgumentException("Especie vacía");
        }
        this.especie = nuevaEspecie;
    }

    public final void setPeso(double nuevoPeso){
        if(nuevoPeso <= 0 || Double.isNaN(nuevoPeso)){
            throw new IllegalArgumentException("El peso ingresado no es un número válido");
        }

        this.peso = nuevoPeso;
    }
}