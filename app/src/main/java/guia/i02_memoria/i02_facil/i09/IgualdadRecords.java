package guia.i02_memoria.i02_facil.i09;

/**
 * 9. Igualdad de records. Test que dos Punto(1,2) cumplen equals pero no ==.
 */

public class IgualdadRecords {

    public static void main(String[] args) {
        Punto p1 = new Punto(1, 2);
        Punto p2 = new Punto(1, 2);

        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
    }

    public record Punto(int x, int y) {
        
    }
}