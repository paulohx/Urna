package modelo;

public class CadPartido {

    private String nome;
    private int numero;
    private String sigla;
    
    public String getNome(){
        return(this.nome);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getNumero(){
        return(this.numero);
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getSigla(){
        return(this.sigla);
    }
    
    public void setSigla(String sigla){
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return ("Nome: "   + this.nome   + "\n" +
                "Sigla: "  + this.sigla  + "\n" +
                "Numero: " + this.numero + "\n");
    }
}