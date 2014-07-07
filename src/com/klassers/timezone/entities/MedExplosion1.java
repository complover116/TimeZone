package com.klassers.timezone.entities;

import com.klassers.timezone.Effect;
import com.klassers.timezone.ImageContainer;
import com.klassers.timezone.SoundHandler;

public class MedExplosion1 extends Effect {
	@Override
	public int loadFrames() {
		SoundHandler.playSound("explosion/med_1");
		frames.add(ImageContainer.images.get("medexplosion1_1"));
		frames.add(ImageContainer.images.get("medexplosion1_2"));
		frames.add(ImageContainer.images.get("medexplosion1_3"));
		frames.add(ImageContainer.images.get("medexplosion1_4"));
		frames.add(ImageContainer.images.get("medexplosion1_5"));
		this.frametime = 5;
		this.model.rotX = 16.5;
		this.model.rotY = 16.5;
		return 5;
	}

}
