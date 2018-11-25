package modelo;

import excecoes.CpfException;
import util.Verifica;

public class Pessoa {

    private String nome;
    private String cpf;
    
    /**
     * Construtor com parâmetros da classe.
     * @param nome O nome da pessoa.
     * @param cpf O cpf da pessoa.
     * @throws CpfException - Caso o cpf não seja válido.
     */
    public Pessoa(String nome, String cpf) throws CpfException {
        
        this.nome = nome;
        
        if (setCpf(cpf)){
            this.cpf = cpf;
        }else{throw new CpfException("Cpf inválido...");}
    }
    
    /**
     * Pega o nome da pessoa.
     * @return String - O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Seta o nome da pessoa.
     * @param nome O nome a ser setado.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Pega o cpf da pessoa.
     * @return String - O cpf.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Seta o cpf da pessoa e verifica se ele é válido ou não.
     * @param cpf O cpf a ser setado.
     * @return boolean - Retorna verdadeiro se o cpf é válido, caso contrário retorna falso.
     */
    public boolean setCpf(String cpf) {
        
        /*Verifica se o cpf esta valido*/
        if (new Verifica().Cpf(cpf)){
              this.cpf = cpf;
              return true;
        }else{return false;}
    }
    
    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {
        return "Nome:    " + this.nome                 + "\n" +
               "Cpf:     " + this.cpf                  + "\n";
    }
}