package guia.i03_poo.i04_dificil.e02;
/**
 * 2. TDA Tren con vagones. Vagon (capacidad en kilos, carga actual; su cargar valida y ES SU
lógica — checklist 6: el tren delega) + Tren: engancharVagon, desengancharUltimo, cargar(kilos)
que reparte al primer vagón con lugar (y lanza si no entra en ninguno), cargaTotal() calculada,
vagonMasCargado() (con null). Test del reparto y de los bordes.
 */

public class Main{
    public static void main(String[] args) {
        Tren tren = new Tren();
        tren.engancharVagon(new Vagon(1000));
        tren.engancharVagon(new Vagon(2000));

        tren.cargar(800);   // entra en el primero
        tren.cargar(500);   // el primero tiene 200 de lugar: va al segundo
        System.out.println(tren);
        System.out.println("cargaTotal(): " + tren.getCargaTotal() + " kg");
        System.out.println("vagonMasCargado(): " + tren.getVagonMasCargado());

        Vagon desenganchado = tren.desengancharUltimo();
        System.out.println("desengancharUltimo(): " + desenganchado);
        System.out.println(tren);
    }
}