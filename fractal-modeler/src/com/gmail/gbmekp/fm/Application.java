package com.gmail.gbmekp.fm;

import javax.swing.SwingUtilities;

/**
 * Класс для запуска приложения.
 * 
 * @author george
 *
 */
public class Application implements Runnable {

	@Override
	public void run() {
		new MainFrame().setVisible(true);
	}

	/**
	 * Точка входа.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Application());
	}
}
