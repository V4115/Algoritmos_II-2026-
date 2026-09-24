package guia.i03_poo.i04_dificil.e01;

/**
 * 1. TDA Zoologico. Animal (nombre, especie, peso) + Zoologico sobre arreglo con capacidad
por constructor: ingresar(animal), buscarPorNombre(nombre), elMasPesado() (con null si no
hay animales — checklist 13), pesoTotal() CALCULADO (checklist 15), toString con todo el
contenido. Pasale el checklist completo antes de entregar; el test incluye los casos inválidos
(checklist 20).
 */

public class Main{
    public static void main(String[] args) {
        Animal[] animales = { 
                            new Perro("Paco", 40), 
                            new Gato("Manchita", 25), 
                            new Perro("Lola", 35),
                            new Gato("Nenio", 80),
                        };

        Zoologico zoo = new Zoologico(5);

        //Print de arreglo completo
        System.out.println("Todos mis animales");
        for (Animal actual : animales){
            System.out.println(actual.toString());
        }
        System.out.println();

        //Ingreso mis animales
        for (Animal actual : animales) {
            zoo.ingresar(actual);
        }

        //Busco por nombre e imprimo
        System.out.println("Busco a Manchita");
        System.out.println(zoo.buscarPorNombre("Manchita").toString());
        System.out.println();

        //busco el más pesado
        System.out.println("Busco a el más pesado");
        System.out.println(zoo.elMasPesado().toString());
        System.out.println();

        //imprimo el peso total del zoo
        System.out.println("Peso total del zoo es: " + zoo.pesoTotal());
        System.out.println();
    }
}