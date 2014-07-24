package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;
import com.complover116.timezone.ImageContainer;

public class Spark1 extends Effect {
	@Override
	public int loadFrames() {
		frames.add(ImageContainer.images.get("spark1_1"));
		frames.add(ImageContainer.images.get("spark1_2"));
		frames.add(ImageContainer.images.get("spark1_3"));
		frames.add(ImageContainer.images.get("spark1_4"));
		frames.add(ImageContainer.images.get("spark1_5"));
		this.frametime = 3;
		return 5;
	}
}
