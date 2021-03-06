package visao;

import conexao.Conexao;
import dao.EleitorDAO;
import java.awt.Cursor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import modelo.Eleitor;
import dao.UrnaDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.PPMFileReader;
import util.PPMImage;
import util.PGMImage;

public class Login extends javax.swing.JFrame {
    
    EleitorDAO eleitorDAO = new EleitorDAO();
    UrnaDAO    urna       = new UrnaDAO();
            
    /**
     * Construtor da classe sem parâmetro.
     * @throws IOException 
     */
    public Login() throws IOException {
        
        initComponents();
        
        this.setTitle("Tela de login");
        this.getContentPane().setBackground(new java.awt.Color(122,130,190));
        this.setLocationRelativeTo(null);
        this.setExtendedState(HIDE_ON_CLOSE);
        
        /*Verifica se a pasta local esta criada*/
        File dir = new File("ArquivosJson");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();

        /*Antes de fazer algo usando a conexao verifica primeiro se tem internet*/
        if (!Conexao.getInternet()){
            JOptionPane.showMessageDialog(this, "Sem acesso a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        /*Iniciando o servico do drive*/
        try {
            Conexao.service();
        } catch (GeneralSecurityException ex) {
            JOptionPane.showMessageDialog(this, "Houve algum erro de segurança ao tentar conectar no drive.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Houve algum erro de entrada e saída.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Credenciais não encontradas.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        Login.setEnabled(false);
        
        try {
            eleitorDAO.baixarEleitorJson();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Houve um erro ao baixar os votos do drive.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    List<Eleitor> eleitores = eleitorDAO.getVetorEleitor();
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        btnLocalizarImagem = new javax.swing.JButton();
        texImagemEleitor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFotoLogin = new javax.swing.JLabel();
        Login = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(1, 1, 1));
        setUndecorated(true);

        panel1.setBackground(new java.awt.Color(122, 130, 190));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("IMAGEM:");

        btnLocalizarImagem.setText("...");
        btnLocalizarImagem.setToolTipText("Clique para selecionar uma imagem");
        btnLocalizarImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLocalizarImagemMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLocalizarImagemMouseEntered(evt);
            }
        });
        btnLocalizarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarImagemActionPerformed(evt);
            }
        });

        texImagemEleitor.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("    ELEIÇÕES 2018");

        jPanel1.setBackground(new java.awt.Color(122, 130, 190));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(122, 130, 190));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFotoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFotoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 234, Short.MAX_VALUE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(btnLocalizarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(texImagemEleitor)))))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLocalizarImagem)
                        .addComponent(texImagemEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Login.setText("LOGIN");
        Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginMouseEntered(evt);
            }
        });
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        btnSair.setText("SAIR");
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        
        try {
            /*Muda o cursor*/
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            boolean imagemIgual = false;

            /*Verifica se a url da imagem esta vazia*/
            if(!texImagemEleitor.getText().equals("")){

                /*Transforma a imagem seleciona em objeto*/
                PPMImage PPM = PPMFileReader.readImage(texImagemEleitor.getText());

                /* Criando uma variavel PGM do tipo PGMImage*/
                PGMImage PGM;

                /* Atribuindo para a variavel PGM, a imagem convertida para PGM do eleitor*/
                PGM = PPM.convertToPGM();

                /* Desenhando a imagem na tela*/
                draw(PGM);

                for (Eleitor eleitor : eleitores) {

                    if(eleitor != null){

                        /* Comparando se a imagem selecionada e igual ao de algum eleitor*/                    
                        if(eleitor.getImagem().equals(PPM)){

                            /*Entrou no if quer dizer que a imagem e igual*/
                            imagemIgual = true;

                            /* Se este eleitor ja votou (caso votou nao deixa ele entrar)*/
                            if (eleitor.getVotou()){

                                JOptionPane.showMessageDialog(this, eleitor.getNome() + " você não pode votar denovo", "Erro", JOptionPane.ERROR_MESSAGE);
                                break;

                            }else{

                                /*Chegou aqui e porque a imagem esta certa E ele ainda nao votou*/                            
                                JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
                                
                                /* Instânciando a urna, passando os parametros e colocando ela visivel*/
                                new Urna(eleitor, urna, eleitorDAO).setVisible(true);

                                /* Fechando a tela de login*/
                                this.dispose();
                            }
                        }
                    }
                }

                if (!imagemIgual){

                    JOptionPane.showMessageDialog(this, "Não há nenhum eleitor cadastrado com essa imagem", "Erro", JOptionPane.ERROR_MESSAGE);

                    texImagemEleitor.setText("");
                }
            }
            
        } finally {
            /*volta o cursor para o normal*/
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_LoginActionPerformed

    /**
     * Utilizada para desenhar a imagem.
     * @param imagem A imagem a ser desenhada.
     */
    public void draw (PGMImage imagem){
        
        /*Funcao para gerar a imagem do eleitor assim que ele clicar em login*/
        MemoryImageSource source = new MemoryImageSource(imagem.getWidth(), imagem.getHeight(), ColorModel.getRGBdefault(), imagem.toRGBModel(), 0, imagem.getWidth());
        Image img = Toolkit.getDefaultToolkit().createImage(source);
        
        /*Colocando a imagem dentro de um label*/   
        lblFotoLogin.setIcon(new ImageIcon(img));        
    }
    private void btnLocalizarImagemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocalizarImagemMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnLocalizarImagemMouseExited

    private void btnLocalizarImagemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocalizarImagemMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnLocalizarImagemMouseEntered

    private void btnLocalizarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarImagemActionPerformed
        
        /* Criando um seletor de arquivos*/
        JFileChooser seleciona = new JFileChooser();
        
        /*Verifica se o usuario clicou no Abrir*/
        if (seleciona.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

            /* Colocando dentro da variavel arq do tipo FILE, a imagem selecionada*/
            if (seleciona.getSelectedFile().getAbsolutePath().matches(".+\\.ppm")){
             
                /*Pega o caminho do arquivo*/
                String caminho = seleciona.getSelectedFile().getAbsolutePath();
            
                /*Verifica se o caminho nao esta vindo vazio*/
                if (!caminho.equals("")){

                    /* Colocando no text o endereco da imagem selecionada*/
                    texImagemEleitor.setText(caminho);
                }

                /* Ativando o botao de login*/
                Login.setEnabled(true);
                
            }else{
                /*Selecionou um arquivo que nao tem extensao .ppm*/
                JOptionPane.showMessageDialog(this, "Você só pode selecionar arquivos .ppm", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnLocalizarImagemActionPerformed

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnSairMouseEntered

    private void LoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_LoginMouseEntered

    private void LoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_LoginMouseExited

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnSairMouseExited

    /**
     * Função que inicializa o programa.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String args[]) {
        
        /*Cria o form*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton btnLocalizarImagem;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFotoLogin;
    private java.awt.Panel panel1;
    private javax.swing.JTextField texImagemEleitor;
    // End of variables declaration//GEN-END:variables
}