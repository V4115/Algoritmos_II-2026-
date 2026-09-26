package guia.i03_poo.i04_dificil.e03;
/**
 * 3. TDA Restaurant con mesas. Mesa (número, capacidad, estado ENUM LIBRE/RESERVADA/OCUPADA
— checklist 11: dibujá el diagrama de estados en el javadoc) + Restaurant: reservar(personas)
asigna la mesa LIBRE más chica en la que entren (ésa es la problemática — pensala bien),
ocupar(numero), liberar(numero, gasto), recaudacion() calculada y mesasLibres(). Test de
todas las transiciones de estado inválidas (ocupar sin reserva, liberar una libre, reservar sin
lugar).
 */

public class Mesa{
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private int numero;
    private int capacidad;
    private EstadoMesa estado;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Mesa(int numero, int capacidad){
        setCapacidad(capacidad);
        setNumero(numero);
        setEstado(EstadoMesa.LIBRE);
    }
    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
        //-toString
        @Override
        public String toString(){
            return "Mesa {numero: " + getNumero() + 
                    " capacidad: " + getCapacidad() + 
                    " estado: " + getEstado() + "}";
        }
        //equals
        //hashCode
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public void reservar(){
        if (getEstado() != EstadoMesa.LIBRE) {
            throw new IllegalArgumentException(
                "No se puede reservar una mesa que no esta libre; mesa " + getNumero() + " está " + getEstado());
        }
        setEstado(EstadoMesa.RESERVADA);    
    }
    public void ocupar(){
        if (getEstado() == EstadoMesa.OCUPADA) {
            throw new IllegalArgumentException(
                "No se puede ocupar una mesa que ya está ocupada; mesa " + getNumero() + " está " + getEstado());
        }
        if (getEstado() == EstadoMesa.LIBRE) {
            throw new IllegalArgumentException(
                "No se puede ocupar una mesa que no está reservada; mesa " + getNumero() + " está " + getEstado());
        }
        setEstado(EstadoMesa.OCUPADA);    
    }
    public void liberar(){
        if (getEstado() != EstadoMesa.OCUPADA) {
            throw new IllegalArgumentException(
                "No se puede liberar una mesa que no está ocupada; mesa " + getNumero() + " está " + getEstado());
        }
        setEstado(EstadoMesa.LIBRE); 
    }
    public void anularReservacion(){
        if (getEstado() != EstadoMesa.RESERVADA) {
            throw new IllegalArgumentException(
                "No se puede anular la reservación de una mesa que no está reservada; mesa " + getNumero() + " está " + getEstado());
        }
        setEstado(EstadoMesa.LIBRE); 
    }
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public int getNumero(){
        return this.numero;
    }
    public int getCapacidad(){
        return this.capacidad;
    }
    public EstadoMesa getEstado(){
        return this.estado;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setNumero(int nuevoNumero){
        if(nuevoNumero <= 0){
            throw new IllegalArgumentException("Numero invalido de mesa");
        }
        this.numero = nuevoNumero;
    }
    private void setEstado(EstadoMesa nuevoEstado){
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("Estado nulo");
        }

        this.estado = nuevoEstado;
    }
    private void setCapacidad(int nuevaCapacidad){
        if(nuevaCapacidad <= 0){
            throw new IllegalArgumentException("Capacidad invalida para una mesa");
        }
        this.capacidad = nuevaCapacidad;
    }
}