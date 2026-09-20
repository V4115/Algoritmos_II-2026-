package guia.i03_poo.i03_medio.e01;
/**
 * 1. TDA Cadena. Sobre char[] (sin String por dentro, salvo en toString): largo(), caracterEn(i),
contiene(char), enMayusculas() e invertida() (devuelven una Cadena NUEVA: inmutable),
equals y toString. Test.
 */

import  java.util.Arrays;

public final class Cadena{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private char[] caracteres;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    
    public Cadena(char[] arreglo){
        if (arreglo == null){
            throw new IllegalArgumentException("Vector de caracteres vacío");
        }

        this.caracteres = Arrays.copyOf(arreglo, arreglo.length);
    }

    public Cadena(String string){
        if (string == null){
            throw new IllegalArgumentException("String vacío");
        }

        this.caracteres = string.toCharArray();
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return new String(this.caracteres);
        }

        //equals
        @Override
        public boolean equals(Object otro){
            if (this == otro){
                return true;
            }
            if(!(otro instanceof Cadena otraCadena)){
                return false;
            }

            return Arrays.equals(this.caracteres, otraCadena.caracteres);
        }
        //hashCode
        @Override
        public int hashCode(){
            return Arrays.hashCode(this.caracteres);
        }
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public int largo(){
        return caracteres.length;
    }

    public char caracterEn(int i){
        if (i < 0 || i >=largo()){
            throw new IllegalArgumentException("Posición invalida");
        }

        return this.caracteres[i];
    }

    public int contiene(char c){
        for (int i = 0; i < largo(); i++){
            if(c == this.caracteres[i]){
                return i;
            }
        }
        return -1;
    }

    public Cadena enMayusculas(){
        
        char [] nuevo = new char[largo()];

        for (int i = 0; i < largo(); i++){
            nuevo[i] = Character.toUpperCase(this.caracteres[i]);
        }
        return new Cadena(nuevo);
    }

    public Cadena invertida(){
        char [] nuevo = new char[largo()];

        for (int i = 0; i < largo(); i++){
            nuevo[i] = Character.toUpperCase(this.caracteres[largo()- i - 1]);
        }
        return new Cadena(nuevo);
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Cadena saludo = new Cadena("hola mundo");
        System.out.println("Cadena: " + saludo);
        System.out.println("largo(): " + saludo.largo());
        System.out.println("caracterEn(4): '" + saludo.caracterEn(4) + "'");
        System.out.println("contiene('m'): " + saludo.contiene('m'));
        System.out.println("contiene('z'): " + saludo.contiene('z'));
        System.out.println("enMayusculas(): " + saludo.enMayusculas());
        System.out.println("invertida(): " + saludo.invertida());
        System.out.println("La original NO cambió (inmutable): " + saludo);
        System.out.println("equals con otra igual: " + saludo.equals(new Cadena("hola mundo")));
    }
}