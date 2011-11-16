package com.gmail.gbmekp.fm.j2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.gmail.gbmekp.fm.FractalCanvas;
import com.gmail.gbmekp.fm.engine.LSystem;

public class ImagePanel extends JPanel implements FractalCanvas {
    private static final long serialVersionUID = -728123946092154157L;

    private Image image;
    
    public ImagePanel() {

    }
    
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D G = (Graphics2D) g;
        if (image != null) {
            G.drawImage(image, 0, 0, null);
        }
    }

	@Override
	public void paintFractal(LSystem lSystem, int dAplha, int depth) {
		setImage(Painter.draw(getWidth(), getHeight(), 0, lSystem, dAplha, depth));
		repaint();
	}
}
