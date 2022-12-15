import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.util.gl2.GLUT;


/**
 * @author Opeyemi Ajisegiri
 * Class: CMSC 405 
 * Project: 2
 * File: Project2.java
 * Note: Project was programmed based on the project and image template provided by the professor, 
 * 		 to render the  3D graphis and shapes and apply affine transformation to the graphics using 
 *       OpenGL  
 *        */

public class Project2 extends GLJPanel implements GLEventListener {
	
	private int frameNumber;
	private long elapsedTimeMillis;
    private GLUT glut = new GLUT(); 
    
    /**
     * A main routine to create and show a window contains the 3D images
     * to be drawn.  
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("3D Shapes in OpenGL [JOGL]");
        Project2 panel = new Project2();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
        
		Timer animationTimer;
		final long startTime = System.currentTimeMillis();
		animationTimer = new Timer(1600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(panel.frameNumber > 5) {
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
     * Constructor for class Project 2.
     */
    public Project2() {
        super( new GLCapabilities(null) ); 
        setPreferredSize( new Dimension(850,650) );
        addGLEventListener(this); 
        //addKeyListener(this);
    }
    
    /**-------------  The methods to draw the three Dimensional shapes in OpenGL  ----------------**/
    
    double rotateX = 15;    // rotations of the cube about the axes
    double rotateY = -15;
    double rotateZ = 0;
    
    /**
     * Attempted to design the shapes using vertices and transforms 
     * **/
    
    /*   
    private void square(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r,g,b);
        gl2.glBegin(GL2.GL_POLYGON);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.5, 0.5);
        gl2.glEnd();
    }
    
    private void triangle(GL2 gl2, double r, double g, double b) {
    	gl2.glColor3d(r, g, b);
    	gl2.glBegin(gl2.GL_TRIANGLES);
    	gl2.glVertex3d(-0.5, -0.5, 0.5);
    	gl2.glVertex3d(0.5,  -0.5, 0.5);
    	gl2.glVertex3d(0,   0.5, 0.5);
    	gl2.glEnd();
    	
    }
    
    private void hexagon(GL2 gl2, double r, double g, double b) {
    	gl2.glColor3d(r, g, b);
    	gl2.glBegin(gl2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(-1.0, 0, 0);
        gl2.glVertex3d(-0.5, 0.5, -0.5);
        gl2.glVertex3d(0.5, 0.5, -0.5);
        gl2.glVertex3d(1, 0, 0);
        gl2.glVertex3d(0.5, -0.5, 0.5);
    	gl2.glEnd();
    }
    
    private void hexagonalPrism(GL2 gl2, double size) {
    	gl2.glPushMatrix();
    	gl2.glScaled(size, size, size);
    	gl2.glTranslated(2, -2, 0);
    	gl2.glRotated(90, 1, 0, 0);
    	hexagon(gl2,0,1,0);
    	
    	gl2.glPushMatrix();
    	square(gl2, 1, 0, 1);
    	gl2.glPopMatrix();
    	
    	gl2.glPushMatrix();
    	gl2.glRotated(45, 0, 1, 0);
    	square(gl2, 1, 1, 1);
    	gl2.glPopMatrix();
    	
    	gl2.glPopMatrix();
    }
    
    private void cube(GL2 gl2, double size) {
        gl2.glPushMatrix();
        gl2.glScaled(size,size,size);  
        gl2.glTranslated(3,3,0);
        square(gl2,1, 0, 0);
        
        gl2.glPushMatrix();
        gl2.glRotated(90, 0, 1, 0);
       
        square(gl2,0, 1, 0); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0);
        square(gl2,0, 0, 1); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0);
        square(gl2,0, 1, 1);
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0);
        square(gl2,1, 0, 1);
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(90, 1, 0, 0);
        square(gl2,1, 1, 0);
        gl2.glPopMatrix();
        
        gl2.glPopMatrix(); // Restore matrix to its state before cube() was called.
    }*/
    
    /**  The drawHouse method  draws a house using the shapes: cone [roof], and
     *  cube [building, and chimney]; using transform methods to put the shapes in the
     *   needed sizes and position.
     * @param GL2 gl2
     * **/
    private void drawHouse(GL2 gl2) {
    	gl2.glPushMatrix();
    	
    	//The roof of the house
        gl2.glTranslated(0, 1, 0);
        gl2.glPushMatrix();
        gl2.glRotatef(-90,1,0,0);
        gl2.glColor3d(0.4, 0.4, 0.4);
        glut.glutSolidCone(4, 3, 22, 6);
        gl2.glPopMatrix();
       
        //The building
        gl2.glPushMatrix();
        gl2.glColor3d(1, 0, 0);
        gl2.glTranslated(0, -1.5, 0);
        glut.glutSolidCube(4);
        gl2.glPopMatrix();
       
        //The Chimney
        gl2.glTranslated(0.75,2/*0.5*/,-0.75);         
        gl2.glPushMatrix();                   
        gl2.glColor3d(0.25, 0.3, 0.25);
        gl2.glScaled(0.5,2/*3*/,1);
        glut.glutSolidCube((float) 0.75);
        gl2.glPopMatrix();
        
        gl2.glPopMatrix();
    }
    
