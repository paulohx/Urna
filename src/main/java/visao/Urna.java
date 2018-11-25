package visao;

import conexao.Conexao;
import dao.CandidatoDAO;
import dao.EleitorDAO;
import dao.PartidoDAO;
import dao.VotoDAO;
import dao.UrnaDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import modelo.Candidato;
import modelo.Eleitor;
import modelo.Voto;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;

public class Urna extends javax.swing.JFrame {
    
    CandidatoDAO candidatoDAO = new CandidatoDAO();
    PartidoDAO   partidoDAO   = new PartidoDAO();
    VotoDAO      votoDAO      = new VotoDAO();
    EleitorDAO   eleitorDAO   = new EleitorDAO();
    
    Eleitor eleitor = null;
    Voto    voto    = null;
    
    Candidato deputadoFederalVoto = null;
    Candidato presidenteVoto      = null;
    
    List<Candidato> candidatos = candidatoDAO.getVetorCandidato();
    List<Eleitor> eleitores;
    
    boolean prePrimeiroDigito;
    boolean depPrimeiroDigito;
    boolean depSegundoDigito;
    boolean depTerceiroDigito;
    boolean votoBranco = false;
    
    String  mensagem   = "";

    /**
     * Construtor da classe com parâmetro.
     * @param eleitor O eleitor que logou para fazer o voto.
     * @param urna A urna em que está ocorrendo o voto.
     * @param eleitorDAO O dao do eleitor.
     */
    public Urna(Eleitor eleitor, UrnaDAO urna, EleitorDAO eleitorDAO) {
        
        this.eleitor    = eleitor;
        this.voto       = new Voto(urna);
        this.eleitorDAO = eleitorDAO;
        this.eleitores  = this.eleitorDAO.getVetorEleitor();
        
        initComponents();
        
        /*Ainda nao foi inserido nenhum numero*/
        prePrimeiroDigito = false;
        depPrimeiroDigito = false;
        depSegundoDigito  = false;
        depTerceiroDigito = false;
        
        this.requestFocus();
        this.setTitle("Urna eletrônica");
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
        this.setLocationRelativeTo(null);
        this.setExtendedState(HIDE_ON_CLOSE);
        
        /*Colore os componentes da tela*/
        iniciaCores();
        
        /*Verifica se a pasta local esta criada*/
        File dir = new File("ArquivosJson");
        
        /*Caso nao estiver entao cria*/
        dir.mkdirs();

        /*Antes de fazer algo usando a conexao verifica primeiro se tem internet*/
        if (!Conexao.getInternet()){
            JOptionPane.showMessageDialog(this, "Sem acesso a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    
        /*Baixa os arquivos .json do drive e coloca na pasta ArquivosJson, sao usadas Threads para ficar mais rapido o processo*/
        Thread p = new Thread(){
            @Override
            public void run() {
                try {
                    partidoDAO.baixarPartidoJson();
                }catch(Exception e){
                    mensagem = "Houve um erro ao baixar os partidos do drive.";
                }
            }            
        };

        Thread c = new Thread(){
            @Override
            public void run() {
                try {
                    candidatoDAO.baixarCandidatoJson();
                }catch(Exception e){
                    mensagem = "Houve um erro ao baixar os candidatos do drive.";
                }
            }            
        };
            
        /*Inicia a execucao das threads*/
        p.start();
        c.start();
        
        /*Espera cada Thread ser finalizada para prosseguir*/
        try {            
            
            p.join();
            p.interrupt();
            c.join();
            c.interrupt();
            
            if (!mensagem.equals("")){
                JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Houve algum erro ao baixar os arquivos .json do drive.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        /*O panel do deputado ficara falso pois o eleitor ira votar primeiro no presidente*/
        pnlDeputadoFederal.setVisible(false);
    }

    /**
     * Função que seta cores para vários componentes.
     */
    private void iniciaCores(){
        
        this.panelIcone.setBackground(new java.awt.Color(245,245,245));
        this.setBackground(new java.awt.Color(0,0,0));
        
        btnConfirma.setBackground(new java.awt.Color(0,100,0));
        
        btnCorrige.setBackground(new java.awt.Color(255,140,0));
        btnCorrige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorrigeActionPerformed(evt);
            }
        });
        
        /* Colocando cores nos botões da Urna*/
        btnNum0.setForeground(Color.WHITE);
        btnNum0.setBackground(Color.BLACK);
        btnNum1.setForeground(Color.WHITE);
        btnNum1.setBackground(Color.BLACK);
        btnNum2.setForeground(Color.WHITE);
        btnNum2.setBackground(Color.BLACK);
        btnNum3.setForeground(Color.WHITE);
        btnNum3.setBackground(Color.BLACK);
        btnNum4.setForeground(Color.WHITE);
        btnNum4.setBackground(Color.BLACK);
        btnNum5.setForeground(Color.WHITE);
        btnNum5.setBackground(Color.BLACK);
        btnNum6.setForeground(Color.WHITE);
        btnNum6.setBackground(Color.BLACK);
        btnNum7.setForeground(Color.WHITE);
        btnNum7.setBackground(Color.BLACK);
        btnNum8.setForeground(Color.WHITE);
        btnNum8.setBackground(Color.BLACK);
        btnNum9.setForeground(Color.WHITE);
        btnNum9.setBackground(Color.BLACK);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        btnConfirma = new javax.swing.JButton();
        btnBranco = new javax.swing.JButton();
        btnCorrige = new javax.swing.JButton();
        btnNum2 = new javax.swing.JButton();
        btnNum4 = new javax.swing.JButton();
        btnNum7 = new javax.swing.JButton();
        btnNum5 = new javax.swing.JButton();
        btnNum6 = new javax.swing.JButton();
        btnNum8 = new javax.swing.JButton();
        btnNum9 = new javax.swing.JButton();
        btnNum1 = new javax.swing.JButton();
        btnNum3 = new javax.swing.JButton();
        btnNum0 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelIcone = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        pnlPresidente = new java.awt.Panel();
        panel2 = new java.awt.Panel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        texPrePrimeiroDigito = new java.awt.TextField();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        lblPreNome = new java.awt.Label();
        lblPrePartido = new java.awt.Label();
        texPreSegundoDigito = new java.awt.TextField();
        pnlDeputadoFederal = new java.awt.Panel();
        panel3 = new java.awt.Panel();
        label10 = new java.awt.Label();
        label11 = new java.awt.Label();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        label14 = new java.awt.Label();
        label16 = new java.awt.Label();
        label17 = new java.awt.Label();
        label18 = new java.awt.Label();
        lblDepNome = new java.awt.Label();
        lblDepPartido = new java.awt.Label();
        texDepPrimeiroDigito = new java.awt.TextField();
        texDepSegundoDigito = new java.awt.TextField();
        texDepTerceiroDigito = new java.awt.TextField();
        texDepQuartoDigito = new java.awt.TextField();
        label21 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(930, 570));
        getContentPane().setLayout(null);

        panel1.setBackground(new java.awt.Color(14, 7, 7));

        btnConfirma.setFont(new java.awt.Font("Chandas", 1, 10)); // NOI18N
        btnConfirma.setText("CONFIRMA");
        btnConfirma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmaMouseEntered(evt);
            }
        });
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaActionPerformed(evt);
            }
        });

        btnBranco.setFont(new java.awt.Font("Chandas", 1, 10)); // NOI18N
        btnBranco.setText("BRANCO");
        btnBranco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBrancoMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBrancoMouseEntered(evt);
            }
        });
        btnBranco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrancoActionPerformed(evt);
            }
        });

        btnCorrige.setFont(new java.awt.Font("Chandas", 1, 10)); // NOI18N
        btnCorrige.setText("CORRIGE");
        btnCorrige.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCorrigeMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCorrigeMouseEntered(evt);
            }
        });
        btnCorrige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorrigeActionPerformed(evt);
            }
        });

        btnNum2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum2.setText("2");
        btnNum2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum2MouseEntered(evt);
            }
        });
        btnNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum2ActionPerformed(evt);
            }
        });

        btnNum4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum4.setText("4");
        btnNum4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum4MouseEntered(evt);
            }
        });
        btnNum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum4ActionPerformed(evt);
            }
        });

        btnNum7.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum7.setText("7");
        btnNum7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum7MouseEntered(evt);
            }
        });
        btnNum7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum7ActionPerformed(evt);
            }
        });

        btnNum5.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum5.setText("5");
        btnNum5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum5MouseEntered(evt);
            }
        });
        btnNum5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum5ActionPerformed(evt);
            }
        });

        btnNum6.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum6.setText("6");
        btnNum6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum6MouseEntered(evt);
            }
        });
        btnNum6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum6ActionPerformed(evt);
            }
        });

        btnNum8.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum8.setText("8");
        btnNum8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum8MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum8MouseEntered(evt);
            }
        });
        btnNum8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum8ActionPerformed(evt);
            }
        });

        btnNum9.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum9.setText("9");
        btnNum9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum9MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum9MouseEntered(evt);
            }
        });
        btnNum9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum9ActionPerformed(evt);
            }
        });

        btnNum1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum1.setText("1");
        btnNum1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum1MouseEntered(evt);
            }
        });
        btnNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum1ActionPerformed(evt);
            }
        });

        btnNum3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum3.setText("3");
        btnNum3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum3MouseEntered(evt);
            }
        });
        btnNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum3ActionPerformed(evt);
            }
        });

        btnNum0.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnNum0.setText("0");
        btnNum0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNum0MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNum0MouseEntered(evt);
            }
        });
        btnNum0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBranco, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNum7, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(btnNum4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNum1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(btnCorrige, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNum0, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnNum9, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(btnNum6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNum3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(32, 32, 32))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNum0, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBranco, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorrige, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        getContentPane().add(panel1);
        panel1.setBounds(556, 73, 336, 431);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 26)); // NOI18N
        jLabel1.setText("JUSTIÇA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(785, 0, 107, 31);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 26)); // NOI18N
        jLabel3.setText("ELEITORAL");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(749, 32, 143, 31);

        panelIcone.setBackground(new java.awt.Color(254, 254, 254));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo-miniatura.png"))); // NOI18N

        javax.swing.GroupLayout panelIconeLayout = new javax.swing.GroupLayout(panelIcone);
        panelIcone.setLayout(panelIconeLayout);
        panelIconeLayout.setHorizontalGroup(
            panelIconeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIconeLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelIconeLayout.setVerticalGroup(
            panelIconeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIconeLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(panelIcone);
        panelIcone.setBounds(595, 10, 57, 53);

        pnlPresidente.setBackground(new java.awt.Color(122, 113, 113));
        pnlPresidente.setLayout(null);

        panel2.setBackground(new java.awt.Color(254, 254, 254));

        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setText("Aperte a tecla:");

        label2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label2.setText("VERDE para CONFIRMAR o voto");

        label3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label3.setText("LARANJA para CORRIGIR o voto");

        label4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label4.setText("BRANCO para ANULAR o voto");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlPresidente.add(panel2);
        panel2.setBounds(0, 410, 510, 100);

        texPrePrimeiroDigito.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        texPrePrimeiroDigito.setEditable(false);
        texPrePrimeiroDigito.setEnabled(false);
        texPrePrimeiroDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlPresidente.add(texPrePrimeiroDigito);
        texPrePrimeiroDigito.setBounds(147, 96, 90, 90);

        label5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label5.setText("SEU VOTO PARA");
        pnlPresidente.add(label5);
        label5.setBounds(18, 10, 181, 29);

        label6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        label6.setText("PRESIDENTE");
        pnlPresidente.add(label6);
        label6.setBounds(20, 49, 460, 37);

        label7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label7.setText("Número:");
        pnlPresidente.add(label7);
        label7.setBounds(18, 114, 100, 51);

        label8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label8.setText("Partido:");
        pnlPresidente.add(label8);
        label8.setBounds(18, 286, 100, 51);

        label9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label9.setText("Nome:");
        pnlPresidente.add(label9);
        label9.setBounds(18, 224, 100, 51);

        lblPreNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pnlPresidente.add(lblPreNome);
        lblPreNome.setBounds(128, 224, 382, 51);

        lblPrePartido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pnlPresidente.add(lblPrePartido);
        lblPrePartido.setBounds(128, 285, 382, 52);

        texPreSegundoDigito.setEditable(false);
        texPreSegundoDigito.setEnabled(false);
        texPreSegundoDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlPresidente.add(texPreSegundoDigito);
        texPreSegundoDigito.setBounds(247, 96, 90, 90);

        getContentPane().add(pnlPresidente);
        pnlPresidente.setBounds(30, 50, 510, 510);

        pnlDeputadoFederal.setBackground(new java.awt.Color(122, 113, 113));
        pnlDeputadoFederal.setLayout(null);

        panel3.setBackground(new java.awt.Color(254, 254, 254));

        label10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label10.setText("Aperte a tecla:");

        label11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label11.setText("VERDE para CONFIRMAR o voto");

        label12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label12.setText("LARANJA para CORRIGIR o voto");

        label13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label13.setText("BRANCO para ANULAR o voto");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(265, Short.MAX_VALUE))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDeputadoFederal.add(panel3);
        panel3.setBounds(0, 410, 520, 100);

        label14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label14.setText("SEU VOTO PARA");
        pnlDeputadoFederal.add(label14);
        label14.setBounds(18, 10, 181, 29);

        label16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label16.setText("Número:");
        pnlDeputadoFederal.add(label16);
        label16.setBounds(18, 114, 100, 51);

        label17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label17.setText("Partido:");
        pnlDeputadoFederal.add(label17);
        label17.setBounds(18, 286, 100, 51);

        label18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label18.setText("Nome:");
        pnlDeputadoFederal.add(label18);
        label18.setBounds(18, 224, 100, 51);

        lblDepNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pnlDeputadoFederal.add(lblDepNome);
        lblDepNome.setBounds(128, 224, 382, 51);

        lblDepPartido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pnlDeputadoFederal.add(lblDepPartido);
        lblDepPartido.setBounds(128, 285, 382, 52);

        texDepPrimeiroDigito.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        texDepPrimeiroDigito.setEditable(false);
        texDepPrimeiroDigito.setEnabled(false);
        texDepPrimeiroDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlDeputadoFederal.add(texDepPrimeiroDigito);
        texDepPrimeiroDigito.setBounds(120, 110, 70, 80);

        texDepSegundoDigito.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        texDepSegundoDigito.setEditable(false);
        texDepSegundoDigito.setEnabled(false);
        texDepSegundoDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlDeputadoFederal.add(texDepSegundoDigito);
        texDepSegundoDigito.setBounds(200, 110, 70, 80);

        texDepTerceiroDigito.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        texDepTerceiroDigito.setEditable(false);
        texDepTerceiroDigito.setEnabled(false);
        texDepTerceiroDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlDeputadoFederal.add(texDepTerceiroDigito);
        texDepTerceiroDigito.setBounds(280, 110, 70, 80);

        texDepQuartoDigito.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        texDepQuartoDigito.setEditable(false);
        texDepQuartoDigito.setEnabled(false);
        texDepQuartoDigito.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        pnlDeputadoFederal.add(texDepQuartoDigito);
        texDepQuartoDigito.setBounds(360, 110, 70, 80);

        label21.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        label21.setText("DEPUTADO FEDERAL");
        pnlDeputadoFederal.add(label21);
        label21.setBounds(70, 50, 430, 37);

        getContentPane().add(pnlDeputadoFederal);
        pnlDeputadoFederal.setBounds(20, 40, 530, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Função que pega o nome de um arquivo .wav e o reproduz.
     * @param soundName O caminho de onde esta o som.
     */
    public void playSound(String soundName){
        
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }catch(Exception ex){}
    }

    /**
     * Função que pega os 2 digitos que o eleitor escolheu e verifica se tem algum candidato com os mesmo.
     * @return Candidato - Se ouver ele pega o nome do candidato e o nome do partido e retorna o candidato.
     */
    private Candidato verificandoCandidato() {
        
        /*O eleitor esta votando no presidente no momento*/
        if (pnlPresidente.isVisible()){
        
            int aux1 = Character.getNumericValue(texPrePrimeiroDigito.getText().charAt(2)); 
            int aux2 = Character.getNumericValue(texPreSegundoDigito.getText().charAt(2)); 

            String aux;

            aux  = Integer.toString(aux1);
            aux += Integer.toString(aux2);

            presidenteVoto = candidatoDAO.getPresidenteByNum(Integer.parseInt(aux));
            if(presidenteVoto != null){
                lblPreNome.setText(presidenteVoto.getNome());
                lblPrePartido.setText(presidenteVoto.getPartido().getNome());
                return(presidenteVoto);
            }else{
                lblPreNome.setText("VOTO NULO");
                return(presidenteVoto);
            }
        
        /*O eleitor esta votando no deputado federal no momento*/
        }else {
    
            int aux1 = Character.getNumericValue(texDepPrimeiroDigito.getText().charAt(1));
            int aux2 = Character.getNumericValue(texDepSegundoDigito.getText().charAt(1));
            int aux3 = Character.getNumericValue(texDepTerceiroDigito.getText().charAt(1));
            int aux4 = Character.getNumericValue(texDepQuartoDigito.getText().charAt(1));

            String aux;

            aux  = Integer.toString(aux1);
            aux += Integer.toString(aux2);
            aux += Integer.toString(aux3);
            aux += Integer.toString(aux4);

            deputadoFederalVoto = candidatoDAO.getDeputadoFederalByNum(Integer.parseInt(aux));
            if(deputadoFederalVoto != null){
                lblDepNome.setText(deputadoFederalVoto.getNome());
                lblDepPartido.setText(deputadoFederalVoto.getPartido().getNome());
                return(deputadoFederalVoto);
            }else{
                lblDepNome.setText("VOTO NULO");
                return(deputadoFederalVoto);
            }
        }
    }

    /**
     * Ativar ou desativar os digitos da urna.
     * @param controle Passa true ou false para setar o enabled.
     */
    private void controleBotoes(boolean controle){

        btnNum0.setEnabled(controle);
        btnNum1.setEnabled(controle);
        btnNum2.setEnabled(controle);
        btnNum3.setEnabled(controle);
        btnNum4.setEnabled(controle);
        btnNum5.setEnabled(controle);
        btnNum6.setEnabled(controle);
        btnNum7.setEnabled(controle);
        btnNum8.setEnabled(controle);
        btnNum9.setEnabled(controle);
    }
    
    /**
     * Funçao que tira todos os botões, text e labels, ta tela da urna.
     */
    private void desabilitandoTelaUrna(){
        
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        label9.setVisible(false);
        
        /*O eleitor esta votando no presidente no momento*/
        if (pnlPresidente.isVisible()) {
            
            texPrePrimeiroDigito.setVisible(false);
            texPreSegundoDigito.setVisible(false);
            lblPreNome.setVisible(false);
            lblPrePartido.setVisible(false);
            
        /*O eleitor esta votando no deputado federal no momento*/
        }else {
            
            texDepPrimeiroDigito.setVisible(false);
            texDepSegundoDigito.setVisible(false);
            texDepTerceiroDigito.setVisible(false);
            texDepQuartoDigito.setVisible(false);
            lblDepNome.setVisible(false);
            lblDepPartido.setVisible(false);
        }            
    }
    
    /**
     * Função utilizada para sempre quando o usuário clicar em algum dos digitos, dar um bip e mostrar o valor.
     * @param valor O valor a ser mostrado.
     */
    private void clicandoNosNumeros(int valor) {
        
        /* Som do Digito da urna*/
        playSound("urnaDigito.wav");
        
        /*O eleitor esta votando no presidente no momento*/
        if (pnlPresidente.isVisible()){
            
            /* Verificando se o primeiro digito e falso*/
            if(!prePrimeiroDigito){
                /* Setando 1 para o text do digito 1*/
                texPrePrimeiroDigito.setText("  " + valor);
                /* Setando true para a variavel de controle*/
                prePrimeiroDigito = true;
            }else{/* Se não*/
                /* Setando 1 para o text do digito 2*/
                texPreSegundoDigito.setText("  " + valor);
                /* Chamando funcao que verifica se o candidato e valido e retorna ele como parametro*/
                presidenteVoto = verificandoCandidato();
            }
        
        /*O eleitor esta votando no deputado federal no momento*/
        }else {
                        
            if(!depPrimeiroDigito){
                texDepPrimeiroDigito.setText(" " + valor);
                depPrimeiroDigito = true;
            }else if (!depSegundoDigito){
                texDepSegundoDigito.setText(" " + valor);
                depSegundoDigito = true;                
            }else if (!depTerceiroDigito){
                texDepTerceiroDigito.setText(" " + valor);
                depTerceiroDigito = true;
            }else{
                texDepQuartoDigito.setText(" " + valor);
                controleBotoes(false);
                deputadoFederalVoto = verificandoCandidato();
            }
        }
    }
    
    private void btnNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum1ActionPerformed
        clicandoNosNumeros(1);
    }//GEN-LAST:event_btnNum1ActionPerformed

    private void btnCorrigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrigeActionPerformed
        
        /* Chamando funcao para setar true no enable dos botoes*/
        controleBotoes(true);
        
        /*O eleitor esta votando no presidente no momento*/
        if (pnlPresidente.isVisible()){
            
            /* Limpando os text e os labels para o eleitor poder corrigir seu voto*/
            texPrePrimeiroDigito.setText("");
            texPreSegundoDigito.setText("");
            lblPreNome.setText("");
            lblPrePartido.setText("");
            
            /* Setando falso para a variavel de controle primeiro digito */
            prePrimeiroDigito = false;
            
            presidenteVoto = null;
            
        /*O eleitor esta votando no deputado federal no momento*/
        }else {
            
            /* Limpando os text e os labels para o eleitor poder corrigir seu voto*/
            texDepPrimeiroDigito.setText("");
            texDepSegundoDigito.setText("");
            texDepTerceiroDigito.setText("");
            texDepQuartoDigito.setText("");
            lblDepNome.setText("");
            lblDepPartido.setText("");
            
            depPrimeiroDigito = false;
            depSegundoDigito  = false;
            depTerceiroDigito = false;
            
            deputadoFederalVoto = null;
        }
    }//GEN-LAST:event_btnCorrigeActionPerformed
    /* 
       Arrumando o cursor do mouse para sempre que ele passar em cima de um botao,
       ele mudar o cursor para uma maozinha e sempre que sair do botao voltar para o padrao.
    */
    private void btnConfirmaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmaMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnConfirmaMouseEntered

    private void btnCorrigeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCorrigeMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCorrigeMouseEntered

    private void btnBrancoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrancoMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnBrancoMouseEntered

    private void btnNum0MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum0MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum0MouseEntered

    private void btnNum9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum9MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum9MouseEntered

    private void btnNum8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum8MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum8MouseEntered

    private void btnNum7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum7MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum7MouseEntered

    private void btnNum6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum6MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum6MouseEntered

    private void btnNum5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum5MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum5MouseEntered

    private void btnNum4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum4MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum4MouseEntered

    private void btnNum3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum3MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum3MouseEntered

    private void btnNum2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum2MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum2MouseEntered

    private void btnNum1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum1MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNum1MouseEntered

    private void btnBrancoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrancoMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnBrancoMouseExited

    private void btnCorrigeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCorrigeMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCorrigeMouseExited

    private void btnConfirmaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmaMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnConfirmaMouseExited

    private void btnNum0MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum0MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum0MouseExited

    private void btnNum9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum9MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum9MouseExited

    private void btnNum8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum8MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum8MouseExited

    private void btnNum7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum7MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum7MouseExited

    private void btnNum1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum1MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum1MouseExited

    private void btnNum2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum2MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));                
    }//GEN-LAST:event_btnNum2MouseExited

    private void btnNum3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum3MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum3MouseExited

    private void btnNum4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum4MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum4MouseExited

    private void btnNum5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum5MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum5MouseExited

    private void btnNum6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNum6MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNum6MouseExited

    private void btnNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum2ActionPerformed
        clicandoNosNumeros(2);
    }//GEN-LAST:event_btnNum2ActionPerformed

    private void btnNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum3ActionPerformed
        clicandoNosNumeros(3);
    }//GEN-LAST:event_btnNum3ActionPerformed

    private void btnNum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum4ActionPerformed
        clicandoNosNumeros(4);
    }//GEN-LAST:event_btnNum4ActionPerformed

    private void btnNum5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum5ActionPerformed
        clicandoNosNumeros(5);
    }//GEN-LAST:event_btnNum5ActionPerformed

    private void btnNum6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum6ActionPerformed
        clicandoNosNumeros(6);
    }//GEN-LAST:event_btnNum6ActionPerformed

    private void btnNum7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum7ActionPerformed
        clicandoNosNumeros(7);
    }//GEN-LAST:event_btnNum7ActionPerformed

    private void btnNum8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum8ActionPerformed
        clicandoNosNumeros(8);
    }//GEN-LAST:event_btnNum8ActionPerformed

    private void btnNum9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum9ActionPerformed
        clicandoNosNumeros(9);
    }//GEN-LAST:event_btnNum9ActionPerformed

    private void btnNum0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum0ActionPerformed
        clicandoNosNumeros(0);
    }//GEN-LAST:event_btnNum0ActionPerformed

    private void btnBrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrancoActionPerformed
        
        /*O eleitor esta votando no presidente no momento*/
        if (pnlPresidente.isVisible()){
        
            lblPreNome.setText("VOTO EM BRANCO");
            lblPrePartido.setText("");
            
        /*O eleitor esta votando no deputado federal no momento*/
        }else {
            
            lblDepNome.setText("VOTO EM BRANCO");
            lblDepPartido.setText("");
        }
        
        votoBranco = true;
        btnConfirmaActionPerformed(evt);
    }//GEN-LAST:event_btnBrancoActionPerformed

    private void btnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaActionPerformed
        
        try {
            /*
              Voto nulo e voto em branco sao tratados do mesmo jeito neste software, ou seja
              jogando nulo no candidato.
            */

            /* Mudando o cursor para não clicar em nada na tela*/
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            /*O eleitor esta votando no presidente no momento*/
            if (pnlPresidente.isVisible()){
                
                /*Evita pressionar confirmar sem antes ter preenchido os campos e se nao estiver pressionado voto em branco*/
                if (texPreSegundoDigito.getText().equals("") && !votoBranco){
                    return ;
                }
                
                /*Se esse objeto estiver nulo e porque o usuario quer votar branco ou nulo*/
                if (presidenteVoto != null){

                    /* Percorrendo o vetor de candidatos*/
                    for (Candidato candidato : candidatos) {

                        /*Evita o erro de Null pointer exception*/
                        if(candidato != null){

                            /* Comparando se o numero digitado na urna e igual a de algum candidato*/                    
                            if(presidenteVoto.getNumero() == candidato.getNumero()){

                                /* Acrescentando um voto para o candidato escolhido*/
                                candidato.setQtdeVoto(1);

                                break;
                            }
                        }
                    }
                }
                
            /*O eleitor esta votando no deputado federal no momento*/
            }else {
                
                /*Evita pressionar confirmar sem antes ter preenchido os campos e se nao estiver pressionado voto em branco*/
                if (texDepQuartoDigito.getText().equals("") && !votoBranco){
                    return ;
                }
                
                /*Se esse objeto estiver nulo e porque o usuario quer votar branco ou nulo*/
                if (deputadoFederalVoto != null){

                    /* Percorrendo o vetor de candidatos*/
                    for (Candidato candidato : candidatos) {

                        /*Evita o erro de Null pointer exception*/
                        if(candidato != null){

                            /* Comparando se o numero digitado na urna e igual a de algum candidato*/                    
                            if(deputadoFederalVoto.getNumero() == candidato.getNumero()){

                                /* Acrescentando um voto para o candidato escolhido*/
                                candidato.setQtdeVoto(1);

                                break;
                            }
                        }
                    }
                }
            }

            /* Criando uma variavel do tipo FileWriter*/
            FileWriter arq = null;
            try {
                /* Criando um arquivo Json sem nada dentro*/
                arq = new FileWriter("./ArquivosJson/Candidato.json");

                /* Fechando o arquivo*/
                arq.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Houve um erro ao criar o .json do candidato", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            /* Laco do tamanho do vetor de candidatos*/            
            for (Candidato candidato : candidatos) {

                /*Evita o null pointer exception*/
                if(candidato != null){

                    /* Inserindo no arquivo Json, os dados atualizados dos candidatos*/
                    try {
                        candidatoDAO.inserirJson(candidato);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Houve algum erro ao salvar os candidatos no arquivo json.", "Erro", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }

            /* Som do Confirma da Urna*/
            playSound("urnaConfirma.wav");

            /*Se o panel do presidente estiver visivel ainda e porque ainda nao votou no deputado federal*/
            if (pnlPresidente.isVisible()) {
                
                pnlPresidente.setVisible(false);
                pnlDeputadoFederal.setVisible(true);
                
                votoBranco = false;
                
                return ;
            }
            
            /*Se chegou aqui e porque ja votou no deputado federal*/
            
            /* Colocando true para o eleitor que acabou de votar*/
            eleitor.setVotou(true);
            
            /* Criando uma variavel do tipo FileWriter*/
            try {

                /* Criando um arquivo Json sem nada dentro*/
                arq = new FileWriter("./ArquivosJson/Eleitor.json");

                /* Fechando o arquivo*/
                arq.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Houve um erro ao criar o .json do eleitor", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
            /* Laco do tamanho do vetor de eleitores*/
            for (Eleitor eleitore : eleitores) {

                /*Evita o null pointer exception*/
                if(eleitore != null){

                    /* Inserindo no arquivo Json, os dados atualizados do eleitor em relacao aos votos*/
                    try {
                        eleitorDAO.inserirJson(eleitore);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Houve algum erro ao salvar os eleitores no arquivo json.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                }
            }
            
            /* Desabilitando a tela da urna*/
            desabilitandoTelaUrna();

            /* Guardando o CPF do eleitor para contabilizar os votos*/
            voto.setCpfEleitor(eleitor.getCpf());

            /* Guardando o presidente que o eleitor votou (se votou nulo ou branco esse variavel estara nula)*/
            voto.setPresidente(presidenteVoto);
            
            /* Guardando o deputado federal que o eleitor votou (se votou nulo ou branco esse variavel estara nula)*/
            voto.setDeputadoFederal(deputadoFederalVoto);

            /* Colocando o voto no vetor de votos*/
            votoDAO.inserir(voto);
            
            /* Colocando o voto no arquivo JSON*/
            try {
                votoDAO.inserirJson(voto);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Houve algum erro ao salvar o voto no arquivo json.", "Erro", JOptionPane.ERROR_MESSAGE);
                return ;
            }
                    
            /*Antes de fazer algo usando a conexao verifica primeiro se tem internet*/
            if (!Conexao.getInternet()){
                JOptionPane.showMessageDialog(this, "Sem acesso a internet.", "Erro", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            
            /*Envia os arquivos .json para drive, sao usadas Threads para ficar mais rapido o processo*/
            mensagem = "";
                
            /* Enviando o vetor de eleitores para o Drive*/
            Thread p = new Thread(){
                @Override
                public void run() {
                    try {
                        eleitorDAO.enviaDrive();
                    }catch(Exception e){
                        mensagem = "Houve um erro ao enviar os eleitores para drive.";
                    }
                }            
            };

            /* Enviando o vetor de candidatos para o Drive*/
            Thread c = new Thread(){
                @Override
                public void run() {
                    try {
                        candidatoDAO.enviaDrive();
                    }catch(Exception e){
                        mensagem = "Houve um erro ao enviar os candidatos para drive.";
                    }
                }            
            };

            /* Enviando o vetor de votos para o Drive*/
            Thread v = new Thread(){
                @Override
                public void run() {
                    try {
                        votoDAO.enviaDrive();
                    }catch(Exception e){
                        mensagem = "Houve um erro ao enviar os votos para drive.";
                    }
                }            
            };

            /*Inicia a execucao das threads*/
            p.start();
            c.start();
            v.start();

            /*Espera cada Thread ser finalizada para prosseguir*/
            try {

                p.join();
                p.interrupt();
                c.join();
                c.interrupt();
                v.join();
                v.interrupt();

                if (!mensagem.equals("")){
                    JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
                    return ;
                }

            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(this, "Houve algum erro ao enviar os arquivos .json para o drive.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            /* Fechando a urna*/
            this.dispose();
        
        } finally {
            /*Volta o cursor como estava*/
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_btnConfirmaActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBranco;
    private javax.swing.JButton btnConfirma;
    private javax.swing.JButton btnCorrige;
    private javax.swing.JButton btnNum0;
    private javax.swing.JButton btnNum1;
    private javax.swing.JButton btnNum2;
    private javax.swing.JButton btnNum3;
    private javax.swing.JButton btnNum4;
    private javax.swing.JButton btnNum5;
    private javax.swing.JButton btnNum6;
    private javax.swing.JButton btnNum7;
    private javax.swing.JButton btnNum8;
    private javax.swing.JButton btnNum9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label16;
    private java.awt.Label label17;
    private java.awt.Label label18;
    private java.awt.Label label2;
    private java.awt.Label label21;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.Label lblDepNome;
    private java.awt.Label lblDepPartido;
    private java.awt.Label lblPreNome;
    private java.awt.Label lblPrePartido;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.Panel panelIcone;
    private java.awt.Panel pnlDeputadoFederal;
    private java.awt.Panel pnlPresidente;
    private java.awt.TextField texDepPrimeiroDigito;
    private java.awt.TextField texDepQuartoDigito;
    private java.awt.TextField texDepSegundoDigito;
    private java.awt.TextField texDepTerceiroDigito;
    private java.awt.TextField texPrePrimeiroDigito;
    private java.awt.TextField texPreSegundoDigito;
    // End of variables declaration//GEN-END:variables
}