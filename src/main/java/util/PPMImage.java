package util;

public class PPMImage {
    
    private int width;
    private int height;
    private int coding;
    private String type;
    private int [] redPixels;
    private int [] greenPixels;
    private int [] bluPixels;

    /**
     * Contrutor sem parâmetro da classe.
     */
    public PPMImage() {
        
    }

    /**
     * Contrutor com parâmetros da classe.
     * @param width Largura da imagem.
     * @param height Altura da imagem.
     * @param coding O valor máximo de cada cor, representado por um número inteiro. O mesmo deverá ser maior que 0 até 255 (inclusive).
     * @param type Tipo do arquivo.
     * @param redPixels Vetor que guarda os pixels da cor vermelha.
     * @param greenPixels Vetor que guarda os pixels da cor verde.
     * @param bluPixels Vetor que guarda os pixels da cor azul.
     */
    public PPMImage(int width, int height, int coding, String type, int[] redPixels, int[] greenPixels, int[] bluPixels) {
        this.width       = width;
        this.height      = height;
        this.coding      = coding;
        this.type        = type;
        this.redPixels   = redPixels;
        this.greenPixels = greenPixels;
        this.bluPixels   = bluPixels;
    }
    
    /**
     * Pega o vetor de pixels vermelhos.
     * @return int[] - O vetor de pixels.
     */
    public int[] getRedPixels() {
        return redPixels;
    }

    /**
     * Seta o vetor de pixels vermelhos.
     * @param redPixels O vetor a ser setado.
     */
    public void setRedPixels(int[] redPixels) {
        this.redPixels = redPixels;
    }

    /**
     * Pega o vetor de pixels verdes.
     * @return int[] - O vetor de pixels.
     */
    public int[] getGreenPixels() {
        return greenPixels;
    }

    /**
     * Seta o vetor de pixels verdes.
     * @param greenPixels O vetor a sersetado.
     */
    public void setGreenPixels(int[] greenPixels) {
        this.greenPixels = greenPixels;
    }

    /**
     * Pega o vetor de pixels azuis.
     * @return int[] - O vetor de pixels.
     */
    public int[] getBluPixels() {
        return bluPixels;
    }

    /**
     * Seta o vetor de pixels azuis.
     * @param bluPixels O vetor a ser setado.
     */
    public void setBluPixels(int[] bluPixels) {
        this.bluPixels = bluPixels;
    }

    /**
     * Pega a largura da imagem.
     * @return int - A largura.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Seta a largura da imagem.
     * @param width A largura a ser setada.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Pega a altura da imagem.
     * @return int - A altura.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Seta a altura da imagem.
     * @param height A altura a ser setada.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Pega o valor máximo de cada cor da imagem.
     * @return int - O valor máximo.
     */
    public int getCoding() {
        return coding;
    }

    /**
     * Seta o valor máximo de cada cor da imagem.
     * @param coding O valor máximo a ser setado.
     */
    public void setCoding(int coding) {
        this.coding = coding;
    }
    
    /**
     * Pega o tipo da imagem.
     * @return String - O tipo.
     */
    public String getType() {
        return type;
    }

    /**
     * Seta o tipo da imagem.
     * @param type O tipo a ser setado.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Verifica se uma imagem é igual a outra.
     * @param ppm A imagem a ser verificada.
     * @return boolean - Retorna true caso a imagem seja igual, caso contrário retorna false.
     */
    public boolean equals(PPMImage ppm) {
        
        /*Se o tipo nao for o mesmo*/
        if(!(this.getType().equals(ppm.getType()))){
            return(false);
        }
        
        /*Se a largura nao for a mesma*/
        if(!(this.getWidth() == ppm.getWidth())){
            return(false);
        }
        
        /*Se a altura nao for a mesma*/
        if(!(this.getHeight() == ppm.getHeight())){
            return(false);
        }
        
        /*Se o numero maximo de cores nao for o mesmo*/
        if(!(this.getCoding() == ppm.getCoding())){
            return(false);
        }
        
        int auxTRed[]   = this.getRedPixels();
        int auxRed[]    = ppm.getRedPixels();
        int auxTGreen[] = this.getGreenPixels();
        int auxGreen[]  = ppm.getGreenPixels();
        int auxTBlue[]  = this.getBluPixels();
        int auxBlue[]   = ppm.getBluPixels();
        
        /*Se os vetores nao forem o mesmo*/
        for(int i = 0; i< redPixels.length; i++){
            if((auxTRed[i]   != auxRed[i])  ||
               (auxTGreen[i] != auxGreen[i])||
               (auxTBlue[i]  != auxBlue[i])){
                return(false);
            }
        }
  
        /*Imagem igual*/
        return(true);
    }
    
    /**
     * Converte a imagem PPM em PGM para ser desenhada na interface.
     * @return PGMImage - A imagem PPM convertida em PGM.
     */
    public PGMImage convertToPGM (){
        
        PGMImage image= new PGMImage();
        int pixels [] = new int [redPixels.length];

        for (int i = 0; i < redPixels.length; i++) {
            pixels[i] = (int)((double)((redPixels[i] + greenPixels[i] + bluPixels[i]) / 3));
        }
        
        image.setCoding(coding);
        image.setType("P2");
        image.setHeight(height);
        image.setWidth(width);
        image.setPixels(pixels);
        
        return image;
    }
}