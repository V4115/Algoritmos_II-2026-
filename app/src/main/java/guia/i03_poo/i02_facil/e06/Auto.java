package guia.i03_poo.i02_facil.e06;
/**
 * 6. Herencia. Vehiculo(marca) con Auto y Moto que redefinan descripcion(); test.
 */

public class Auto extends Vehiculo{

    public Auto(String marca){
        super(marca);
    }

    @Override
    public String descripcion(){
        return "Auto de marca: " + getMarca();
    }
}