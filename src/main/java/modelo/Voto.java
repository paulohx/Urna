package modelo;

import dao.UrnaDAO;
import java.util.Date;

public class Voto {

    private String cpfEleitor;
    private CadCandidato candidato = new CadCandidato();
    private int urna;
    private Date data;
    
    public Voto (UrnaDAO urna){
        this.urna = urna.getCodigo();
        this.data = new Date(); 
    }

    public String getCpfEleitor() {
        return cpfEleitor;
    }

    public void setCpfEleitor(String cpfEleitor) {
        this.cpfEleitor = cpfEleitor;
    }

    public CadCandidato getCandidato() {
        return candidato;
    }

    public void setCandidato(CadCandidato candidato) {
        this.candidato = candidato;
    }

    public Date getData() {
        return data;
    }
}