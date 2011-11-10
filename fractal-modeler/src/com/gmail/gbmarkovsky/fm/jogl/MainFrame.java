package com.gmail.gbmarkovsky.fm.jogl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
        setSize(700, 500);

        canvas = new GLCanvas();
        listener = new SimpleRenderer(canvas);
		canvas.addGLEventListener(listener);
        addMouseWheelListener(listener);
        //add(canvas, BorderLayout.CENTER);
        controlPanel = new ControlPanel(this);
        //add(controlPanel, BorderLayout.EAST);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        add(canvas, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(controlPanel, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
                new Insets(0, 0, 0, 0), 0, 0));
	}
	
	public void setSystem(LSystem lSystem, int depth, double dAng) {
		listener.setSystem(lSystem, depth, dAng);
	}
}
