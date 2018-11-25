package dao;

public class UrnaDAO {

    private int codigo;
    private VotoDAO votoDAO;
    
    /**
     * Construtor sem parâmetro - Inicia as variáveis.
     */
    public UrnaDAO(){
        this.codigo  = 1; /*Esta sendo utilizada somente UMA urna*/
        this.votoDAO = new VotoDAO();
    }
    
    /**
     * Pega o código da urna.
     * @return O código.
     */
    public int getCodigo(){
        return this.codigo;
    }
    
    /**
     * Pega o objeto dao do voto.
     * @return VotoDAO - O objeto.
     */
    public VotoDAO getVotoDAO() {
        return votoDAO;
    }

    /**
     * Seta o voto dao da urna.
     * @param votoDAO O voto dao a ser setado.
     */
    public void setVotoDAO(VotoDAO votoDAO) {
        this.votoDAO = votoDAO;
    }   
}