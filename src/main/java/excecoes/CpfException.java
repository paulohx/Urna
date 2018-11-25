package excecoes;

/**
 * Exceção realizada quando algum cpf não foi preenchido corretamente.
 */
public class CpfException extends Exception {
    
    /**
     * Construtor sem parâmetros da classe.
     */
    public CpfException() {
        super ();
    }
    
    /**
     * Construtor com parâmetros da classe.
     * @param s A mensagem de erro.
     */
    public CpfException(String s) {
        super(s);
    }
}