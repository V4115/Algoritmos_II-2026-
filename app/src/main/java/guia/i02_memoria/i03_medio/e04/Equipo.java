package guia.i02_memoria.i03_medio.e04;

/**
 * 4. Copia defensiva. Clase Equipo que guarde un int[] copiándolo en el constructor y
devolviéndolo copiado en el getter; test que mutar el arreglo externo no la afecta.
 */

public class Equipo {
    private final int[] players;

    public Equipo(int[] players) {
        // Defensive copy of the input array to prevent external modification
        this.players = players.clone();
    }

    public int[] getplayers() {
        // Return a copy of the internal array to prevent external modification
        return players.clone();
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "jugadores=" + java.util.Arrays.toString(players) +
                '}';
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        Equipo team = new Equipo(original);
        int[] copy = team.getplayers();

        System.out.println("Antes de mutar el arreglo externo: " + team);
        
        // Mutate the external array
        original[0] = 99;
        //Mutate the copy of the internal array
        copy[1] = 88;

        System.out.println("Después de mutar el arreglo externo y la copia: " + team);
    }
}