package guia.i03_poo.i04_dificil.e01;

/**
 * 1. TDA Zoologico. Animal (nombre, especie, peso) + Zoologico sobre arreglo con capacidad
por constructor: ingresar(animal), buscarPorNombre(nombre), elMasPesado() (con null si no
hay animales — checklist 13), pesoTotal() CALCULADO (checklist 15), toString con todo el
contenido. Pasale el checklist completo antes de entregar; el test incluye los casos inválidos
(checklist 20).
 */

public class Zoologico{

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
    }

    //metodos privados

}

