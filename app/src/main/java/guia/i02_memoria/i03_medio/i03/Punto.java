package guia.i02_memoria.i03_medio.i03;

/**
 * 3. equals/hashCode propios. Clase Punto (no record) con equals y hashCode bien hechos; test del
contrato (dos iguales → equals true y mismo hashCode).
 */

public class Punto {
    private final int x;
    private final int y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
         return true;
        }
        //This check ensures that the object is not null and this and obj are the same class type
        if (obj == null || getClass() != obj.getClass()){ 
            return false;
        }
        
        Punto punto = (Punto) obj;

        return x == punto.x && y == punto.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Punto(" + x + ", " + y + ')';
        }

    public static void main(String[] args) {
        Punto p1 = new Punto(1, 2);
        Punto p2 = new Punto(1, 2);
        Punto p3 = new Punto(3, 4);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);

        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // false

        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
        System.out.println("p3.hashCode(): " + p3.hashCode());
    }
}