package guia.i03_poo.i02_facil.e02;
/**
 * 2. toString propio. Clase Persona(nombre, edad) con un toString legible; test que incluye el
nombre.
 */

public class Persona{
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
        Persona persona = new Persona("Alberto", 40);

        System.out.println(persona.toString());
    }
}