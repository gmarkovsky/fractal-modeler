package com.gmail.gbmekp.fm.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Главное окно приложения.
 * 
 * @author george
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 501818573355567849L;
	private static final int WIDTH = 740;
	private static final int HEIGHT = 530;
	private static final String TITLE = "Моделирование фракталов";
	
	
	public MainFrame() {
		super(TITLE);

		initComponents();
		initMenu();
		
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
	    setLayout(new BorderLayout());
	    
		ImagePanel comp = new ImagePanel();
        add(comp, BorderLayout.CENTER);
		add(new ControlPanel(comp), BorderLayout.EAST);
	}
}
