package tps.tp2.modelos;

public class Heroe {
    private int energia;
    //private Posicion posicionActual;
    //private final Inventario(seria un arreglo de items, Item[]) inventario;

    public Heroe(int energiaInicial) {
        if (energiaInicial <= 0) {
            throw new IllegalArgumentException("Energia inicial invalida: " + energiaInicial);
        }
        this.energia = energiaInicial;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void agregarEnergia(int energia){
        if (energia <= 0) {
            throw new IllegalArgumentException("Energia a agregar invalida: " + energia);
        }
        this.energia += energia;
    }

    public void quitarEnergia(int energia) {
        if (energia <= 0) {
            throw new IllegalArgumentException("Energia a quitar invalida: " + energia);
        }
        this.energia = Math.max(0, this.energia - energia);
    }

    public boolean tieneEnergia() {
        return this.energia != 0;
    }
}
