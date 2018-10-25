package modelo;

import util.PPMImage;
import util.Verifica;

public class CadEleitor {    
        
    private String nome;
    private String cpf;
    private int secao;
    private String numeroTitulo;
    private boolean votou;
    private PPMImage imagem = new PPMImage();
    
    /**
     * Construtor da classe, como o eleitor ainda não votou seta como false.
     */
    public CadEleitor(){
        this.votou = false;
    }
    
    /**
     * Pega o nome do eleitor.
     * @return String - O nome.
     */
    public String getNome(){
        return(this.nome);
    }

    /**
     * Seta o nome do eleitor.
     * @param nome O nome a ser setado.
     */
    public void setNome(String nome){
        this.nome = nome;
    }
    
    /**
     * Pega o cpf do eleitor.
     * @return String - O cpf.
     */
    public String getCpf(){
        return(this.cpf);
    }
    
    /**
     * Seta o cpf do eleitor.
     * @param cpf O cpf a ser setado.
     * @return boolean - Retorna true se o cpf for válido, caso contrário retorna false.
     */
    public boolean setCpf(String cpf){
        
        /*Verifica se o cpf esta valido*/
        if (new Verifica().Cpf(cpf)){
              this.cpf = cpf;
              return true;
        }else{return false;}
    }
    
    /**
     * Pega a seção do eleitor.
     * @return int - A seção.
     */
    public int getSecao(){
        return(this.secao);
    }
    
    /**
     * Seta a seção do eleitor.
     * @param secao A seção a ser setada.
     */
    public void setSecao(int secao){
        this.secao = secao;
    }
    
    /**
     * Pega o número do título do eleitor
     * @return String - O número do título.
     */
    public String getNumeroTitulo(){
        return(this.numeroTitulo);
    }
    
    /**
     * Seta o número do título do eleitor.
     * @param numeroTitulo O número do título a ser setado.
     */
    public void setNumeroTitulo(String numeroTitulo){
        this.numeroTitulo = numeroTitulo;
    }
    
    /**
     * Pega a verificação se o eleitor já votou.
     * @return boolean - Votou ou não votou.
     */
    public boolean getVotou() {
        return(this.votou);
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
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString(){
             return ("Nome:    " + this.nome                 + "\n" +
                     "Numero:  " + this.numeroTitulo         + "\n" +
                     "Cpf:     " + this.cpf                  + "\n" +
                     "Secao:   " + this.secao                + "\n" +
                     "Votou:   " + this.votou +                "\n");
    }
}