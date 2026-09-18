package guia.i03_poo.i02_facil.e04;
/**
 * 4. Orden natural. Clase Persona que implemente Comparable por edad; test del orden.
 */

public class Persona implements Comparable<Persona>{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String nombre;
    private final int edad;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    @Override
    public String toString(){
        return "Persona:{nombre: " + nombre + ", edad: " + edad +"}";
    }

    /**
     * Compara por edad: negativo si esta persona es mas joven,
     * positivo si es mayor, cero si tienen la misma edad.
     */
    @Override 
    public int compareTo(Persona otro){
        return Integer.compare(this.getEdad(), otro.getEdad());
    }
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public String getNombre(){
        return nombre;
    }
    public int getEdad(){
        return edad;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    public static void main(String[] args){
        Persona persona1 = new Persona("Alberto", 40);
        Persona persona2 = new Persona("Sandra", 35);
        Persona persona3 = new Persona("Santiago", 22);

        System.out.println(persona1.toString());
        System.out.println(persona2.toString());
        System.out.println(persona3.toString());

        int comparador = persona1.compareTo(persona2);
        
        if( comparador > 0){
            System.out.println(persona1.getNombre() + " es mayor que " + persona2.getNombre());
        }else if (comparador == 0){
            System.out.println(persona1.getNombre() + " y " + persona2.getNombre() + " tienen la misma edad");
        }else{
            System.out.println(persona2.getNombre() + " es mayor que " + persona1.getNombre());
        }

        comparador = persona1.compareTo(persona3);
        if( comparador > 0){
            System.out.println(persona1.getNombre() + " es mayor que " + persona3.getNombre());
        }else if (comparador == 0){
            System.out.println(persona1.getNombre() + " y " + persona3.getNombre() + " tienen la misma edad");
        }else{
            System.out.println(persona3.getNombre() + " es mayor que " + persona1.getNombre());
        }
    }

    
}