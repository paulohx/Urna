package modelo;

import excecoes.CpfException;

public class Presidente extends Candidato {
    
    /**
     * Construtor da classe com parâmetro.
     * @param nome O nome do presidente.
     * @param cpf O cpf do presidente.
     * @param numero O número do presidente de dois dígitos.
     * @param partido O partido do presidente.
     * @throws CpfException - Caso o cpf não seja válido.
     */
    public Presidente(String nome, String cpf, int numero, Partido partido) throws CpfException {
        super(nome, cpf, numero, partido);
    }
    
    /**
     * Utilizada pegar os dados do objeto.
     * @return String - Retorna os dados do objeto em forma de String.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}