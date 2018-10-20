package dao;

import com.google.gson.Gson;
import conexao.Conexao;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
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
    
    public Voto[] getVetorVoto(){
        return this.votos;
    }
    
    public boolean enviaDrive(){
        
        try {
            
            String idPas = Conexao.existePasta("ArquivosJson");            
            if (idPas.equals("")){
                idPas = Conexao.criaPasta(Conexao.service(), "ArquivosJson");    
            }
            
            String idArq = Conexao.existeArquivo("Voto.json");          

            if (idArq.equals("")){                
                idArq = Conexao.enviaArquivo(idPas, "Voto.json");
            }
            
            Conexao.removeArquivo(idArq);
            Conexao.enviaArquivo(idPas, "Voto.json");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para salvar o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
        
    public boolean inserirJson(Voto voto){
        
        Gson gson = new Gson();
        
        FileWriter arq = null;
        try {
            arq = new FileWriter("./ArquivosJson/Voto.json", true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar o voto no arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(voto));
        
        try {
            arq.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao fechar o arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}