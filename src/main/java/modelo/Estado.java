package modelo;

/**
 * Enumerador com todos estados do Brasil.
 */
public enum Estado {

    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");
    
    private String nome;
    
    /**
     * Construtor do enum com parâmetros.
     * @param nome O nome do estado.
     */
    private Estado(String nome) {
        this.nome  = nome;
    }
    
    /**
     * Pega o nome do estado.
     * @return String - O nome.
     */
    public String getNome() {
        return nome;
    }
}