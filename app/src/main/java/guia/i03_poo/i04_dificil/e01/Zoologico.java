package guia.i03_poo.i04_dificil.e01;

/**
 * 1. TDA Zoologico. Animal (nombre, especie, peso) + Zoologico sobre arreglo con capacidad
por constructor: ingresar(animal), buscarPorNombre(nombre), elMasPesado() (con null si no
hay animales — checklist 13), pesoTotal() CALCULADO (checklist 15), toString con todo el
contenido. Pasale el checklist completo antes de entregar; el test incluye los casos inválidos
(checklist 20).
 */

import java.util.Arrays;

public class Zoologico{

    //Ctes de zoologico
    private static final int  ANIMALES_ARREGLO_FACTOR = 2;

    //variables privadas
    private Animal[] animales;
    private int cantidad;

    //constructor
    public Zoologico(int capacidad){
        animales = new Animal[capacidad];
        cantidad = 0;
    }

    //metodos publicos
    public void ingresar(Animal animal){
        if(cantidad >= animales.length){
            animales = agrandarAnimales(animales, cantidad*);
        }
    }

    //metodos privados

    /**
     * Agranda el arreglo de animales
     * @param animales arreglo de animales
     * @param len nueva longitud del arreglo, no puede ser menor a la longitud actual
     * @return arreglo de animales con nueva longitud
     */
    
    private Animal[] agrandarAnimales(Animal[] animales, int len){
        return Arrays.copyOf(animales, len);
    }
}

