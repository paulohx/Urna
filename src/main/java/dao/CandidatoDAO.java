package dao;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import conexao.Conexao;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import modelo.Candidato;
import excecoes.IgualdadeDeObjetosException;
import java.io.File;
import java.util.List;
import modelo.DeputadoFederal;
import modelo.Presidente;

public class CandidatoDAO {

    /*Vetor de candidatos*/
    private List<Candidato> candidatos;

    /**
     * Construtor sem parâmetro - Instancia o vetor.
     */
    public CandidatoDAO() {
        candidatos = new ArrayList();
    }
	
    /**
     * Insere o candidato no vetor.
     * @param candidato É passado um objeto inteiro de candidato para a inserção.
     */
    public void inserir(Candidato candidato) {
        this.candidatos.add(candidato);
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de candidatos.
     * @return List (Candidato) - Retorna o vetor de candidatos.
     */
    public List<Candidato> getVetorCandidato(){
        return this.candidatos;
    }

    /**
     * Verifica se existe no vetor um candidato idêntico ao passado por parâmetro.
     * @param c O Objeto inteiro do candidato é passado para verificar no vetor se tem algum igual.
     * @throws IgualdadeDeObjetosException - Caso há um cadastro com o mesmo item de determinado campo.
     */
    public void igualdadeCandidato(Candidato c) throws IgualdadeDeObjetosException {
        
        String campo = "";
        
        for (Candidato candidato : this.candidatos) {
            
            /*Trata o null pointer exception*/
            if (candidato != null) {
                
                /*Verifica se o partido e igual, SE SOMENTE SE O candidato do VETOR E o passado por PARAMETRO forem presidentes*/
                if ((candidato instanceof Presidente) && (c instanceof Presidente)) {
                    
                    if ((candidato.getPartido().getNome().equals(c.getPartido().getNome())) ||
                        (candidato.getNumero() == c.getNumero())  ||
                        (candidato.getPartido().getSigla().equals(c.getPartido().getSigla()))) {
                        campo =  "PARTIDO";
                    }
                }
                
                /*Não pode haver deputado com o mesmo numero*/
                if (c instanceof DeputadoFederal) {
                    
                    if (candidato.getNumero() == c.getNumero()) {
                        campo = "NUMERO";
                    }
                }
                
                /*Verifica se o cpf e igual*/
                if (candidato.getCpf().equals(c.getCpf())){
                    campo = "CPF";
                }
            }
        }
        
        if (!campo.equals("")){
            throw new IgualdadeDeObjetosException("Há um cadastro com o mesmo item do campo " + campo + "...");            
        }
    }
    
    /**
     * Utilizada para baixar o Candidato.json do Google Drive.
     * @throws Exception 
     */
    public void baixarCandidatoJson() throws Exception {
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;
            
        /*Verifica se a pasta existe*/
        String idPas = Conexao.existePasta("ArquivosJson"); 
        if (!(idPas.equals(""))){

            /*Verifica se o arquivo existe*/
            String idArq = Conexao.existeArquivo("Candidato.json");            
            if (!(idArq.equals(""))){

                /*Se existir o arquivo coloca nessa variavel o conteudo dele*/
                aux = Conexao.printFile(idArq);
            }                
        }
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){       

            /*Exclui o .json que estava local*/
            File arq = new File("./ArquivosJson/Candidato.json");
            arq.delete();
            
            /*Transforma cada linha do json em objeto do tipo candidato e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                candidatos.add(gson.fromJson(linha, DeputadoFederal.class));
                inserirJson(gson.fromJson(linha, DeputadoFederal.class));
            }
        }
    }
    
    /**
     * Insere no arquivo Candidato.json um objeto do tipo candidato.
     * @param candidato Insere um objeto inteiro do tipo candidato no arquivo json.
     * @throws Exception
     */
    public void inserirJson(Candidato candidato) throws Exception {
        
        /*Verifica se a pasta local esta criada*/
        File dir = new File("ArquivosJson");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();
        
        Gson gson = new Gson();

        FileWriter arq = new FileWriter("./ArquivosJson/Candidato.json", true);
        
        PrintWriter escreveArq = new PrintWriter(arq);
        escreveArq.printf("%s\n", gson.toJson(candidato));
        
        arq.close();
    }
    
    /**
     * Envia o Candidato.json local para o Google Drive.
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
        String idArq = Conexao.existeArquivo("Candidato.json");
        if (idArq.equals("")){                

            /*Se o arquivo nao existir entao cria*/
            idArq = Conexao.enviaArquivo(idPas, "Candidato.json");
        }

        /*Remove o arquivo que esta no drive para nao criar varios dele mesmo*/
        Conexao.removeArquivo(idArq);

        /*Por fim, envia o json local para la*/
        Conexao.enviaArquivo(idPas, "Candidato.json");
    }
    
    /**
     * Verifica se existe algum candidato no vetor com aquele número.
     * @param numero Número do candidato.
     * @return Candidato - Retorna o objeto inteiro do candidato, caso contrário retorna null.
     */
    public DeputadoFederal getPresidenteByNum(int numero){
        
        for (Candidato c: this.candidatos){
            
            /*Evita o null pointer exception*/
            if (c != null){
               
                /*Se for presidente o estado vai estar nulo*/
                if (((DeputadoFederal)c).getEstado() == null) {
                    
                    /*Se o numero do candidato e igual ao do passado por parametro*/
                    if (c.getNumero() == numero){
                        return ((DeputadoFederal)c);
                    }
                }
            }            
        }

        return null;
    }
    
    /**
     * Verifica se existe algum candidato no vetor com aquele número.
     * @param numero Número do candidato.
     * @return Candidato - Retorna o objeto inteiro do candidato, caso contrário retorna null.
     */
    public DeputadoFederal getDeputadoFederalByNum(int numero){
        
        for (Candidato c: this.candidatos){
            
            /*Evita o null pointer exception*/
            if (c != null){
               
                /*Se for deputado federal o estado NAO vai estar nulo*/
                if (((DeputadoFederal)c).getEstado() != null) {
                    
                    /*Se o numero do candidato e igual ao do passado por parametro*/
                    if (c.getNumero() == numero){
                        return ((DeputadoFederal)c);
                    }
                }
            }            
        }

        return null;
    }
}