package guia.i03_poo.i03_medio.e04;

/**
 * 4. Ordenar por criterio. Clase Producto(precio) Comparable; ordená un arreglo con Arrays.sort;
test.
 */

public class Producto implements Comparable<Producto>{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private double precio;
    private String nombre;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Producto(String nombre, double precio){
        setPrecio(precio);
        setNombre(nombre);
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
    @Override
    public String toString(){
        return "Producto: " + this.nombre + " $" + this.precio;
    }
        //equals
        //hashCode
    @Override
    public int compareTo(Producto otro){
        return Double.compare(this.precio, otro.getPrecio());
    }
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public String getNombre(){
        return this.nombre;
    }

    public double getPrecio(){
        return this.precio;
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setNombre(String nuevoNombre){
        if(nuevoNombre == null || nuevoNombre.isBlank()){
            throw new IllegalArgumentException("nombre invalido");
        }
        this.nombre = nuevoNombre;
    }
    public void setPrecio(double nuevoPrecio){
        if(nuevoPrecio < 0 || Double.isNaN(nuevoPrecio)){
            throw new IllegalArgumentException("Precio invalido");
        }
        this.precio = nuevoPrecio;
    }
}