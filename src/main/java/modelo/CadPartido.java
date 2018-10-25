package modelo;

public class CadPartido {

    private String nome;
    private int numero;
    private String sigla;
    
    /**
     * Pega o nome do partido.
     * @return String - O nome.
     */
    public String getNome(){
        return(this.nome);
    }
    
    /**
     * Seta o nome do partido.
     * @param nome O nome a ser setado.
     */
    public void setNome(String nome){
        this.nome = nome;
    }
    
    /**
     * Pega o número do partido.
     * @return int - O número.
     */
    public int getNumero(){
        return(this.numero);
    }
    
    /**
     * Seta o número do partido.
     * @param numero O número a ser setado.
     */
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    /**
     * Pega a sigla do partido.
     * @return String - A sigla.
     */
    public String getSigla(){
        return(this.sigla);
    }
    
    /**
     * Seta a sigla do partido.
     * @param sigla A sigla a ser setada.
     */
    public void setSigla(String sigla){
        this.sigla = sigla;
    }

    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {
        return ("Nome: "   + this.nome   + "\n" +
                "Sigla: "  + this.sigla  + "\n" +
                "Numero: " + this.numero + "\n");
    }
}