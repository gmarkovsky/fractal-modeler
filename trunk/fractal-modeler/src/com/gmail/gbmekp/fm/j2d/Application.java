package com.gmail.gbmekp.fm.j2d;

import javax.swing.SwingUtilities;

/**
 * Запускаемая единица.
 * 
 * @author george
 *
 */
public class Application implements Runnable {

	@Override
	public void run() {
		new MainFrame().setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Application());
	}
}
