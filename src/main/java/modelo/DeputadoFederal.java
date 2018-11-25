package modelo;

import excecoes.CpfException;

public class DeputadoFederal extends Candidato {

    private Estado estado;
    
    /**
     * Construtor da classe com parâmetro.
     * @param nome O nome do deputado.
     * @param cpf O cpf do deputado.
     * @param numero O número do deputado de quatro dígitos.
     * @param partido O partido do deputado.
     * @param estado O estado do deputado.
     * @throws CpfException - Caso o cpf não seja válido.
     */
    public DeputadoFederal(String nome, String cpf, int numero, Partido partido, Estado estado) throws CpfException {
        super(nome, cpf, numero, partido);
        this.estado = estado;
    }
    
    /**
     * Pega o estado do deputado federal.
     * @return Estado - O estado.
     */
    public Estado getEstado() {
        return this.estado;
    }
    
    /**
     * Seta o estado do deputado federal.
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
        return super.toString() +
                "Estado:   "    + this.estado.getNome();
    }
}