package guia.i03_poo.i03_medio.e03;
/**
 * 3. Polimorfismo con figuras. Interfaz Figura + Circulo/Rectangulo; método que sume las áreas
de un Figura[]; test.
 */

public interface Figura{
    double area();

    static double sumaDeAreas(Figura[] figuras){
        if(figuras == null || figuras.length == 0){
            throw new IllegalArgumentException("Arreglo de figuras vacío");
        }

        double areaTotal = 0;

        for (Figura actual: figuras){
            areaTotal += actual.area();
        }

        return areaTotal;
    }
}