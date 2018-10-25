package dao;

import com.google.gson.Gson;
import java.io.IOException;
import modelo.Voto;
import conexao.Conexao;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.data.general.DefaultPieDataset;

public class VotoDAO {
    
    /*Vetor de votos*/
    private Voto votos [] = new Voto[50];
    
    /**
     * Insere o voto na primeira posição vazia que achar do vetor.
     * @param votos É passado um obejto inteiro de voto para a inserção.
     * @return boolean - Se ocorreu tudo certo na inserção então retorna true, caso contrário retorna false.
     */
    public boolean inserir(Voto votos) {

        for (int i = 0; i < this.votos.length; i++) {			
            if (this.votos[i] == null) {
                this.votos[i] = votos;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de votos.
     * @return Voto - Retorna o vetor de votos.
     */
    public Voto [] getVetorVoto(){
        return this.votos;
    }
    
    /**
     * Utilizada para baixar o Voto.json do Google Drive.
     * @throws IOException 
     */
    public void baixarVotoJson() throws IOException{
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;        
        try {
            
            /*Verifica se a pasta existe*/
            String idPas = Conexao.existePasta("ArquivosJson"); 
            if (!(idPas.equals(""))){
                
                /*Verifica se o arquivo existe*/
                String idArq = Conexao.existeArquivo("Voto.json");
                if (!(idArq.equals(""))){
                    
                    /*Se existir o arquivo coloca nessa variavel o conteudo dele*/
                    aux = Conexao.printFile(idArq);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível baixar os dados dos votos, verifique sua conexão com a internet..", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){
            
            /*Cria um vetor dinamico de votos*/
            List <Voto> voto = new ArrayList<>();

            /*Transforma cada linha do json em objeto do tipo voto e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                voto.add(gson.fromJson(linha, Voto.class)); 
            }

            /*Joga no vetor estatico cada posicao do vetor dinamico*/
            for (int i = 0; i < voto.size(); i++) {
                if(this.votos[i] == null){
                    this.votos[i] = voto.get(i);
                }
            }
        }
    }
    
    /**
     * Insere no arquivo Voto.json um objeto do tipo voto.
     * @param voto Insere um objeto inteiro do tipo voto no arquivo json.
     * @return boolean - Retorna true caso conseguiu realizar a inserção e false caso ocorreu algo de errado.
     */
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
    
    /**
     * Envia o Voto.json local para o Google Drive.
     * @return boolean - Retorna true caso conseguiu realizar o envio e false caso ocorreu algo de errado.
     */
    public boolean enviaDrive(){
        
        try {
            
            /*Verifica se existe essa pasta no Google Drive*/
            String idPas = Conexao.existePasta("ArquivosJson");            
            if (idPas.equals("")){
                
                /*Se a pasta nao existir entao cria*/
                idPas = Conexao.criaPasta(Conexao.service(), "ArquivosJson");    
            }
            
            /*Verifica se existe esse arquivo no Google Drive*/
            String idArq = Conexao.existeArquivo("Voto.json");
            if (idArq.equals("")){
                
                /*Se o arquivo nao existir entao cria*/
                idArq = Conexao.enviaArquivo(idPas, "Voto.json");
            }
            
            /*Remove o arquivo que esta no drive para nao criar varios dele mesmo*/
            Conexao.removeArquivo(idArq);
            
            /*Por fim, envia o json local para la*/
            Conexao.enviaArquivo(idPas, "Voto.json");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para salvar o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Utilizada para popularizar o gráfico do resultado da eleição.
     * @return DefaultPieDataset - O objeto do tipo DefaultPieDataset devidamente preenchido com os dados do candidato.
     */
    public DefaultPieDataset preencheGrafico(){
        
        DefaultPieDataset dataSet = new DefaultPieDataset();
        
        /*Populando o grafico com o nome dos candidatos, partido e a quantida de votos*/
        for (Voto v: votos){
            
            /*Evita o null pointer exception*/
            if (v != null){
                
                /*Verificacao utilizada pois caso o eleitor vote NULO ou BRANCO nao da erro quando chegar aqui*/
                if (v.getCandidato() != null){
                    dataSet.setValue(v.getCandidato().getNome() + " - " + v.getCandidato().getPartido().getSigla(), v.getCandidato().getQtdeVoto());
                }                
            }            
        }
        
        return dataSet;
    }
    
    /**
     * Verifica se houve algum voto computado no sistema.
     * @return boolean - Retorna true caso achou algum voto, caso contrário retorna false
     */
    public boolean verificaAlguemVotou(){
        
        for (Voto v: votos){
            
            /*Evita o null pointer exception*/
            if (v != null){

                /*Verificacao utilizada pois caso o eleitor vote NULO ou BRANCO nao da erro quando chegar aqui*/
                if (v.getCandidato() != null){
                    
                    /*Se pelo menos um eleitor ja votou naquele candidato entao retorna*/
                    if (v.getCandidato().getQtdeVoto() > 0){
                        return true;
                    }
                }
            }            
        }
        
        /*Nenhum eleitor votou*/
        return false;
    }
}