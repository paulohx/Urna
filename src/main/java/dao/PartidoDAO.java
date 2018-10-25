package dao;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import modelo.CadPartido;
import conexao.Conexao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {
    
    /*Vetor de partidos*/
    private CadPartido partidos[] = new CadPartido[50];
	
    /**
     * Insere o partido na primeira posição vazia que achar do vetor.
     * @param partidos É passado um obejto inteiro de aprtido para a inserção.
     * @return boolean - Se ocorreu tudo certo na inserção então retorna true, caso contrário retorna false.
     */
    public boolean inserir(CadPartido partidos) {

        for (int i = 0; i < this.partidos.length; i++) {			
            if (this.partidos[i] == null) {
                this.partidos[i] = partidos;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de partidos.
     * @return CadPartido[] - Retorna o vetor de partidos.
     */
    public CadPartido[] getVetorPartido(){
        return this.partidos;
    }
    
    /**
     * Verifica se existe no vetor um partido idêntico ao passado por parâmetro.
     * @param p O Objeto inteiro do partido é passado para verificar no vetor se tem algum igual.
     * @return String - Retorna o campo em que há a igualdade e caso não haver, retorna "".
     */
    public String igualdadePartido(CadPartido p){
        
        for (int i = 0; i < partidos.length; i++) {
            
            /*Trata o null pointer exception*/
            if (partidos[i] != null){
                
                /*Verifica se o nome e igual*/
                if (partidos[i].getNome().equals(p.getNome())){
                    return "NOME";
                }
                
                /*Verifica se a sigla e igual*/
                if (partidos[i].getSigla().equals(p.getSigla())){
                    return "SIGLA";
                }
                
                /*Verifica se o numero e igual*/
                if (partidos[i].getNumero() == p.getNumero()){
                    return "NUMERO";
                }
            }
        }
        
        return "";
    }
    
    /**
     * Utilizada para baixar o Partido.json do Google Drive.
     * @throws IOException 
     */
    public void baixarPartidoJson() throws IOException{
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;        
        try {
            
            /*Verifica se a pasta existe*/
            String idPas = Conexao.existePasta("ArquivosJson"); 
            if (!(idPas.equals(""))){
                
                /*Verifica se o arquivo existe*/
                String idArq = Conexao.existeArquivo("Partido.json");            
                if (!(idArq.equals(""))){
                    
                    /*Se existir o arquivo coloca nessa variavel o conteudo dele*/
                    aux = Conexao.printFile(idArq);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível baixar os dados dos partidos, verifique sua conexão com a internet..", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){
        
            /*Cria um vetor dinamico de partidos*/
            List <CadPartido> partido = new ArrayList<>();

            /*Transforma cada linha do json em objeto do tipo partido e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                partido.add(gson.fromJson(linha, CadPartido.class)); 
            }

            /*Joga no vetor estatico cada posicao do vetor dinamico*/
            for (int i = 0; i < partido.size(); i++) {
                if(this.partidos[i] == null){
                    this.partidos[i] = partido.get(i);
                }
            }
        }
    }
    
    /**
     * Insere no arquivo Partido.json um objeto do tipo partido.
     * @param partido Insere um objeto inteiro do tipo partido no arquivo json.
     * @return boolean - Retorna true caso conseguiu realizar a inserção e false caso ocorreu algo de errado.
     */
    public boolean inserirJson(CadPartido partido){
        
        Gson gson = new Gson();
        
        FileWriter arq = null;
        try {
            arq = new FileWriter("./ArquivosJson/Partido.json", true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar o partido no arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(partido));
        
        try {
            arq.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve algum erro ao fechar o arquivo json", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Envia o Partido.json local para o Google Drive.
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
            String idArq = Conexao.existeArquivo("Partido.json");
            if (idArq.equals("")){
                
                /*Se o arquivo nao existir entao cria*/
                idArq = Conexao.enviaArquivo(idPas, "Partido.json");
            }
            
            /*Remove o arquivo que esta no drive para nao criar varios dele mesmo*/
            Conexao.removeArquivo(idArq);
            
            /*Por fim, envia o json local para la*/
            Conexao.enviaArquivo(idPas, "Partido.json");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para salvar o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Verifica se existe algum partido cadastrado.
     * @return boolean - Retorna true caso achou algum partido inserido no vetor.
     */    
    public boolean existeAlgumPartido(){
        
        for (int i = 0; i < partidos.length; i++) {
            
            /*Trata o null pointer exception*/
            if (partidos[i] != null){
                return true;
            }            
        }
        
        return false;
    }
    
    /**
     * Verifica se existe um partido com aquela sigla no vetor.
     * @param sigla Sigla que irá ser pesquisada no vetor.
     * @return CadPartido - Se achou um partido com aquela sigla retorna o objeto inteiro do partido, caso contrário retorna null.
     */
    public CadPartido getPartidoBySigla(String sigla){
        
        for (CadPartido p: this.partidos){
            
            /*Evita o null pointer exception*/
            if (p != null){
                
                /*Se achou um partido com aquela sigla entao retorn o objeto*/
                if (p.getSigla().toUpperCase().equals(sigla.toUpperCase())){
                    return p;
                }
            }            
        }

        return null;
    }
}