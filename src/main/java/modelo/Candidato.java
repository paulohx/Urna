package modelo;

import excecoes.CpfException;

public class Candidato extends Pessoa {
    
    private int numero;
    private Partido partido;
    private int qtdeVoto;
    
    /**
     * Construtor da classe com parâmetros, seta a quantidade de votos do candidato para 0.
     * @param nome O nome do candidato.
     * @param cpf O cpf do candidato.
     * @param numero O número do candidato.
     * @param partido O partido do candidato.
     * @throws CpfException - Caso o cpf não seja válido.
     */
    public Candidato(String nome, String cpf, int numero, Partido partido) throws CpfException {
        super(nome, cpf);
        this.qtdeVoto = 0;
        this.numero   = numero;
        this.partido  = partido;
    }
    
    /**
     * Pega a quantidade de votos daquele candidato.
     * @return int - A quantidade de votos.
     */
    public int getQtdeVoto() {
        return qtdeVoto;
    }

    /**
     * Seta a quantidade de votos.
     * @param qtdeVoto Quantidade de votos a ser setada.
     */
    public void setQtdeVoto(int qtdeVoto) {
        this.qtdeVoto += qtdeVoto;
    }
    
    /**
     * Pega o número do candidato.
     * @return int - O número.
     */
    public int getNumero() {
        return this.numero;
    }
    
    /**
     * Seta o número do candidato.
     * @param numero O número a ser setado.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * Pega o partido do candidato.
     * @return Partido - O partido.
     */
    public Partido getPartido() {
        return this.partido ;
    }
    
    /**
     * Seta o partido do candidato.
     * @param partido O partido a ser setado.
     */
    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {        
        return super.toString() +
                "Numero:  " + this.numero               + "\n" +
                "Partido: \n"                           +
                "\tNome:   " + this.partido.getNome()   + "\n" +
                "\tNumero: " + this.partido.getNumero() + "\n";
    }
}