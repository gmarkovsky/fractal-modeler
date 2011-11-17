package com.gmail.gbmekp.fm.jogl;

import java.util.Stack;

import com.gmail.gbmekp.fm.engine.State;
import com.gmail.gbmekp.fm.engine.Vector;

public class Turtle {
	private Vector g = new Vector(0, -1);
	private boolean force;
    private final LineCanvas canvas;
    private State state;
    private Stack<State> memory = new Stack<State>();
    private final double stepLength;
    private final double deltaAngle;

    public Turtle(LineCanvas canvas, double stepLength, double deltaAngle) {
        this(canvas, stepLength, deltaAngle, false, new Vector(1, 1));
    }
    
    public Turtle(LineCanvas canvas, double stepLength, double deltaAngle, boolean force, Vector vector) {
        this.canvas = canvas;
        this.stepLength = stepLength;
        this.deltaAngle = deltaAngle;
        this.force = force;
        this.g = vector;
    }
    
    public void draw(String string, State first) {
        state = first;
        for (int i = 0; i < string.length(); ++i) {
            switch (string.charAt(i)) {
                case 'F':
                    drawStep();
                    break;
                case 'b':
                    moveStep();
                    break;
                case '+':
                    rotateRight();
                    break;
                case '-':
                    rotateLeft();
                    break;
                case '[':
                    pushState();
                    break;
                case ']':
                    popState();
                    break;
                default:
                    break;
            }
        }
    }

    private void drawStep() {
        double oldX = state.getX();
        double oldY = state.getY();
        double angle = state.getAngle();
        
        double newX = oldX + Math.sin(angle) * stepLength;
        double newY = oldY + Math.cos(angle) * stepLength;
        
        Vector v = new Vector(newX, newY);
        
        double alpha = v.angle(g);
        
        double da = Math.sin(alpha) * Math.PI / 16 * g.modul();
        
        newX = oldX + Math.sin(angle - da) * stepLength;
        newY = oldY + Math.cos(angle - da) * stepLength;
        
        Vector v1 = new Vector(newX, newY);
        
        newX = oldX + Math.sin(angle + da) * stepLength;
        newY = oldY + Math.cos(angle + da) * stepLength;
        
        Vector v2 = new Vector(newX, newY);
        
        if (v1.angle(g) < v2.angle(g)) {
        	da = - da;
        }
        
        if (!force) {
        	da = 0;
        }
        
        newX = oldX + Math.sin(angle + da) * stepLength;
        newY = oldY + Math.cos(angle + da) * stepLength;
        
        canvas.drawLine(oldX, oldY, newX, newY);
        state = new State(newX, newY, angle);
    }

    private void moveStep() {
        double oldX = state.getX();
        double oldY = state.getY();
        double angle = state.getAngle();
        double newX = oldX + Math.sin(angle) * stepLength;
        double newY = oldY + Math.cos(angle) * stepLength;
        state = new State(newX, newY, angle);
    }

    private void rotateRight() {
        double x = state.getX();
        double y = state.getY();
        double oldAngle = state.getAngle();
        double newAngle = oldAngle - deltaAngle;
        state = new State(x, y, newAngle);
    }

    private void rotateLeft() {
        double x = state.getX();
        double y = state.getY();
        double oldAngle = state.getAngle();
        double newAngle = oldAngle + deltaAngle;
        state = new State(x, y, newAngle);
    }

    private void pushState() {
        memory.push(state);
    }

    private void popState() {
        state = memory.lastElement();
        memory.pop();
    }
}
