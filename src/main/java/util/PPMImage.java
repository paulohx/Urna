package util;

public class PPMImage {
    
    private int width;
    private int height;
    private int coding;
    private String type;
    private int [] redPixels;
    private int [] greenPixels;
    private int [] bluPixels;

    public PPMImage() {
        
    }

    public PPMImage(int width, int height, int coding, String type, int[] redPixels, int[] greenPixels, int[] bluPixels) {
        this.width       = width;
        this.height      = height;
        this.coding      = coding;
        this.type        = type;
        this.redPixels   = redPixels;
        this.greenPixels = greenPixels;
        this.bluPixels   = bluPixels;
    }
    
    public int[] getRedPixels() {
        return redPixels;
    }

    public void setRedPixels(int[] redPixels) {
        this.redPixels = redPixels;
    }

    public int[] getGreenPixels() {
        return greenPixels;
    }

    public void setGreenPixels(int[] greenPixels) {
        this.greenPixels = greenPixels;
    }

    public int[] getBluPixels() {
        return bluPixels;
    }

    public void setBluPixels(int[] bluPixels) {
        this.bluPixels = bluPixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(PPMImage ppm) {
        
        
        if(!(this.getType().equals(ppm.getType()))){
            return(false);
        }
        
        if(!(this.getWidth() == ppm.getWidth())){
            return(false);
        }
        
        if(!(this.getHeight() == ppm.getHeight())){
            return(false);
        }
                
        if(!(this.getCoding() == ppm.getCoding())){
            return(false);
        }
        
        int auxTRed[]   = this.getRedPixels();
        int auxRed[]    = ppm.getRedPixels();
        int auxTGreen[] = this.getGreenPixels();
        int auxGreen[]  = ppm.getGreenPixels();
        int auxTBlue[]  = this.getBluPixels();
        int auxBlue[]   = ppm.getBluPixels();
        
        for(int i = 0; i< redPixels.length; i++){
            if((auxTRed[i]   != auxRed[i])  ||
               (auxTGreen[i] != auxGreen[i])||
               (auxTBlue[i]  != auxBlue[i])){
                return(false);
            }
        }
  
        return(true);
    }

}