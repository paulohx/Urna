package conexao;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.FileList;

import java.security.GeneralSecurityException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Conexao {
    
    private static final String APPLICATION_NAME      = "Google Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY     = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    /*Variavel global utilizada afim de pegar o servico*/
    private static Drive service = null;

    /*Instancia global da variavel SCOPES, utilizada dessa forma retorna todas as permissoes possiveis para manipular o drive*/
    private static final List<String> SCOPES = new ArrayList<>(DriveScopes.all());
    
    /*Variavel global com o intuito de salvar o caminho de onde estao as credencias do drive*/
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Construtor da classe - onde se inicia a variável do serviço.
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws NullPointerException 
     */
    private Conexao() throws IOException, GeneralSecurityException, NullPointerException {
            
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    /**
     * Verifica se o pc tem internet, verifica a conexão com o servidor do Google que é raro ficar fora do ar.
     * @return boolean - Retorna verdadeiro se conseguiu conexão com a internet, caso contrário retorna falso.
     */
    public static boolean getInternet() {
        
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException ex) {
            return false;
        }   
    }
    
    /**
     * Cria um objeto de credencial autorizado.
     * @param HTTP_TRANSPORT HTTP transporte da rede.
     * @return Credential - Um objeto de credencial autorizado.
     * @throws IOException se o arquivo de credentials.json não for encontrado.
     */
    private static Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException {

        /*Carrega os "secredos" do cliente*/
        InputStream in = Conexao.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        /*Constroi o fluxo e uma trigger do pedido do usuario*/
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
    /**
     * Caso o serviço ainda não esteja instânciado então instância.
     * @return Drive - O serviço.
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws NullPointerException 
     */
    public static Drive service() throws IOException, GeneralSecurityException, NullPointerException {
        
        /*Se o servico estiver nulo, cria um novo servico*/
        if (service == null) {
            Conexao conexao = new Conexao();
        }
        return service;
    }
    
    /**
     * Verifica se existe determinada pasta no Google Drive.
     * @param nome Nome da pasta.
     * @return String - Retorna o id dessa pasta ou se ela não existir retorna "".
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public static String existePasta(String nome)  throws IOException, GeneralSecurityException {
        
        String pageToken = null;
        do {
          FileList result = service.files().list()
              .setQ("mimeType='application/vnd.google-apps.folder'")
              .setSpaces("drive")
              .setFields("nextPageToken, files(id, name)")
              .setPageToken(pageToken)
              .execute();
          
          /*Laco rodando todas as pastas que estao no drive*/
          for (File file : result.getFiles()) {
              
              /*Verifica se o nome da pasta que esta no drive e igual ao procurado*/
              if (file.getName().equals(nome)){
                  
                  /*Se for igual entao retorna o id da pasta*/
                  return file.getId();
              }
          }
          pageToken = result.getNextPageToken();
        } while (pageToken != null);
        
        return "";
    }

    /**
     * Verifica se existe esse arquivo no Google Drive. 
     * @param nome Nome do arquivo.
     * @return String - Retorna o id desse arquivo ou se ele não existir retorn "".
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public static String existeArquivo(String nome)  throws IOException, GeneralSecurityException {
        
        String pageToken = null;
        do {
          FileList result = service.files().list()
              .setQ("mimeType='arquivosjson/json'")
              .setSpaces("drive")
              .setFields("nextPageToken, files(id, name)")
              .setPageToken(pageToken)
              .execute();
          
          /*Laco rodando todos os arquivos que estao no drive*/
          for (File file : result.getFiles()) {
              
              /*Verifica se o nome do arquivo que esta no drive e igual ao procurado*/
              if (file.getName().equals(nome)){
                  
                  /*Se for igual entao retorna o id do arquivo*/
                  return file.getId();
              }
          }
          pageToken = result.getNextPageToken();
        } while (pageToken != null);
        
        return "";
    }
    
    /**
     * Cria uma pasta no Google Drive.
     * @param service Serviço instânciado.
     * @param name Nome da pasta.
     * @return String - Retorna o id da pasta criada.
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public static String criaPasta(Drive service, String name) throws IOException, GeneralSecurityException{
        
        File fileMetadata = new File();
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        File file = service.files().create(fileMetadata).setFields("id").execute();
        
        return file.getId();        
    }
    
    /**
     * Remove um arquivo do Google Drive.
     * @param idArquivo Id do arquivo que você quer remover.
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public static void removeArquivo(String idArquivo) throws IOException, GeneralSecurityException{        
        service.files().delete(idArquivo).execute();
    }
    
    /**
     * Envia um arquivo para o Google Drive.
     * @param idPasta Id da pasta pra onde quer enviar o arquivo.
     * @param url Caminho especificando onde está o arquivo que quer enviar para o Google Drive.
     * @return String - Retorna o id do arquivo que foi enviado.
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public static String enviaArquivo(String idPasta, String url) throws IOException, GeneralSecurityException{

        File fileMetadata = new File();
        fileMetadata.setName(url);
        fileMetadata.setParents(Collections.singletonList(idPasta));
        java.io.File filePath    = new java.io.File("ArquivosJson/" + url);
        FileContent mediaContent = new FileContent("ArquivosJson/json", filePath);
        File file = service.files().create(fileMetadata, mediaContent).setFields("id, parents").execute();
        
        return file.getId();
    }
    
    /**
     * Mostra o conteúdo de um arquivo do Google Drive.
     * @param idArquivo Id do arquivo que queria imprimir.
     * @return String - Retorna o conteúdo do arquivo em forma de String.
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static String printFile(String idArquivo) throws IOException, GeneralSecurityException{

          File file = service.files().get(idArquivo).execute();
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          service.files().get(idArquivo).executeMediaAndDownloadTo(outputStream);
          
          return outputStream.toString();
    }
}