package modelo;


public class CadCandidato {
    
    private String nome;
    private int numero;
    private String cpf;
    private CadPartido partido;
    private int qtdeVoto;

    public CadCandidato(){
        this.qtdeVoto = 0;
    }
    
    public int getQtdeVoto() {
        return qtdeVoto;
    }

    public void setQtdeVoto(int qtdeVoto) {
        this.qtdeVoto = qtdeVoto;
    }
    
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
    
    public String getCpf(){
        return(this.cpf);
    }

    public CadPartido getPartido(){
        return(this.partido);
    }
    
    public void setPartido(CadPartido partido){
        this.partido = partido;
    }

    @Override
    public String toString() {        
        return ("Nome:    " + this.nome                 + "\n" +
                "Numero:  " + this.numero               + "\n" +
                "Cpf:     " + this.cpf                  + "\n" +
                "Partido: \n"                           +
                "\tNome:   " + this.partido.getNome()   + "\n" +
                "\tNumero: " + this.partido.getNumero() + "\n");
    }
}