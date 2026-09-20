package guia.i03_poo.i03_medio.e03;
/**
 * 3. Polimorfismo con figuras. Interfaz Figura + Circulo/Rectangulo; método que sume las áreas
de un Figura[]; test.
 */

public class Main{
    public static void main(String[] args) {
        //Figuras
        Circulo circulo = new Circulo(9);
        Cuadrado cuadrado = new Cuadrado(4);
        Rectangulo rectangulo = new Rectangulo(5, 3);

        Figura[] arregloFig = {circulo, cuadrado, rectangulo};

        for (Figura actual: arregloFig){
            System.out.println(actual.toString() + " Area = " + actual.area());
        }

        System.out.println("Suma de las areas = " + Figura.sumaDeAreas(arregloFig));
    }
}