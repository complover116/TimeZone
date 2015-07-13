package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;
import com.complover116.timezone.SoundHandler;

public class BigExplosion extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1524112459262597901L;

	@Override
	public int loadFrames() {
		SoundHandler.playSound("explosion/big_1");
		frames.add("bigexplosion1_1");
		frames.add("bigexplosion1_2");
		frames.add("bigexplosion1_3");
		frames.add("bigexplosion1_4");
		frames.add("bigexplosion1_5");
		this.frametime = 20;
		this.model.rotX = 32;
		this.model.rotY = 32;
		return 5;
	}

}
