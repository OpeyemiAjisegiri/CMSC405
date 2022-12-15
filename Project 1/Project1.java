import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
//import java.util.Timer;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;
//import javax.management.timer.Timer;
import javax.swing.JFrame;

/**
 * @author Opeyemi Ajisegiri
 * Class: CMSC 405 
 * Project: 1
 * File: Project1.java
 * Note: Project was programmed based on the project and image template provided by the professor, and
 * 		 the applyWindowToViewport function was not changed from the provided template.
 */
public class Project1 extends JPanel {

	//Global Variables
	private int frameNumber;
	private long elapsedTimeMillis;
	private float pixelSize;
	
    static int translateX = 0;
    static int translateY = 0;
    static double rotation = 0.0;
    static double scaleX = 1.0;
    static double scaleY = 1.0;
	
    ImageTemplate myImages = new ImageTemplate();
    BufferedImage image;

    /**
     * The main function creates a Graphic User Interface window that contains the panel of drawings
     * and their transformations. The program ends when the user closes the window.
     * @param args 
     */
	public static void main(String[] args) {
		JFrame window = new JFrame("Java Image Animation");
		Project1 panel = new Project1();
		panel.setPreferredSize(new Dimension(800, 600));
		
		window.setContentPane(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screen.width - window.getWidth())/2,
							(screen.height - window.getHeight())/2);
		Timer animationTimer;
		final long startTime = System.currentTimeMillis();
		animationTimer = new Timer(1600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(panel.frameNumber > 5 /*6*/) {
					panel.frameNumber = 0;
				}else {
					panel.frameNumber++;
				}
				panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
				panel.repaint();
			}});
		
		window.setVisible(true);
		animationTimer.start();
	}
	
    /**
     * This paintComponent method draws the content of the JPanel using a graphics context
     * parameter. It draws the images gotten remotely from the Images template.
     * @param g2: The graphic component
     */
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, getWidth(),getHeight());
		applyWindowToViewportTransformation(g2, -75, 15, -5, 45, true);
		AffineTransform savedTransform = g2.getTransform();
		System.out.println("Frame is " + frameNumber);
        switch (frameNumber) {
	        case 0:
	            translateX = 0;
	            translateY = 0;
	            scaleX = 1.0;
	            scaleY = 1.0;
	            rotation = 0;
	        	break;
	        case 1: 
	        	translateX = -5;
	            break;
	        case 2: 
	            translateY = 7;;
	            break;
	        case 3:
	            rotation = 45 * Math.PI / 180.0;
	            break;
	        case 4: 
	        	rotation = -90 * Math.PI/180.0;
	        	break;
	        case 5:
	        	scaleX = 2.0;
	        	break;
	        case 6:
	        	scaleY = 0.5;
	        	break;
	        default:
	            break;
        }
        
        drawTriangle(g2);
        g2.setTransform(savedTransform);
        
        drawLetterJ(g2);
        g2.setTransform(savedTransform);
        
        drawNumber10(g2);
        g2.setTransform(savedTransform);
 
	}

	/**
	 * The drawNumber10 function gets the number10 image from the image template, 
	 * draws it and animate it based on the value of the global variable used.
	 * @param g2: Graphics2D component that is to be drawn on.
	 */
	private void drawNumber10(Graphics2D g2) {
		image = myImages.getImage(ImageTemplate.number10);
        g2.translate(translateX, translateY); // Move image.
        // To offset translate again
        // This allows you to place your images across your graphic
        g2.translate(-50,10);
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(image, 0, 0, this); // Draw image.
		
	}

	/**
	 * The drawLetterJ function gets the letterJ image from the image template, 
	 * draws it and animate it based on the value of the global variable used.
	 * @param g2: Graphics2D component that is to be drawn on.
	 */
	private void drawLetterJ(Graphics2D g2) {
		image = myImages.getImage(ImageTemplate.letterJ);
        g2.translate(translateX, translateY); // Move image.
        // To offset translate again
        // This allows you to place your images across your graphic
        g2.translate(-30,30);
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(image, 0, 0, this); // Draw image.
		
	}

	/**
	 * The drawTriangle function gets the triangle image from the image template, 
	 * draws it and animate it based on the value of the global variable used.
	 * @param g2: Graphics2D component that is to be drawn on.
	 */
	private void drawTriangle(Graphics2D g2) {
		image = myImages.getImage(ImageTemplate.triangle);
        g2.translate(translateX, translateY);
        g2.translate(-10, 10);
        g2.rotate(rotation);
        g2.scale(scaleX, scaleY);
        g2.drawImage(image, 0, 0, this);
	}

	/**
	 * The applyWindowToViewportTransformation function transforms the viewport co-ordinates of the images to window; and sets the pixel size (height 
	 * and width) of pixel based on the aspect and request ratio of the drawn image(s).
	 * @param g2: The drawing context.
	 * @param  left: The requested value of x at the left of the drawing.
	 * @param right: The requested value of x at the right of the drawing.
	 * @param  bottom: The request value of y at the bottom of the drawing.
	 * @param  top: The requested value of y at the top of the drawing. 
	 * @param preserveAspect
	 */
	private void applyWindowToViewportTransformation(Graphics2D g2, double left, double right, double bottom, double top, boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double)height / width);
            double requestedAspect = Math.abs(( bottom-top ) / ( right-left ));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom-top) * (displayAspect/requestedAspect - 1);
                bottom += excess/2;
                top -= excess/2;
            }
            else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
                double excess = (right-left) * (requestedAspect/displayAspect - 1);
                right += excess/2;
                left -= excess/2;
            }
        }
        g2.scale( width / (right-left), height / (bottom-top) );
        g2.translate( -left, -top );
        double pixelWidth = Math.abs(( right - left ) / width);
        double pixelHeight = Math.abs(( bottom - top ) / height);
        pixelSize = (float)Math.max(pixelWidth,pixelHeight);
		
	}
}
