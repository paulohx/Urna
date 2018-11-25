package excecoes;

/**
 * Exceção realizada quando algum partido não existe.
 */
public class PartidoException extends Exception {
    
    /**
     * Construtor sem parâmetros da classe.
     */
    public PartidoException() {
        super ();
    }
    
    /**
     * Construtor com parâmetros da classe.
     * @param s A mensagem de erro.
     */
    public PartidoException(String s) {
        super(s);
    }
}