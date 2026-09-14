package guia.i03_poo.i03_medio.e02;

public enum EstadoDeTateti{
    JUGANDO,
    EMPATE,
    GANO_X,
    GANO_O;

    public boolean esFinalizado(){
        return this != JUGANDO;
    }

    public boolean esGanadorX(){
        return this == GANO_X;
    }

    public boolean esGanadorO(){
        return this == GANO_O;
    }
}