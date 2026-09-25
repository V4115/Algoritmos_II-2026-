package guia.i03_poo.i04_dificil.e02;
/**
 * 2. TDA Tren con vagones. Vagon (capacidad en kilos, carga actual; su cargar valida y ES SU
lógica — checklist 6: el tren delega) + Tren: engancharVagon, desengancharUltimo, cargar(kilos)
que reparte al primer vagón con lugar (y lanza si no entra en ninguno), cargaTotal() calculada,
vagonMasCargado() (con null). Test del reparto y de los bordes.
 */

import java.util.Arrays;

public class Tren{

    //Ctes 
    private static final int VAGONES_INIT_LEN = 10;
    private static final int VAGONES_FACTOR = 2;

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Vagon[] vagones;
    private int cantVagones;
    private double cargaTotal;
    private Vagon vagonMasCargado;
    
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Tren(){
        this.vagones = new Vagon[VAGONES_INIT_LEN];
        setcantidadDeVagones(0);
        setCargaTotal(0);
        this.vagonMasCargado = null;
    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString() {
            StringBuilder texto = new StringBuilder("Tren (")
            .append(getCantidadDeVagones()).append('/').append(getCantidadMaximaDeVagones()).append(" vagones)");
            for (int i = 0; i < getCantidadDeVagones(); i++) {
                texto.append("\n  ").append(i).append(": ").append(vagones[i]);
            }
            return texto.toString();
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public void engancharVagon(Vagon nuevoVagon){
        if(nuevoVagon == null){
            throw new IllegalArgumentException("Vagon: Puntero nulo");
        }

        if (cantVagones >= getCantidadMaximaDeVagones()) {
            this.vagones = Arrays.copyOf(this.vagones , getCantidadMaximaDeVagones() * VAGONES_FACTOR);
        }

        this.vagones[cantVagones] = nuevoVagon;
        setcantidadDeVagones(++cantVagones);

        if(getVagonMasCargado() == null || vagonMasCargado.getCarga() < nuevoVagon.getCarga()){
            vagonMasCargado = nuevoVagon;
        }
    }

    public Vagon desengancharUltimo(){
        if(getCantidadDeVagones() == 0){
            throw new IllegalArgumentException("El tren no tiene vagones para desenganchar");
        }
        
        //el ultimo vagon sigue estando en memoria, pero como está inaccesible, es basura.
        Vagon aux = vagones[getCantidadDeVagones() - 1];

        setcantidadDeVagones(--cantVagones);

        return aux;
    }

    public boolean cargar(double carga){
        if (carga <= 0){
            throw new IllegalArgumentException("Tren: Carga Invalida");
        }

        for (Vagon actual : vagones){
            //por el momento solo cargo si puedo cargar todo en un vagon libre, por simplicidad
            if (actual.getLugarLibre() >= carga){
                actual.cargar(carga);
                setCargaTotal(getCargaTotal() + carga);
                return true;
            }
        }

        return false;
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    private int getCantidadMaximaDeVagones(){
        return this.vagones.length;
    }

    public double getCargaTotal(){
        return this.cargaTotal;
    }

    public int getCantidadDeVagones(){
        return this.cantVagones;
    }

    public Vagon getVagonMasCargado(){
        return this.vagonMasCargado;
    }
    //SETTERS SIMPLES ----------------------------------------------------------------------------------------- 

    private void setcantidadDeVagones(int nuevaCantidadDeVagones){
        if(nuevaCantidadDeVagones < 0 || nuevaCantidadDeVagones > getCantidadMaximaDeVagones()){
            throw new IllegalArgumentException("Tren: cantidad de vagones invalida");
        }

        this.cantVagones = nuevaCantidadDeVagones;
    }

    //No pregunta si es una cargaTotal valida, más allá de si es negativo o NaN: 
    //ejm: si no hay vagones y le asignas 20kg de carga total, no se da cuenta
    private void setCargaTotal(double nuevaCargaTotal){
        if(nuevaCargaTotal < 0 || Double.isNaN(cargaTotal)){
            throw new IllegalArgumentException("CargaTotal invalida");
        }

        this.cargaTotal = nuevaCargaTotal;
    }
}