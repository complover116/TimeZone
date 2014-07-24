package com.complover116.timezone.entities;

import com.complover116.timezone.Effect;
import com.complover116.timezone.ImageContainer;
import com.complover116.timezone.SoundHandler;

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
