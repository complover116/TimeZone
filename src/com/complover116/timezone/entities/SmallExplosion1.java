package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;
import com.complover116.timezone.ImageContainer;
import com.complover116.timezone.SoundHandler;

public class SmallExplosion1 extends Effect {
	@Override
	public int loadFrames() {
		SoundHandler.playSound("explosion/small_1");
		frames.add(ImageContainer.images.get("smallexplosion1_1"));
		frames.add(ImageContainer.images.get("smallexplosion1_2"));
		frames.add(ImageContainer.images.get("smallexplosion1_3"));
		frames.add(ImageContainer.images.get("smallexplosion1_4"));
		frames.add(ImageContainer.images.get("smallexplosion1_5"));
		return 5;
	}
}
