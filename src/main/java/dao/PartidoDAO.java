package dao;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import modelo.Partido;
import conexao.Conexao;
import excecoes.IgualdadeDeObjetosException;
import excecoes.PartidoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {
    
    /*Vetor de partidos*/
    private List<Partido> partidos;

    /**
     * Construtor sem parâmetro - Instancia o vetor.
     */
    public PartidoDAO() {
        partidos = new ArrayList();
    }
    
    /**
     * Insere o partido no vetor.
     * @param partido É passado um objeto inteiro de partido para a inserção.
     */
    public void inserir(Partido partido) {
        this.partidos.add(partido);
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de partidos.
     * @return List (Partido) - Retorna o vetor de partidos.
     */
    public List<Partido> getVetorPartido(){
        return this.partidos;
    }
    
    /**
     * Verifica se existe no vetor um partido idêntico ao passado por parâmetro.
     * @param p O Objeto inteiro do partido é passado para verificar no vetor se tem algum igual.
     * @throws IgualdadeDeObjetosException - Caso há um cadastro com o mesmo item de determinado campo.
     */
    public void igualdadePartido(Partido p) throws IgualdadeDeObjetosException {
        
        String campo = "";
        
        for (Partido partido : this.partidos) {
            
            /*Trata o null pointer exception*/
            if (partido != null){
                
                /*Verifica se o numero e igual*/
                if (partido.getNumero() == p.getNumero()){
                    campo = "NUMERO";
                }
                
                /*Verifica se a sigla e igual*/
                if (partido.getSigla().equals(p.getSigla())){
                    campo = "SIGLA";
                }
                
                /*Verifica se o nome e igual*/
                if (partido.getNome().equals(p.getNome())){
                    campo = "NOME";
                }
            }
        }
        
        if (!campo.equals("")){
            throw new IgualdadeDeObjetosException("Há um cadastro com o mesmo item do campo " + campo + "...");            
        }
    }
    
    /**
     * Utilizada para baixar o Partido.json do Google Drive.
     * @throws IOException 
     */
    public void baixarPartidoJson() throws Exception {
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;
            
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
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){

            /*Exclui o .json que estava local*/
            File arq = new File("./ArquivosJson/Partido.json");
            arq.delete();
            
            /*Transforma cada linha do json em objeto do tipo partido e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                partidos.add(gson.fromJson(linha, Partido.class));
                inserirJson(gson.fromJson(linha, Partido.class));
            }
        }
    }
    
    /**
     * Insere no arquivo Partido.json um objeto do tipo partido.
     * @param partido Insere um objeto inteiro do tipo partido no arquivo json.
     * @throws Exception 
     */
    public void inserirJson(Partido partido) throws Exception {
        
        /*Verifica se a pasta local esta criada*/
        File dir = new File("ArquivosJson");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();
        
        Gson gson = new Gson();
        
        FileWriter arq = new FileWriter("./ArquivosJson/Partido.json", true);
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(partido));
        
        arq.close();
    }
    
    /**
     * Envia o Partido.json local para o Google Drive.
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
        String idArq = Conexao.existeArquivo("Partido.json");
        if (idArq.equals("")){

            /*Se o arquivo nao existir entao cria*/
            idArq = Conexao.enviaArquivo(idPas, "Partido.json");
        }

        /*Remove o arquivo que esta no drive para nao criar varios dele mesmo*/
        Conexao.removeArquivo(idArq);

        /*Por fim, envia o json local para la*/
        Conexao.enviaArquivo(idPas, "Partido.json");
    }
    
    /**
     * Verifica se existe algum partido cadastrado.
     * @throws PartidoException - Caso não exista nenhum partido.
     */
    public void existeAlgumPartido() throws PartidoException {
        
        for (Partido p : this.partidos) {
            
            /*Trata o null pointer exception*/
            if (p != null) {
                return ;
            }            
        }
        
        throw new PartidoException("Você não pode cadastrar um candidato sem antes ter cadastrado um partido");
    }
    
    /**
     * Verifica se existe um partido com aquela sigla no vetor.
     * @param sigla Sigla que irá ser pesquisada no vetor.
     * @return Partido - Se achou um partido com aquela sigla retorna o objeto inteiro do partido, caso contrário retorna null.
     */
    public Partido getPartidoBySigla(String sigla){
        
        for (Partido p: this.partidos) {
            
            /*Evita o null pointer exception*/
            if (p != null) {
                
                /*Se achou um partido com aquela sigla entao retorn o objeto*/
                if (p.getSigla().toUpperCase().equals(sigla.toUpperCase())) {
                    return p;
                }
            }            
        }

        return null;
    }
}