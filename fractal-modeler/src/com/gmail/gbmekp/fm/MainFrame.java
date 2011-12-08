package com.gmail.gbmekp.fm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import com.gmail.gbmekp.fm.j2d.J2DPanel;
import com.gmail.gbmekp.fm.jogl.JoglCanvas;

/**
 * Главное окно приложения.
 * 
 * @author george
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 4718365275850516051L;
	private static final String TITLE = "Моделирование фракталов";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 530;

	private final ControlPanel controls;
	private final Controller controller;
	private JPanel wrapper;
	
	public MainFrame() {
		super(TITLE);
		
		controller = new Controller();
		controls = new ControlPanel(controller);
		
		initMenu();
		initComponents();
		
		initOpenGL();
		
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void initMenu() {
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Файл");
		
		JMenu modeMenu = new JMenu("Режим");
		
		JRadioButtonMenuItem j2dItem = new JRadioButtonMenuItem(new AbstractAction("Java 2D") {
			private static final long serialVersionUID = 4147074802769544221L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				initJava2D();
			}
		});
		
		modeMenu.add(j2dItem);
		
		JRadioButtonMenuItem joglItem = new JRadioButtonMenuItem(new AbstractAction("OpenGL") {
			private static final long serialVersionUID = -1410931317991814774L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				initOpenGL();
			}
		});
		joglItem.setSelected(true);
		modeMenu.add(joglItem);
		
		ButtonGroup group = new ButtonGroup();
		group.add(j2dItem);
		group.add(joglItem);
		
		
		fileMenu.addSeparator();
		fileMenu.add(new AbstractAction("Выход") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenu helpMenu = new JMenu("Справка");
		JMenuItem aboutItem = new JMenuItem("О программе");
		
		helpMenu.add(aboutItem);
		
		menu.add(fileMenu);
		menu.add(modeMenu);
		//menu.add(helpMenu);
		
		//setJMenuBar(menu);
	}


	private void initComponents() {
	    setLayout(new GridBagLayout());
	    
	    wrapper = new JPanel(new BorderLayout());
        add(wrapper, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 0, 0, 0), 0, 0));
		
        add(controls, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
                new Insets(0, 0, 0, 0), 0, 0));
	}
	
	private void initJava2D() {
        J2DPanel imagePanel = new J2DPanel();
        wrapper.removeAll();
		wrapper.add(imagePanel);
		wrapper.revalidate();
        controller.setCanvas(imagePanel);
	}
	
	private void initOpenGL() {
        JoglCanvas joglCanvas = new JoglCanvas();
        wrapper.removeAll();
		wrapper.add(joglCanvas);
		wrapper.revalidate();
        controller.setCanvas(joglCanvas);
	}
}
