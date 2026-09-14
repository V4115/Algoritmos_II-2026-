package guia.i03_poo.i03_medio.e02;

public class Main{

    public static void main(String[] args) {
        partidaQueGanaX();
        jugadasInvalidas();
        partidaEmpatada();
        partidaQueGanaOConTurnoAutomatico();
        partidaEnTableroDeCuatro();
    }

    // -------------------------------------------------------------------

    private static void partidaQueGanaX() {
        titulo("1. Gana X completando la primera fila");
        int filas = 3, columnas = 3;
        Tablero<Ficha> tablero = new TableroMatriz(filas, columnas);
        Tateti tateti = new Tateti(tablero);

        System.out.println("Tablero de " + tateti.getCantidadDeFilas() + "x" + tateti.getCantidadDeColumnas()
                + ". Empieza " + tateti.getProximaFicha() + ".");
        System.out.println("¿(1,1) está vacía? " + tateti.estaVacio(1, 1));
        System.out.println();

        // Con tres parámetros, el que llama elige la ficha, y el tateti
        // verifica que sea la del turno.
        jugar(tateti, 1, 1, Ficha.X);
        jugar(tateti, 2, 1, Ficha.O);
        jugar(tateti, 1, 2, Ficha.X);
        jugar(tateti, 2, 2, Ficha.O);
        jugar(tateti, 1, 3, Ficha.X);           // X X X en la fila 1

        System.out.println("¿Hay ganador? " + tateti.hayGanador()
                + " | ganador: " + tateti.obtenerGanador()
                + " | ¿empate? " + tateti.hayEmpate()
                + " | ¿terminó? " + tateti.estaFinalizado());
        System.out.println("Jugadas: " + tateti.getCantidadDeJugadas()
                + " | última ficha: " + tateti.getUltimaFicha()
                + " | próxima ficha: " + tateti.getProximaFicha() + "   <- null: ya no juega nadie");
        System.out.println("(1,1) tiene " + tateti.obtener(1, 1) + " y (3,3) tiene " + tateti.obtener(3, 3));
    }

    private static void jugadasInvalidas() {
        titulo("2. Jugadas inválidas: cada una explica por qué");

        int filas = 3;
        int columnas = 3;

        Tablero<Ficha> tablero = new TableroMatriz<>(filas, columnas);
        Tateti tateti = new Tateti(tablero);

        intentar("empezar con O",
                () -> tateti.colocar(1, 1, Ficha.O));

        tateti.colocar(1, 1, Ficha.X);

        intentar("jugar X dos veces seguidas",
                () -> tateti.colocar(1, 2, Ficha.X));

        intentar("pisar una posición ocupada",
                () -> tateti.colocar(1, 1, Ficha.O));

        intentar("salirse del tablero",
                () -> tateti.colocar(4, 1, Ficha.O));

        intentar("usar la fila 0",
                () -> tateti.colocar(0, 1, Ficha.O));

        intentar("una ficha nula",
                () -> tateti.colocar(2, 2, null));

        // Terminamos la partida para mostrar el último caso.
        tateti.colocar(2, 1, Ficha.O);
        tateti.colocar(1, 2, Ficha.X);
        tateti.colocar(2, 2, Ficha.O);
        tateti.colocar(1, 3, Ficha.X);

        intentar("seguir jugando después de terminar",
                () -> tateti.colocar(3, 3, Ficha.O));
    }

    private static void partidaEmpatada() {
        titulo("3. Empate: el tablero se llena sin línea");
        int filas = 3, columnas = 3;
        Tablero<Ficha> tablero = new TableroMatriz(filas, columnas);
        Tateti tateti = new Tateti(tablero);
        int[][] jugadas = {{1, 1}, {1, 2}, {1, 3}, {2, 2}, {2, 1}, {2, 3}, {3, 2}, {3, 1}, {3, 3}};
        for (int[] jugada : jugadas) {
            tateti.colocar(jugada[0], jugada[1]);      // turno automático
        }
        System.out.println(tateti);
        System.out.println("¿Empate? " + tateti.hayEmpate() + " | ganador: " + tateti.obtenerGanador()
                + " | estado: " + tateti.getEstado() + " | jugadas: " + tateti.getCantidadDeJugadas());
    }

    private static void partidaQueGanaOConTurnoAutomatico() {
        titulo("4. Gana O por la columna 2, con el turno automático");
        int filas = 3, columnas = 3;
        Tablero<Ficha> tablero = new TableroMatriz(filas, columnas);
        Tateti tateti = new Tateti(tablero);

        // Con dos parámetros, el tateti pone la ficha que corresponde.
        jugar(tateti, 1, 1);   // X
        jugar(tateti, 1, 2);   // O
        jugar(tateti, 2, 1);   // X
        jugar(tateti, 2, 2);   // O
        jugar(tateti, 3, 3);   // X
        jugar(tateti, 3, 2);   // O: columna 2 completa

        EstadoDeTateti estado = tateti.getEstado();
        System.out.println("Estado " + estado + ": ¿ganó X? " + estado.esGanadorX()
                + " | ¿ganó O? " + estado.esGanadorO() + " | ¿finalizado? " + estado.esFinalizado());
    }

    private static void partidaEnTableroDeCuatro() {
        titulo("5. Tablero 4x4: gana X por la diagonal secundaria");
        int filas = 4, columnas = 4;
        Tablero<Ficha> tablero = new TableroMatriz(filas, columnas);
        Tateti tateti = new Tateti(tablero);
        jugar(tateti, 1, 4);   // X
        jugar(tateti, 1, 1);   // O
        jugar(tateti, 2, 3);   // X
        jugar(tateti, 1, 2);   // O
        jugar(tateti, 3, 2);   // X
        jugar(tateti, 1, 3);   // O
        jugar(tateti, 4, 1);   // X: (1,4) (2,3) (3,2) (4,1)
        System.out.println("Fichas: X y O se alternan con Ficha.alternar(): "
                + Ficha.X + " -> " + Ficha.X.alternar() + " -> " + Ficha.X.alternar().alternar());
    }

    // -------------------------------------------------------------------
    //  Ayudas de presentación
    // -------------------------------------------------------------------

    private static void jugar(Tateti tateti, int fila, int columna, Ficha ficha) {
        EstadoDeTateti estado = tateti.colocar(fila, columna, ficha);
        System.out.println(ficha + " en (" + fila + "," + columna + ") -> " + estado);
        if (estado.esFinalizado()) {
            System.out.println(tateti);
        }
    }

    private static void jugar(Tateti tateti, int fila, int columna) {
        Ficha ficha = tateti.getProximaFicha();
        jugar(tateti, fila, columna, ficha);
    }

    /** Ejecuta una jugada que debería fallar y muestra el mensaje de la excepción. */
    private static void intentar(String descripcion, Runnable jugada) {
        try {
            jugada.run();
            System.out.println("   " + descripcion + " -> NO falló (¡esto sería un bug!)");
        } catch (IllegalArgumentException e) {
            System.out.println("   " + descripcion + " -> " + e.getMessage());
        }
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=".repeat(64));
        System.out.println("  " + texto);
        System.out.println("=".repeat(64));
    }

}