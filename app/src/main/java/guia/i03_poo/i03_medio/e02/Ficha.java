package guia.i03_poo.i03_medio.e02;

/**
 * Las dos fichas posibles del ta-te-ti. Un enum (y no un char o un boolean)
 * hace imposible representar una ficha inválida.
 */

public enum Ficha{
    X,
    O;

    public Ficha alternar() {
        if (this == X) {
            return O;
        } else {
            return X;
        }
    }
}