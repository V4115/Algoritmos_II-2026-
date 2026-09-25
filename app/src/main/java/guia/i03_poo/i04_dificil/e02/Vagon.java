package guia.i03_poo.i04_dificil.e02;
/**
 * 2. TDA Tren con vagones. Vagon (capacidad en kilos, carga actual; su cargar valida y ES SU
lógica — checklist 6: el tren delega) + Tren: engancharVagon, desengancharUltimo, cargar(kilos)
que reparte al primer vagón con lugar (y lanza si no entra en ninguno), cargaTotal() calculada,
vagonMasCargado() (con null). Test del reparto y de los bordes.
 */

public class Vagon{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final double capacidad;
    private double cargaActual;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    
    //sin carga
    public Vagon(double capacidad){
        
        if (capacidad < 0 || Double.isNaN(capacidad)){
            throw new IllegalArgumentException("Capacidad invalida en el vagon");
        }
        this.capacidad = capacidad;
        setCargaActual(0);
    }
    
    //con carga incluida
    public Vagon(double capacidad, double cargaActual){
        
        if (capacidad < 0 || Double.isNaN(capacidad)){
            throw new IllegalArgumentException("Capacidad invalida en el vagon");
        }
        this.capacidad = capacidad;
        setCargaActual(cargaActual);

    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        public String toString(){
            return "Vagon={capacidad = " + getCapacidad() + 
                    ", carga actual = " + getCarga() + "}";
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public double getCarga(){
        return this.cargaActual;
    }

    public double getCapacidad(){
        return this.capacidad;
    }

    public double getLugarLibre(){
        return getCapacidad() - getCarga();
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    private void setCargaActual(double nuevaCargaActual){
        if (nuevaCargaActual < 0 || Double.isNaN(nuevaCargaActual)){
            throw new IllegalArgumentException("Carga invalida en el vagon");
        }

        if(nuevaCargaActual > getCapacidad()){
            throw new IllegalArgumentException("La carga supera la capacidad del vagon");
        }

        this.cargaActual = nuevaCargaActual;
    }

    public void cargar(double carga){
        setCargaActual(getCarga() + carga);
    }

    public void descargar(double descarga){
        if(descarga > getCarga()){
            throw new IllegalArgumentException("Se intenta descargar más de lo que hay");
        }

        setCargaActual(getCarga() - descarga);
    }
}