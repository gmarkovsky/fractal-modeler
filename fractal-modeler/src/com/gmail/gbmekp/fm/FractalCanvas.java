package com.gmail.gbmekp.fm;

import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.Vector;

public interface FractalCanvas {
	void paintFractal(LSystem lSystem, int dAplha, int depth, boolean force, Vector vector);
}
