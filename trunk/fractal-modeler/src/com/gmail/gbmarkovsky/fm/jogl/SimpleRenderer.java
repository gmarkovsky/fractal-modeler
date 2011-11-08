package com.gmail.gbmarkovsky.fm.jogl;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import com.gmail.gbmarkovsky.fm.jogl.engine.LSystem;
import com.gmail.gbmarkovsky.fm.jogl.engine.State;
import com.gmail.gbmarkovsky.fm.jogl.engine.Turtle;

public class SimpleRenderer implements GLEventListener, MouseWheelListener {
    private GL gl;
    private final GLCanvas canvas;
    
    private int width = 100;
    private int height = 100;
	private int list;
	
	private double maxY = Double.NEGATIVE_INFINITY;
	private double maxX = Double.NEGATIVE_INFINITY;
	private double minX = Double.POSITIVE_INFINITY;
	
	public SimpleRenderer(GLCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL();
        
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		Turtle turtle = new Turtle(this, 1.0, 1.0);
        
        LSystem anObject = new LSystem("X", new String[][] {
                {"F", "FF"},
                {"X", "F[+X]F[-X]+X"}}, "Sticks");
        
//		LSystem anObject = new LSystem("[F]+[F]+[F]+[F]+[F]+[F]", new String[][]{
//                {"F", "F[++F][--F][-FF][+FF]FF[+F][-F]FF"}}, "Snowflake");
        
        list = gl.glGenLists(1);
        
        gl.glNewList(list, GL.GL_COMPILE);
        gl.glColor3d(1.0, 0.0, 0.0);
        gl.glBegin(GL.GL_LINES);
        turtle.draw(anObject.getResult(4), new State(0, 10, 0));
        gl.glEnd();
        gl.glEndList();
        
        width = (int) (maxX - minX);
        height = (int) (maxY + 10);
	}

	public void setSystem(LSystem lSystem, int depth, double angle) {
		Turtle turtle = new Turtle(this, 1.0, angle);
        
        list = gl.glGenLists(1);
        
        gl.glNewList(list, GL.GL_COMPILE);
        gl.glColor3d(1.0, 0.0, 0.0);
        gl.glBegin(GL.GL_LINES);
        turtle.draw(lSystem.getResult(depth), new State(0, 10, 0));
        gl.glEnd();
        gl.glEndList();
        
        width = (int) (maxX - minX);
        height = (int) (maxY + 10);
        
        reshape();
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glTranslated(width/2, 0, 0);
        gl.glCallList(list);
        gl.glTranslated(-width/2, 0, 0);
        gl.glFlush();
	}

	public void drawLine(double x1, double y1, double x2, double y2) {
		if (y1 > maxY) {
			maxY = y1;
		}
		if (y2 > maxY) {
			maxY = y2;
		}
		if (x1 > maxX) {
			maxX = x1;
		}
		if (x2 > maxX) {
			maxX = x2;
		}
		if (x1 < minX) {
			minX = x1;
		}
		if (x2 < minX) {
			minX = x2;
		}
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
