package com.gmail.gbmarkovsky.fm.jogl;

import javax.swing.SwingUtilities;

public class FMApplication {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}
