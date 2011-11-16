package com.gmail.gbmekp.fm;

import com.gmail.gbmekp.fm.engine.LSystem;

public final class Controller {
	private FractalCanvas canvas;
	
	public void setCanvas(FractalCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void paintFractal(LSystem lSystem, int dAlpha, int depth) {
		canvas.paintFractal(lSystem, dAlpha, depth);
	}
}
