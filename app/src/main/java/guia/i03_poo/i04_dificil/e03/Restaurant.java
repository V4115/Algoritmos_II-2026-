package guia.i03_poo.i04_dificil.e03;
/**
 * 3. TDA Restaurant con mesas. Mesa (número, capacidad, estado ENUM LIBRE/RESERVADA/OCUPADA
— checklist 11: dibujá el diagrama de estados en el javadoc) + Restaurant: reservar(personas)
asigna la mesa LIBRE más chica en la que entren (ésa es la problemática — pensala bien),
ocupar(numero), liberar(numero, gasto), recaudacion() calculada y mesasLibres(). Test de
todas las transiciones de estado inválidas (ocupar sin reserva, liberar una libre, reservar sin
lugar).
 */

import java.math.BigDecimal;

public class Restaurant{
    
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Mesa[] mesas;
    private int cantDeMesas;
    private int cantReservas;
    private int cantOcupadas;
    private int cantLibres;
    private BigDecimal recaudacion;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Restaurant(Mesa[] mesas){
        if(mesas.length <= 0){
            throw new IllegalArgumentException("Cantidad de mesas invalida");
        }

        this.cantDeMesas = mesas.length;
        this.cantLibres = this.cantDeMesas;
        this.cantOcupadas = 0;
        this.cantReservas = 0;
        this.recaudacion = new BigDecimal("0");

        this.mesas = mesas;
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        public String toString(){
            StringBuilder texto = new StringBuilder("Restaurant (recaudación: $")
                .append(recaudacion()).append(')');
            
            for (Mesa mesa : mesas) {
                texto.append("\n  ").append(mesa);
            }
            return texto.toString();
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public int reservar(int cantDePersonas){
        Mesa rta = null;

        for (Mesa actual : mesas){
            if(rta == null && 
                actual.getEstado() == EstadoMesa.LIBRE && 
                actual.getCapacidad() >= cantDePersonas){
                
                rta = actual;
            }else if(rta != null &&
                        actual.getEstado() == EstadoMesa.LIBRE && 
                        actual.getCapacidad() < rta.getCapacidad() &&
                        actual.getCapacidad() >= cantDePersonas){
                    
                rta = actual;
            }
        }
        if(rta == null){
            throw new IllegalArgumentException("No hay mesas disponibles");
        }

        rta.reservar();
        this.cantReservas++;
        this.cantLibres--;

        return rta.getNumero();
    }

    public void ocupar(int Numero){
        buscarMesa(Numero).ocupar();
        this.cantOcupadas++;
        this.cantReservas--;
    }

    public void liberar(int numero, BigDecimal gasto){
        if(gasto == null){
            throw new IllegalArgumentException("Gasto nulo");
        }
        if(gasto.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El gasto no puede ser negativo");
        }
        buscarMesa(numero).liberar();
        this.recaudacion = this.recaudacion.add(gasto);
        this.cantLibres++;
        this.cantOcupadas--;
    }

    public BigDecimal recaudacion(){
        return this.recaudacion;
    }

    public int mesasTotales(){
        return this.cantDeMesas;
    }

    public int mesasLibres(){
        return this.cantLibres;
    }
    public int mesasResrvadas(){
        return this.cantReservas;
    }
    public int mesasOcupadas(){
        return this.cantOcupadas;
    }

    private Mesa buscarMesa(int numero){
        for(Mesa actual : mesas){
            if(actual.getNumero() == numero){
                return actual;
            }
        }

        throw new IllegalArgumentException("No existe una mesa con ese número");
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public int getCantidadDeMesas(){
        return this.cantDeMesas;
    }
    public int getCantidadDeMesasLibres(){
        return this.cantLibres;
    }
    public int getCantidadDeMesasOcupadas(){
        return this.cantOcupadas;
    }
    public int getCantidadDeMesasReservadas(){
        return this.cantReservas;
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}