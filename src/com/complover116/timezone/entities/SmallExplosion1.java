package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;
import com.complover116.timezone.SoundHandler;

public class SmallExplosion1 extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6487986027599226375L;

	@Override
	public int loadFrames() {
		SoundHandler.playSound("explosion/small_1");
		frames.add("smallexplosion1_1");
		frames.add("smallexplosion1_2");
		frames.add("smallexplosion1_3");
		frames.add("smallexplosion1_4");
		frames.add("smallexplosion1_5");
		return 5;
	}
}
