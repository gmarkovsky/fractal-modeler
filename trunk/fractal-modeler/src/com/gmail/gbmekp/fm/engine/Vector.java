package com.gmail.gbmekp.fm.engine;

import java.awt.geom.Point2D;

public class Vector {
	private final Point2D point;
	
	public Vector() {
		point = new Point2D.Double(0, 1);
	}
	
	public Vector(double x, double y) {
		this(new Point2D.Double(x, y));
	}
	
	public Vector(Point2D point) {
		this.point = point;
	}
	
	public Vector(double x0, double y0, double x, double y) {
		this(new Point2D.Double(x0, y0), new Point2D.Double(x, y));
	}
	
	public Vector(Point2D start, Point2D end) {
		double x = end.getX() - start.getX();
		double y = end.getY() - start.getY();
		point = new Point2D.Double(x, y);
	}

	public Point2D getPoint() {
		return point;
	}
	
	public double getX() {
		return point.getX();
	}
	
	public double getY() {
		return point.getY();
	}
	
	public double modul() {
		return Math.sqrt(point.getX() * point.getX()
				+ point.getY() * point.getY());
	}
	
	public double dot(Vector v) {
		return v.getX() * getX() + v.getY() * getY();
	}
	
	public double dot() {
		return getX() * getX() + getY() * getY();
	}
	
	public double angle(Vector v) {
		double a = dot(v) / Math.sqrt(dot() * v.dot());
		double acos = Math.acos(a);
		return acos;
	}
	
	@Override
	public String toString() {
		return "(" + point.getX() + ", " + point.getY() + ")";
	}
}
