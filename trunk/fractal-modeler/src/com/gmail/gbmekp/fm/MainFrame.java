package com.gmail.gbmekp.fm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.gmail.gbmekp.fm.j2d.ImagePanel;

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
		initJava2D();
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void initMenu() {
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Файл");
		
		JMenuItem exitItem = new JMenuItem("Выход");
		
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu helpMenu = new JMenu("Справка");
		JMenuItem aboutItem = new JMenuItem("О программе");
		
		helpMenu.add(aboutItem);
		
		menu.add(fileMenu);
		menu.add(helpMenu);
		
		setJMenuBar(menu);
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
        ImagePanel imagePanel = new ImagePanel();
        wrapper.removeAll();
		wrapper.add(imagePanel);
        controller.setCanvas(imagePanel);
	}
	
	@SuppressWarnings("unused")
	private void initOpenGL() {
        ImagePanel imagePanel = new ImagePanel();
        wrapper.removeAll();
		wrapper.add(imagePanel);
        controller.setCanvas(imagePanel);
	}
}
