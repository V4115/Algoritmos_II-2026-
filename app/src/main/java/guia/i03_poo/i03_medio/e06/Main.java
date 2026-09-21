package guia.i03_poo.i03_medio.e06;
/**
 * 6. Herencia con super. Empleado(sueldoBase) con sueldo(); Gerente que agrega un bono
redefiniendo sueldo() y usando super; test.
 */

import java.math.BigDecimal;

public class Main{
    public static void main(String[] args) {
        Empleado empleado = new Empleado("Ana", new BigDecimal("500000"));
        Gerente gerente = new Gerente("Beto", new BigDecimal("500000"), new BigDecimal("200000"));

        System.out.println("Sueldo empleado: " + empleado.sueldo()); // 500000
        System.out.println("Sueldo gerente:  " + gerente.sueldo());  // 700000

        Empleado[] plantel = {empleado, gerente};
        BigDecimal total = BigDecimal.ZERO;
        for (Empleado e : plantel) {
            total = total.add(e.sueldo());
        }
        System.out.println("Total a pagar: " + total); // 1200000
    }
}