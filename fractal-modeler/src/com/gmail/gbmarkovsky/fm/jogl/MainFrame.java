package com.gmail.gbmarkovsky.fm.jogl;

import java.awt.BorderLayout;

import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

import com.gmail.gbmarkovsky.fm.jogl.engine.LSystem;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8758921724990944528L;
	private GLCanvas canvas;
	private ControlPanel controlPanel;
	private SimpleRenderer listener;
	
	public MainFrame() {
		super("Простое использование OpenGL в Java");
        setSize(500, 500);

        canvas = new GLCanvas();
        listener = new SimpleRenderer(canvas);
		canvas.addGLEventListener(listener);
        addMouseWheelListener(listener);
        add(canvas, BorderLayout.CENTER);
        controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.EAST);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setSystem(LSystem lSystem, int depth, double dAng) {
		listener.setSystem(lSystem, depth, dAng);
	}
}
