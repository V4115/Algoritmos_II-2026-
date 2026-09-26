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

public class Main{
        public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(new Mesa[]{
                new Mesa(1, 2), new Mesa(2, 4), new Mesa(3, 6)
        });

        int numeroReservado = restaurant.reservar(3);
        System.out.println("reservar(3) asignó la mesa: " + numeroReservado + " (la de 4, no la de 6)");
        restaurant.ocupar(numeroReservado);
        System.out.println(restaurant);

        restaurant.liberar(numeroReservado, new BigDecimal("45000.0"));
        System.out.println("Después de liberar con gasto 45000:");
        System.out.println(restaurant);
        System.out.println("mesasLibres(): " + restaurant.mesasLibres());
    }
}