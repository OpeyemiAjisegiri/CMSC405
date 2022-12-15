import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author Opeyemi Ajisegiri
 * Class: CMSC 405 
 * Project: 1
 * File: ImageTemplate.java
 * Note: This file defines and gives access the three images defined 
 * 		  as a two-dimensional array.
 */
public class ImageTemplate {
	
	  // Constants
    // X Size of Images
    private final static int IMGSIZEX = 10; //14;
    // Y Size of Images
    private final static int IMGSIZEY = 10; //14;
    
    //Defining the nnumber10 image
    public static int[][] number10 = {
	        {0, 1, 2, 0, 0, 0, 1, 1, 0, 0},
	        {0, 1, 2, 0, 0, 1, 1, 1, 1, 0},
	        {0, 1, 2, 0, 1, 1, 2, 2, 1, 1},
	        {0, 1, 2, 0, 1, 2, 0, 0, 2, 1},
	        {0, 1, 2, 0, 1, 2, 0, 0, 2, 1},
	        {0, 1, 2, 0, 1, 2, 0, 0, 2, 1},
	        {0, 1, 2, 0, 1, 2, 0, 0, 2, 1},
	        {0, 1, 2, 0, 1, 1, 2, 2, 1, 1},
	        {0, 1, 2, 0, 0, 1, 1, 1, 1, 0},
	        {0, 1, 2, 0, 0, 0, 1, 1, 0, 0}};
   
    //Defining the LetterJ image
   public static int[][] letterJ = {
	        {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
	        {0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
	        {0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
	        {0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
	        {0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
	        {0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
	        {0, 1, 2, 0, 0, 1, 2, 0, 0, 0},
	        {0, 1, 2, 0, 0, 1, 2, 0, 0, 0},
	        {0, 1, 1, 1, 1, 1, 2, 0, 0, 0},
	        {0, 0, 2, 2, 2, 2, 0, 0, 0, 0}};
   
   //Defining the triangle image
   public static int[][] triangle = {
	        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
	        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
	        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
	        {0, 0, 0, 1, 2, 2, 1, 0, 0, 0},
	        {0, 0, 1, 1, 2, 2, 1, 1, 0, 0},
	        {0, 0, 1, 2, 2, 2, 2, 1, 0, 0},
	        {0, 1, 1, 2, 2, 2, 2, 1, 1, 0},
	        {0, 1, 2, 2, 2, 2, 2, 2, 1, 0},
	        {1, 1, 2, 2, 2, 2, 2, 2, 1, 1},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    // Methods to apply pixel colors and
    public BufferedImage getImage(int[][] data) {
        BufferedImage image = new BufferedImage(IMGSIZEX, IMGSIZEY,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < IMGSIZEX; x++) {
            for (int y = 0; y < IMGSIZEY; y++) {
                int pixelColor = data[x][y];
                // Set Colors based on Binary Image value
                if (pixelColor == 0) {
                    pixelColor = Color.WHITE.getRGB();
                } else if (pixelColor == 1){
                    pixelColor = Color.BLACK.getRGB();
                }else {
                    pixelColor = Color.GRAY.getRGB();
                }
                image.setRGB(x, y, pixelColor);
            } // End for y.
        } // End for x.
        return image;
    } // End getData method.

}
