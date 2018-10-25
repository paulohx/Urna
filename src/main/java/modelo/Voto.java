package modelo;

import dao.UrnaDAO;
import java.util.Date;

public class Voto {

    private String cpfEleitor;
    private CadCandidato candidato = new CadCandidato();
    private int urna;
    private Date data;
    
    /**
     * Construtor da classe.
     * @param urna A urna em que aquele voto está sendo realizado.
     */
    public Voto (UrnaDAO urna){
        this.urna = urna.getCodigo();
        this.data = new Date(); /*Pega a data no momento que foi efetuado o voto*/
    }

    /**
     * Pega o cpf do eleitor.
     * @return String - O cpf.
     */
    public String getCpfEleitor() {
        return cpfEleitor;
    }

    /**
     * Seta o cpf do eleitor.
     * @param cpfEleitor O cpf a ser setado.
     */
    public void setCpfEleitor(String cpfEleitor) {
        this.cpfEleitor = cpfEleitor;
    }

    /**
     * Pega o candidato em que o eleitor votou.
     * @return CadCandidato - O objeto inteiro.
     */
    public CadCandidato getCandidato() {
        return candidato;
    }

    /**
     * Seta o candidato em que o eleitor votou.
     * @param candidato O candidato a ser setado.
     */
    public void setCandidato(CadCandidato candidato) {
        this.candidato = candidato;
    }

    /**
     * Pega a data da realização do voto.
     * @return Date - A data.
     */
    public Date getData() {
        return data;
    }
    
    /**
     * Pega o código da urna.
     * @return int - O código.
     */
    public int getUrna(){
        return this.urna;
    }
}