package com.gmail.gbmekp.fm.jogl;

import java.awt.BorderLayout;

import javax.media.opengl.GLCanvas;
import javax.swing.JPanel;

import com.gmail.gbmekp.fm.FractalCanvas;
import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.Vector;

public class JoglCanvas extends JPanel implements FractalCanvas {
	private static final long serialVersionUID = 5271508282265404984L;
	
	private GLCanvas canvas;
	private SimpleRenderer listener;
	
	public JoglCanvas() {
		canvas = new GLCanvas();
        listener = new SimpleRenderer(canvas);
		canvas.addGLEventListener(listener);
        addMouseWheelListener(listener);
        setLayout(new BorderLayout());
        add(canvas);
	}
	
	@Override
	public void paintFractal(LSystem lSystem, int dAplha, int depth, boolean force, Vector vector) {
		listener.setSystem(lSystem, depth, dAplha * Math.PI / 180, force, vector);
		canvas.repaint();
	}
}
