package dao;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import conexao.Conexao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import modelo.CadCandidato;

public class CandidatoDAO {

    /*Vetor de candidatos*/
    private CadCandidato candidatos[] = new CadCandidato[50];
	
    /**
     * Insere o candidato na primeira posição vazia que achar do vetor.
     * @param candidatos É passado um obejto inteiro de candidato para a inserção.
     * @return boolean - Se ocorreu tudo certo na inserção então retorna true, caso contrário retorna false.
     */
    public boolean inserir(CadCandidato candidatos) {

        for (int i = 0; i < this.candidatos.length; i++) {			
            if (this.candidatos[i] == null) {
                this.candidatos[i] = candidatos;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Função utilizada com o intúido de retornar o vetor inteiro de candidatos.
     * @return CadCandidato[] - Retorna o vetor de candidatos.
     */
    public CadCandidato[] getVetorCandidato(){
        return this.candidatos;
    }
    
    /**
     * Verifica se existe no vetor um candidato idêntico ao passado por parâmetro.
     * @param c O Objeto inteiro do candidato é passado para verificar no vetor se tem algum igual.
     * @return String - Retorna o campo em que há a igualdade e caso não haver, retorna "".
     */
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
    
    /**
     * Utilizada para baixar o Candidato.json do Google Drive.
     * @throws IOException 
     */
    public void baixarCandidatoJson() throws IOException{
        
        Gson gson = new Gson();
        
        /*Auxiliar para pegar o conteudo do arquivo*/
        String aux = null;
        try {
            
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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível baixar os dados dos candidatos, verifique sua conexão com a internet..", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        /*Caso esta variavel esteja nula e porque nao ha o arquivo para baixar ou ele esta vazio*/
        if (aux != null){
        
            /*Cria um vetor dinamico de candidatos*/
            List <CadCandidato> candidato = new ArrayList<>();

            /*Transforma cada linha do json em objeto do tipo candidato e adiciona no vetor dinamico*/
            BufferedReader verifica = new BufferedReader(new StringReader(aux));        
            String linha;        
            while((linha = verifica.readLine()) != null){
                candidato.add(gson.fromJson(linha, CadCandidato.class)); 
            }

            /*Joga no vetor estatico cada posicao do vetor dinamico*/
            for (int i = 0; i < candidato.size(); i++) {
                if(this.candidatos[i] == null){
                    this.candidatos[i] = candidato.get(i);
                }
            }
        }
    }
    
    /**
     * Insere no arquivo Candidato.json um objeto do tipo candidato.
     * @param candidato Insere um objeto inteiro do tipo candidato no arquivo json.
     * @return boolean - Retorna true caso conseguiu realizar a inserção e false caso ocorreu algo de errado.
     */
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
    
    /**
     * Envia o Candidato.json local para o Google Drive.
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
            String idArq = Conexao.existeArquivo("Candidato.json");
            if (idArq.equals("")){                
                
                /*Se o arquivo nao existir entao cria*/
                idArq = Conexao.enviaArquivo(idPas, "Candidato.json");
            }
            
            /*Remove o arquivo que esta no drive para nao criar varios dele mesmo*/
            Conexao.removeArquivo(idArq);
            
            /*Por fim, envia o json local para la*/
            Conexao.enviaArquivo(idPas, "Candidato.json");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve erro ao conectar com o drive para salvar o arquivo..", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Verifica se existe algum candidato no vetor com aquele número.
     * @param numero Número do candidato.
     * @return CadCandidato - Retorna o objeto inteiro do candidato, caso contrário retorna null.
     */
    public CadCandidato getCandidatoByNum(int numero){
        
        for (CadCandidato c: this.candidatos){
            
            /*Evita o null pointer exception*/
            if (c != null){
                
                /*Se o numero do candidato e igual ao do passado por parametro*/
                if (c.getNumero() == numero){
                    return c;
                }
            }            
        }

        return null;
    }
}