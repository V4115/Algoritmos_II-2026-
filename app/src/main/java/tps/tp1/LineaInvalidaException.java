package tps.tp1;

/**
 *
 * @author jose
 */

public class LineaInvalidaException extends RuntimeException {

    private final int numeroDeLinea;
    private final String motivo;

    public LineaInvalidaException(int numeroDeLinea, String motivo) {
        this.numeroDeLinea = numeroDeLinea;
        this.motivo = motivo;
    }
    public int numeroDeLinea() {
        return numeroDeLinea;
    }
    @Override
    public String getMessage(){
        return motivo;
    }
}
