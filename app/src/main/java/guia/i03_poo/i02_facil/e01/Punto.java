package guia.i03_poo.i02_facil.e01;

/**
 * 1. Punto encapsulado. Clase Punto con campos privados x, y, constructor y getters. Test.
 */

import java.util.Objects;

public class Punto implements Comparable<Punto> {
    //CLASS ATRIBUTES ------------------------------------------------------------------------------------------
    private double x = 0;
    private double y = 0;

    //CONSTRUCTORS----------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of Punto with default coordinates (0, 0).
     */
    public Punto() {
        // Default constructor
    }

    /**
     * Creates a new instance of Punto with the specified coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    
    public Punto(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Compares this point with another point for order. by x-coordinate first, then by y-coordinate.   
     * @param other The other point to compare with.
     * @return A negative integer, zero, or a positive integer as this point is less than, equal to, or greater than the other point.
     */
    @Override
    public int compareTo(Punto other) {
        if (this.x != other.x) {
            return Double.compare(this.x, other.x);
        }
        return Double.compare(this.y, other.y);
    }

    //ABSTRACT METHODS -----------------------------------------------------------------------------------------
    //CLASS METHODS --------------------------------------------------------------------------------------------
    //GENERAL METHODS ------------------------------------------------------------------------------------------
    
    /**
     * Returns a string representation of the point in the format "(x, y)".
     * @return A string representing the point.
     */
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
    
    /**
     * Checks if this point is equal to another object.
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Punto punto = (Punto) obj;
        return Double.compare(punto.x, x) == 0 && Double.compare(punto.y, y) == 0;
    }

    /*
     * Returns a hash code value for the point.
     * @return A hash code value for this point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }   

    //BEHAVIOR METHODS -----------------------------------------------------------------------------------------
    //GETTERS ------------------------------------------------------------------------------------------
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //SETTERS ------------------------------------------------------------------------------------------
    
    /**
     * Sets the x-coordinate of the point.
     * @param x The new x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the y-coordinate of the point.
     * @param y The new y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }
}