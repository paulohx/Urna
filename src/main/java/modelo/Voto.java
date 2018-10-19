package modelo;

import dao.Urna;
import java.util.Date;

public class Voto {

    private CadEleitor eleitor = new CadEleitor();
    private CadCandidato candidato = new CadCandidato();
    private int urna;
    private Date data;
    
    public Voto (Urna urna){
        this.urna = urna.getCodigo();
        this.data = new Date(); 
    }

    public CadEleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(CadEleitor eleitor) {
        this.eleitor = eleitor;
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