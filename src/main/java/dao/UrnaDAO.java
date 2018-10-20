package dao;

import dao.VotoDAO;

public class UrnaDAO {

    private int codigo;
    private VotoDAO votoDAO = new VotoDAO();

    public UrnaDAO(){
        this.codigo = 1;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public VotoDAO getVotoDAO() {
        return votoDAO;
    }

    public void setVotoDAO(VotoDAO votoDAO) {
        this.votoDAO = votoDAO;
    }   
}