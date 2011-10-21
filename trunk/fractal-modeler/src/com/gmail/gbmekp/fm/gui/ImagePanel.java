package com.gmail.gbmekp.fm.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
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
}
