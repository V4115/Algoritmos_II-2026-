package guia.i03_poo.i02_facil.e06;
/**
 * 6. Herencia. Vehiculo(marca) con Auto y Moto que redefinan descripcion(); test.
 */

public class Moto extends Vehiculo{

    public Moto(String marca){
        super(marca);
    }

    @Override
    public String descripcion(){
        return "Moto de marca: " + getMarca();
    }
}