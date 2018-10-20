package util;

import java.io.FileInputStream;
import java.util.Scanner;

public class PPMFileReader {
    
    public static PPMImage readImage (String fileName){
        
        /*Instanciando um objeto do tipo PPMImage*/
        PPMImage image = new PPMImage();
        
        try {
            
            /*Abre o arquivo para a leitura*/
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            
            /*A primeira linha do arquivo e o tipo dele*/
            image.setType(scanner.nextLine());
            
            /*Nao entendi ainda... mas deve que nao pega esses caracteres*/
            scanner.skip("(#.*\n)*");
            
            /*Segunda linha do arquivo e a largura dele*/
            image.setWidth(scanner.nextInt());
            
            /*Terceira linha do arquivo e a altura dele*/
            image.setHeight(scanner.nextInt());
            
            /*As cores vao ate 255*/
            image.setCoding(scanner.nextInt());
                    
            int tam = image.getHeight() * image.getWidth();
            
            /*Cria os vetores para guardar os pixels de cada cor (R, G e B)*/
            int redPixels   [] = new int[tam];
            int greenPixels [] = new int[tam];
            int bluePixels  [] = new int[tam];
            
            /*Preenche os vetores*/
            for (int i = 0; i < tam; i++) {
                redPixels[i]   = scanner.nextInt();
                greenPixels[i] = scanner.nextInt();
                bluePixels[i]  = scanner.nextInt();
            }
            
            /*Joga no obejto os vetores*/
            image.setRedPixels(redPixels);
            image.setGreenPixels(greenPixels);
            image.setBluPixels(bluePixels);
            
            /*Para a leitura*/
            scanner.close();
            
        }catch (Exception e) {            
            System.out.println("Exception during reading the file");
            e.printStackTrace();
        }
        
        /*Retorna o objeto*/
        return image;        
    }
}