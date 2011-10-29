package com.gmail.gbmekp.fm.engine;

import java.util.Stack;

public class Turtle {
	private final Vector forceVector = new Vector(0, 1);
	private boolean force;
    private final Canvas canvas;
    private State state;
    private Stack<State> memory = new Stack<State>();
    private final double stepLength;
    private final double deltaAngle;

    public Turtle(Canvas canvas, double stepLength, double deltaAngle) {
        this(canvas, stepLength, deltaAngle, false);
    }
    
    public Turtle(Canvas canvas, double stepLength, double deltaAngle, boolean force) {
        this.canvas = canvas;
        this.stepLength = stepLength;
        this.deltaAngle = deltaAngle;
        this.force = force;
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
        
        double newX = oldX + Math.cos(angle) * stepLength;
        double newY = oldY - Math.sin(angle) * stepLength;
        
        Vector h = new Vector(oldX, oldY, newX, newY);
        //System.out.println(h);
        double a = h.angle(forceVector);
//        if (a > Math.PI/2) {
//        	a = Math.PI/2 - a;
//        }
        //System.out.println(a);
        double da = Math.sin(a) * Math.PI / 32;

        System.out.println("da=" + da);
        if (!force) {
        	da = 0;
        }
        newX = oldX + Math.cos(angle + da) * stepLength;
        newY = oldY - Math.sin(angle + da) * stepLength;
        
        canvas.drawLine(oldX, oldY, newX, newY);
        state = new State(newX, newY, angle + da);
        
//        System.out.print(stepLength);
//    	System.out.print(" x="+ oldX);
//    	System.out.println(" y="+oldY);
    }

    private void moveStep() {
        double oldX = state.getX();
        double oldY = state.getY();
        double angle = state.getAngle();
        double newX = oldX + Math.cos(angle) * stepLength;
        double newY = oldY - Math.sin(angle) * stepLength;
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
