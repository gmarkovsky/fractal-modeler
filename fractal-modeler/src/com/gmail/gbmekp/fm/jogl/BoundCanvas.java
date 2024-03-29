package com.gmail.gbmekp.fm.jogl;

import java.awt.geom.Rectangle2D;

public class BoundCanvas implements LineCanvas {
    private double maxY = Double.NEGATIVE_INFINITY;
    private double minY = Double.POSITIVE_INFINITY;
    private double maxX = Double.NEGATIVE_INFINITY;
    private double minX = Double.POSITIVE_INFINITY;
    
    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        if (y1 > maxY) {
            maxY = y1;
        }
        if (y2 > maxY) {
            maxY = y2;
        }
        if (y1 < minY) {
            minY = y1;
        }
        if (y2 < minY) {
            minY = y2;
        }
        if (x1 > maxX) {
            maxX = x1;
        }
        if (x2 > maxX) {
            maxX = x2;
        }
        if (x1 < minX) {
            minX = x1;
        }
        if (x2 < minX) {
            minX = x2;
        }
    }
    
    public Rectangle2D getBounds() {
        Rectangle2D.Double double1 = new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
		return double1;
    }
}
