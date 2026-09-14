package guia.i03_poo.i04_dificil.e01;

/**
 * Animal (nombre, especie, peso)
 */

public abstract class Animal {

    // variables privadas
    private final String nombre;
    private final String especie;
    private final int peso;

    //constructor
    public Animal(String nombre, String especie, int peso) {
        this.nombre = nombre;
        this.especie = especie;
        this.peso = peso;
    }
    
    //getters
    public String getNombre(){
        return nombre;
    }

    public String getEspecie(){
        return especie;
    }

    public int getPeso(){
        return peso;
    }


}