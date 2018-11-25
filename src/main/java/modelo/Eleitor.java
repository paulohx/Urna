package modelo;

import util.PPMImage;
import excecoes.CpfException;

public class Eleitor extends Pessoa{
        
    private int secao;
    private String numeroTitulo;
    private boolean votou;
    private PPMImage imagem;
    private Estado estado;
    
    /**
     * Construtor da classe com parâmetros, como o eleitor ainda não votou seta como false.
     * @param nome O nome do eleitor.
     * @param cpf O cpf do eleitor.
     * @param secao A secao do eleitor.
     * @param numeroTitulo O número do título do eleitor.
     * @param imagem A imagem do rosto do eleitor.
     * @param estado O estado do eleitor.
     * @throws CpfException - Caso o cpf não seja válido.
     */
    public Eleitor(String nome, String cpf, int secao, String numeroTitulo, PPMImage imagem, Estado estado) throws CpfException {
        super(nome, cpf);
        this.secao        = secao;
        this.numeroTitulo = numeroTitulo;
        this.votou        = false;
        this.imagem       = imagem;
        this.estado       = estado;
    }
    
    /**
     * Pega a seção do eleitor.
     * @return int - A seção.
     */
    public int getSecao() {
        return this.secao;
    }
    
    /**
     * Seta a seção do eleitor.
     * @param secao A seção a ser setada.
     */
    public void setSecao(int secao) {
        this.secao = secao;
    }
    
    /**
     * Pega o número do título do eleitor
     * @return String - O número do título.
     */
    public String getNumeroTitulo() {
        return this.numeroTitulo;
    }
    
    /**
     * Seta o número do título do eleitor.
     * @param numeroTitulo O número do título a ser setado.
     */
    public void setNumeroTitulo(String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }
    
    /**
     * Pega a verificação se o eleitor já votou.
     * @return boolean - Votou ou não votou.
     */
    public boolean getVotou() {
        return this.votou;
    }

    /**
     * Seta o voto do eleitor.
     * @param votou O voto a ser setado.
     */
    public void setVotou(boolean votou) {
        this.votou = votou;
    }
    
    /**
     * Pega a imagem do eleitor.
     * @return PPMImage - O objeto imagem.
     */
    public PPMImage getImagem() {
        return imagem;
    }

    /**
     * Seta a imagem do eleitor.
     * @param imagem A imagem a ser setada.
     */
    public void setImagem(PPMImage imagem) {
        this.imagem = imagem;
    }
    
    /**
     * Pega o estado do eleitor.
     * @return Estado - O estado.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Seta o estado do eleitor.
     * @param estado O estado a ser setado.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {
             return super.toString()                         + "\n" +
                     "Numero:  " + this.numeroTitulo         + "\n" +
                     "Secao:   " + this.secao                + "\n" +
                     "Votou:   " + this.votou                + "\n" +
                     "Estado:  " + this.estado.getNome()     + "\n";
    }}