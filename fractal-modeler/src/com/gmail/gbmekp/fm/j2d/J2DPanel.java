package com.gmail.gbmekp.fm.j2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.gmail.gbmekp.fm.FractalCanvas;
import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.Vector;

public class J2DPanel extends JPanel implements FractalCanvas {
    private static final long serialVersionUID = -728123946092154157L;

    private Image image;
    
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D G = (Graphics2D) g;
        if (image != null) {
            G.drawImage(image, 0, 0, null);
        } else {
        	G.setColor(Color.white);
        	G.fillRect(0, 0, getWidth(), getHeight());
        }
    }

	@Override
	public void paintFractal(LSystem lSystem, int dAplha, int depth, boolean force, Vector vector) {
		setImage(Painter.draw(getWidth(), getHeight(), 0, lSystem, dAplha * Math.PI / 180, depth));
		repaint();
	}
}
