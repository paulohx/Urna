package dao;

import com.google.gson.Gson;
import java.io.IOException;
import modelo.Voto;
import conexao.Conexao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class VotoDAO {
    
    /*Vetor de votos*/
    private List<Voto> votos;

    /**
     * Construtor sem parâmetro - Instancia o vetor.
     */
    public VotoDAO() {
        votos = new ArrayList();
    }
    
    /**
     * Insere o voto no vetor.
     * @param voto É passado um objeto inteiro de voto para a inserção.
     */
    public void inserir(Voto voto) {
        this.votos.add(voto);
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de votos.
     * @return List (Voto) - Retorna o vetor de votos.
     */
    public List<Voto> getVetorVoto(){
        return this.votos;
    }
    
    /**
     * Utilizada para baixar o Voto.json do Google Drive.
     * @throws IOException 
     */
    public void baixarVotoJson() throws IOException, GeneralSecurityException{
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;
            
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
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){

            /*Transforma cada linha do json em objeto do tipo voto e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                votos.add(gson.fromJson(linha, Voto.class)); 
            }
        }
    }
    
    /**
     * Insere no arquivo Voto.json um objeto do tipo voto.
     * @param voto Insere um objeto inteiro do tipo voto no arquivo json.
     * @throws Exception 
     */
    public void inserirJson(Voto voto) throws Exception {
        
        /*Verifica se a pasta local esta criada*/
        File dir = new File("ArquivosJson");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();
        
        Gson gson = new Gson();
        
        FileWriter arq = new FileWriter("./ArquivosJson/Voto.json", true);
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(voto));
        
        arq.close();
    }
    
    /**
     * Envia o Voto.json local para o Google Drive.
     * @throws Exception 
     */
    public void enviaDrive() throws Exception {
            
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
    }
}