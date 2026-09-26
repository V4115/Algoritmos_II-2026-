package guia.i03_poo.i04_dificil.e03;
/**
 * 3. TDA Restaurant con mesas. Mesa (número, capacidad, estado ENUM LIBRE/RESERVADA/OCUPADA
— checklist 11: dibujá el diagrama de estados en el javadoc) + Restaurant: reservar(personas)
asigna la mesa LIBRE más chica en la que entren (ésa es la problemática — pensala bien),
ocupar(numero), liberar(numero, gasto), recaudacion() calculada y mesasLibres(). Test de
todas las transiciones de estado inválidas (ocupar sin reserva, liberar una libre, reservar sin
lugar).
 */

public enum EstadoMesa{
    LIBRE,
    RESERVADA,
    OCUPADA,
}