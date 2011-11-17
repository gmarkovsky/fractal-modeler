package com.gmail.gbmekp.fm.jogl;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.State;
import com.gmail.gbmekp.fm.engine.Vector;

public class SimpleRenderer implements GLEventListener, MouseWheelListener, LineCanvas {
    private GL gl;
    private final GLCanvas canvas;
    
    private int width = 100;
    private int height = 100;
	
	private LSystem lSystem;
	private int depth = 4;
	
    private Turtle drawTurtle;
    private Turtle metricTurtle;
    private BoundCanvas boundCanvas;
	
	public SimpleRenderer(GLCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL();
        
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		drawTurtle = new Turtle(this, 1.0, 35 * Math.PI / 180);
		boundCanvas = new BoundCanvas();
        metricTurtle = new Turtle(boundCanvas, 1.0, 35 * Math.PI / 180);
		
        lSystem = new LSystem("X", new String[][] {
                {"F", "FF"},
                {"X", "F[+X]F[-X]+X"}}, "Sticks");
        
        metricTurtle.draw(lSystem.getResult(4), new State(0, 0, 0));
        
        width = (int) boundCanvas.getBounds().getWidth();
        height = (int) boundCanvas.getBounds().getHeight();
	}

	public void setSystem(LSystem lSystem, int depth, double angle, boolean force, Vector vector) {
        this.lSystem = lSystem;
		drawTurtle = new Turtle(this, 1.0, angle, force, vector);
        boundCanvas = new BoundCanvas();
        metricTurtle = new Turtle(boundCanvas, 1.0, angle);
        this.depth = depth;
        metricTurtle.draw(lSystem.getResult(depth), new State(0, 0, 0));
        
        width = (int) boundCanvas.getBounds().getWidth();
        height = (int) boundCanvas.getBounds().getHeight();
        
        reshape();
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        double dy = boundCanvas.getBounds().getY();
        
        gl.glTranslated(width/2, -dy, 0);
        
        gl.glColor3d(1.0, 0.0, 0.0);
        
        gl.glBegin(GL.GL_LINES);
        	drawTurtle.draw(lSystem.getResult(depth), new State(0, 0, 0));
        gl.glEnd();
        
        gl.glTranslated(-width/2, dy, 0);
        gl.glFlush();
	}

	public void drawLine(double x1, double y1, double x2, double y2) {
		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		this.width = this.height * width / height;
		
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho( 0, this.width, 0, this.height, -1, 1 );
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		
	}

	private void reshape() {
		canvas.reshape(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.repaint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			width *= 1.2;
			height *= 1.2;
		} else {
			width /= 1.2;
			height /= 1.2;
		}
		
		reshape();
	}
}
