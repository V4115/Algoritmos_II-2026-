package guia.i03_poo.i02_facil.e10;
/**
 * 10. Invariante. Termometro que rechace temperaturas bajo el cero absoluto (-273.15 C) con una excepción;
test.
 */

public class Termometro{
    public static final double CERO_ABSOLUTO = -273.15;

    private double temperatura;

    public Termometro(double temp){
        this.setTemperatura(temp);
    }

    public double getTemperatura(){
        return this.temperatura;
    }

    public void setTemperatura(double temp){
        if (temp < CERO_ABSOLUTO){
            throw new IllegalArgumentException("La temperatura no puede ser menor al cero absoluto.");
        }
        this.temperatura = temp;
    }

    public static void main(String[] args){

        try {
            Termometro termometro = new Termometro(-50);
            System.out.println("Temperatura: " + termometro.getTemperatura());

            termometro.setTemperatura(CERO_ABSOLUTO - 50);
            System.out.println("Temperatura: " + termometro.getTemperatura());

            termometro.setTemperatura(CERO_ABSOLUTO + 50);
            System.out.println("Temperatura: " + termometro.getTemperatura());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}