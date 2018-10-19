package dao;

import modelo.Voto;

public class VotoDAO {
    
    private Voto votos [] = new Voto[50];
    
    /*Insere o eleitor na primeira posicao vazia que achar do vetor*/
    public boolean inserir(Voto votos) {

        for (int i = 0; i < this.votos.length; i++) {			
            if (this.votos[i] == null) {
                this.votos[i] = votos;
                return true;
            }
        }
        return false;
    }
    
    public Voto [] getVoto(){
        return this.votos;
    }
}