    /**  The drawTree method  draws a tree using the shapes: cylinder, sphere and octahedron;
     *   and using transform methods to put the shapes in the needed sizes and position.
     * @param GL2 gl2
     * **/
    private void drawTree(GL2 gl2) {
    	//The trunk of the tree
        gl2.glPushMatrix();
        gl2.glColor3d(0.5, 0.35, 0.05);
        glut.glutSolidCylinder(0.5, 4.0, 25, 6);
        gl2.glPopMatrix();
        
        //Creating a Sphere to be used the top of the tree
        gl2.glPushMatrix();
        gl2.glColor3d(0, 1, 0);
        gl2.glCreateShader(2);
        glut.glutSolidSphere(2, 22, 8);
        gl2.glPopMatrix();
        
        //Using Octahedron as the fruit
        gl2.glPushMatrix();
        gl2.glColor3d(1,1,0);
        gl2.glTranslated(0, 2, 0.5);
        gl2.glScaled(0.25, 0.25, 0.5);
        glut.glutSolidOctahedron();
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glColor3d(1,1,0);
        gl2.glTranslated(-1, -1.6, -0.9);
        gl2.glScaled(0.25, 0.25, 0.5);
        glut.glutSolidOctahedron();
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glColor3d(1,1,0);
        gl2.glTranslated(0.5, 1.4, -1);
        gl2.glScaled(0.25, 0.25, 0.5);
        glut.glutSolidOctahedron();
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glColor3d(1,1,0);
        gl2.glTranslated(-1, -0.03, -1.5);
        gl2.glScaled(0.25, 0.25, 0.5);
        glut.glutSolidOctahedron();
        gl2.glPopMatrix();
    }
    
    /**  The draw method  draws the image by call the defined method to draw the objects
     *  in the 3D image, and a walking path to the house using the tetrahedron shape.
     * @param GL2 gl2
     * **/
    private void draw(GL2 gl2) { 
	  gl2.glRotated(rotateZ,0,0,1);
	  gl2.glRotated(rotateY,0,1,0);
	  gl2.glRotated(rotateX,1,0,0);        
	
	 /*cube(gl2,1);
	  //trianglarPrism(gl2,1);
	  hexagonalPrism(gl2, 1);
	 */
	
	drawHouse(gl2);
 
	 gl2.glPushMatrix();
	 gl2.glRotated(90, 1, 0, 0);
	 gl2.glTranslated(4.5, 2, -3);
	 drawTree(gl2);
	 gl2.glPopMatrix();
	  
	gl2.glPushMatrix();
	gl2.glColor3d(0,1,0);
	gl2.glScaled(0.5, 0.5, 0.5);
	gl2.glTranslated(-6, -6, 2);
	glut.glutSolidTetrahedron();
	
	gl2.glPushMatrix();
	gl2.glColor3d(0,1,0);
	gl2.glScaled(0.5, 0.5, 0.5);
	gl2.glTranslated(-2.6, -3, 2);
	glut.glutSolidTetrahedron();
	gl2.glPopMatrix();
	
	gl2.glPushMatrix();
	gl2.glColor3d(0,1,0);
	gl2.glScaled(0.5, 0.5, 0.5);
	gl2.glTranslated(-2, -1.6, 1);
	glut.glutSolidTetrahedron();
	gl2.glPopMatrix();

	gl2.glPopMatrix();
}
    /*  ---------------- GLEventListener Methods ---------------------------  
     * Methods to draw, display and dispose of the 3D graphics.
     */
    
    /**
     * The method to draw and display the image.
     * @param GLAutoDrawable
     */
    public void display(GLAutoDrawable drawable) {    

    	
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
         
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );;
        
        /*
        //gl2.glMatrixMode(GL2.GL_PROJECTION);  
        //gl2.glMatrixMode(GL_MATRIX_MODE);
        //gl2.glLoadIdentity();
         * */
      gl2.glPushMatrix();
      draw(gl2);
      gl2.glPopMatrix();
      
    } 
    
    /** Initializing the need variables, co-ordinates and projection to display the image **/
    public void init(GLAutoDrawable drawable) {
           // called when the panel is created
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
         gl2.glOrtho(-5, 5 ,-5, 5, -5, 5);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glClearColor( 0, 0, 0, 1 );
        gl2.glEnable(GL2.GL_DEPTH_TEST);
    }

    /**  The method called to dispose of the image**/
    public void dispose(GLAutoDrawable drawable) {
    }

    /**  The method called to display the image when an action such as the resizing of the
     *  window pane has occurred. **/
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }
    
    
}
