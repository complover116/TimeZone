package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;

public class Spark1 extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8044495969335914848L;

	@Override
	public int loadFrames() {
		frames.add("spark1_1");
		frames.add("spark1_2");
		frames.add("spark1_3");
		frames.add("spark1_4");
		frames.add("spark1_5");
		this.frametime = 3;
		this.model.rotX = 8.5;
		this.model.rotY = 8.5;
		return 5;
	}
}
