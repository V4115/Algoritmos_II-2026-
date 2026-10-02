package guia.i03_poo.i04_dificil.e04;
/**
 * 4. Jerarquía completa. Interfaz Figura + clase abstracta + tres figuras concretas, Comparable
por área y toString; test de polimorfismo y orden.
 */

import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        // se guardan como FiguraAbstracta[] porque ahí vive el Comparable (Arrays.sort lo necesita),
        // pero se las recorre y usa como Figura para probar el polimorfismo.
        FiguraAbstracta[] figuras = new FiguraAbstracta[]{
                new Circulo(3),
                new Rectangulo(4, 5),
        };

        System.out.println("Polimorfismo (cada una calcula su propia area/perimetro):");
        double areaTotal = 0;
        for (Figura f : figuras) {
            System.out.println("  " + f);
            areaTotal += f.area();
        }
        System.out.println("Area total: " + String.format("%.2f", areaTotal));

        Arrays.sort(figuras);
        System.out.println("\nOrdenadas por area (ascendente):");
        for (Figura f : figuras) {
            System.out.println("  " + f);
        }

        System.out.println("\nBordes invalidos:");
        try {
            new Circulo(0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Circulo(0) -> " + e.getMessage());
        }
        try {
            new Rectangulo(4, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("  Rectangulo(4,-1) -> " + e.getMessage());
        }
    }
}