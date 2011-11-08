package com.gmail.gbmarkovsky.fm.jogl.engine;

import java.util.Stack;

import com.gmail.gbmarkovsky.fm.jogl.SimpleRenderer;

public class Turtle {
    private final SimpleRenderer canvas;
    private State state;
    private Stack<State> memory = new Stack<State>();
    private final double stepLength;
    private final double deltaAngle;

    public Turtle(SimpleRenderer canvas, double stepLength, double deltaAngle) {
        this.canvas = canvas;
        this.stepLength = stepLength;
        this.deltaAngle = deltaAngle;
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
