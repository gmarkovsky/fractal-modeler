package com.gmail.gbmekp.fm.j2d;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	private static final int WIDTH = 800;
	private static final int HEIGHT = 530;
	private static final String TITLE = "Моделирование фракталов";
    private ControlPanel controls;
	
	
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
	    setLayout(new GridBagLayout());
	    
		ImagePanel comp = new ImagePanel();
        add(comp, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 0, 0, 0), 0, 0));
        controls = new ControlPanel(comp);
        add(controls, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
                new Insets(0, 0, 0, 0), 0, 0));
	}
}
