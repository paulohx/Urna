package dao;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import conexao.Conexao;
import dao.PartidoDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import modelo.CadCandidato;

public class CandidatoDAO {

    private CadCandidato candidatos[] = new CadCandidato[50];
	
    /*Insere o eleitor na primeira posicao vazia que achar do vetor*/
    public boolean inserir(CadCandidato candidatos) {

        for (int i = 0; i < this.candidatos.length; i++) {			
            if (this.candidatos[i] == null) {
                this.candidatos[i] = candidatos;
                return true;
            }
        }
        return false;
    }
    
    public String igualdadeCandidato(CadCandidato c){
        
        for (int i = 0; i < candidatos.length; i++) {
            
            /*Trata o null pointer exception*/
            if (candidatos[i] != null){
                
                /*Verifica se o cpf e igual*/
                if (candidatos[i].getCpf().equals(c.getCpf())){
                    return "CPF";
                }
                
                /*Verifica se o partido e igual*/
                if ((candidatos[i].getPartido().getNome().equals(c.getPartido().getNome())) ||
                    (candidatos[i].getPartido().getNumero() == c.getPartido().getNumero())  ||
                    (candidatos[i].getPartido().getSigla().equals(c.getPartido().getSigla()))){
                    return "PARTIDO";
                }
            }
        }
        
        return "";
    }
    
    
    public void baixarCandidatoJson() throws IOException{
        
        Gson gson = new Gson();
        String aux = null;
            try {
            String idPas = Conexao.existePasta("ArquivosJson"); 
            if (idPas.equals("")){
                System.exit(0);    
            }
            
            String idArq = Conexao.existeArquivo("Candidato.json");    
            aux = Conexao.printFile(idArq);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para ler o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                
            }
        
        List <CadCandidato> candidato = new ArrayList();
        
        BufferedReader verifica = new BufferedReader(new StringReader(aux));
        String linha;
        
        while((linha = verifica.readLine()) != null){
            candidato.add(gson.fromJson(linha, CadCandidato.class)); 
        }
        
        for (int i = 0; i < candidato.size(); i++) {
            if(candidatos[i] == null){
                candidatos[i] = candidato.get(i);
            }
        }
    }
    
    public boolean inserirJson(CadCandidato candidato){
        
        Gson gson = new Gson();
        
        FileWriter arq = null;
        try {
            arq = new FileWriter("./ArquivosJson/Candidato.json", true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar o candidato no arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(candidato));
        
        try {
            arq.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao fechar o arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public boolean enviaDrive(){
        
        try {
            
            String idPas = Conexao.existePasta("ArquivosJson");            
            if (idPas.equals("")){
                idPas = Conexao.criaPasta(Conexao.service(), "ArquivosJson");    
            }
            
            String idArq = Conexao.existeArquivo("Candidato.json");          

            if (idArq.equals("")){                
                idArq = Conexao.enviaArquivo(idPas, "Candidato.json");
            }
            
            Conexao.removeArquivo(idArq);
            Conexao.enviaArquivo(idPas, "Candidato.json");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para salvar o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}