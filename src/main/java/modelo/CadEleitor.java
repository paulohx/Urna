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
    
    public CadEleitor(){
        this.votou = false;
    }
    
    public String getNome(){
        return(this.nome);
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCpf(){
        return(this.cpf);
    }
    
    public boolean setCpf(String cpf){
        
        /*Verifica se o cpf esta valido*/
        if (new Verifica().Cpf(cpf)){
              this.cpf = cpf;
              return true;
        }else{return false;}
    }
    
    public int getSecao(){
        return(this.secao);
    }
    
    public void setSecao(int secao){
        this.secao = secao;
    }
    
    public String getNumeroTitulo(){
        return(this.numeroTitulo);
    }
    
    public void setNumeroTitulo(String numeroTitulo){
        this.numeroTitulo = numeroTitulo;
    }
    
    public boolean getVotou() {
        return(this.votou);
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }
    
    public PPMImage getImagem() {
        return imagem;
    }

    public void setImagem(PPMImage imagem) {
        this.imagem = imagem;
    }
    
    public String toString(){
             return ("Nome:    " + this.nome                 + "\n" +
                     "Numero:  " + this.numeroTitulo         + "\n" +
                     "Cpf:     " + this.cpf                  + "\n" +
                     "Secao:   " + this.secao                + "\n" +
                     "Votou:   " + this.votou +                "\n");
    }
}