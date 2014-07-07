package com.klassers.timezone.entities;

import com.klassers.timezone.Effect;
import com.klassers.timezone.ImageContainer;

public class SmallExplosion1 extends Effect {
	@Override
	public int loadFrames() {
		frames.add(ImageContainer.images.get("smallexplosion1_1"));
		frames.add(ImageContainer.images.get("smallexplosion1_2"));
		frames.add(ImageContainer.images.get("smallexplosion1_3"));
		frames.add(ImageContainer.images.get("smallexplosion1_4"));
		frames.add(ImageContainer.images.get("smallexplosion1_5"));
		return 5;
	}

}
