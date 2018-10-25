package util;

public class PGMImage {
    
    private int width;
    private int height;
    private int coding;
    private String type;
    private int[] pixels;

    /**
     * Construtor sem parâmetros da classe.
     */
    public PGMImage() {
        
    }
    
    /**
     * Contrutor com parâmetros da classe.
     * @param width A largura da imagem.
     * @param height A altura da imagem.
     * @param coding  O valor máximo de intensidade presente na imagem.
     * @param type O tipo da imagem.
     * @param values Vetor de pixels da imagem.
     */
    public PGMImage(int width, int height, int coding, String type, int[] values) {
        this.width  = width;
        this.height = height;
        this.coding = coding;
        this.type   = type;
        this.pixels = values;
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
     * Pega o valor máximo de intensidade presente na imagem.
     * @return int - O valor máximo.
     */
    public int getCoding() {
        return coding;
    }

    /**
     * Seta o valor máximo de intensidade presente na imagem.
     * @param coding O valor máximo a ser setado.
     */
    public void setCoding(int coding) {
        this.coding = coding;
    }

    /**
     * Pega o vetor de pixels.
     * @return int[] - O vetor de pixels.
     */
    public int[] getValues() {
        return pixels;
    }

    /**
     * Seta o vetor de pixels.
     * @param pixels O vetor a ser setado.
     */
    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    /**
     * Pega um pixel da imagem.
     * @param index Posição desejada para pegar o pixel.
     * @return int - O pixel.
     */
    public int getPixel(int index) {
        return this.pixels[index];
    }

    /**
     * Seta um pixel da imagem.
     * @param index Posição do pixel a ser setada.
     * @param pixel O pixel a ser setado.
     */
    public void setPixel(int index, int pixel) {
        this.pixels[index] = pixel;
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
     * Tranforma os pixels da imagem para o modo (R, G e B).
     * @return int[] - O vetor.
     */
    public int[] toRGBModel (){
        
        int [] result = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            result [i] = 0xFF000000+getPixel(i)*0x010101;
        }
        
        return result;
    }
    
//    public PGMImage toNegatif (){
//        
//        PGMImage image=new PGMImage(width, height, coding, type, pixels);
//        int [] resultPixels = new int [pixels.length];
//        for (int i = 0; i < pixels.length; i++) {
//            resultPixels[i] = coding-pixels[i];
//        }
//        
//        image.setPixels(resultPixels);
//        
//        return image;                
//    }
//    
//    public void thrishot (int axis){
//        
//        for (int i = 0; i < pixels.length; i++) {
//            if (pixels[i] <= axis) {
//                  pixels[i]=0;
//            }else{pixels[i]=255;}
//        }
//    }
//    
//    public PGMImage thrishot (Integer[] axis){
//        
//        PGMImage result = new PGMImage(width, height, coding, type, pixels);
//        int [] pix = new int[result.getValues().length];
//        for (int i = 0; i < result.getValues().length; i++) {
//            
//            int color=0;
//            
//            for (int j = 1; j < axis.length; j++) {
//                
//                if ((result.getPixel(i) <= axis[j]) &&
//                    (result.getPixel(i) > axis[j-1])) {
//                    color=((int)((double)result.getCoding()/axis.length)*j);
//                }
//            }
//            
//            pix[i]=color;            
//        }
//        
//        result.setPixels(pix);
//        
//        return result;
//    }
//    
//    public int [][] toMatrice () {
//        
//        int [][] result = new int [this.width][this.height];
//        
//        for (int i = 0; i < this.width; i++) {
//            for (int j = 0; j < this.height; j++) {
//                result [i][j]=this.pixels[i+j*this.width];
//            }
//        }
//        
//        return result;
//    }
}