package dao;

import dao.VotoDAO;

public class Urna {

    private int codigo;
    private VotoDAO votoDAO = new VotoDAO();

    public Urna(){
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