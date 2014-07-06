package com.klassers.timezone.entities;

import com.klassers.timezone.EntityHurtable;
import com.klassers.timezone.ImageContainer;

public class TestTarget extends EntityHurtable{

	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("testtarget");
		this.model.rotX = 8.5;
		this.model.rotY = 8.5;
		this.health = 39;
		this.collideX2 = 16;
		this.collideY2 = 16;
	}

	@Override
	public void onTick() {
		/*this.model.rot ++;
		if(this.model.rot == 360) {
			this.model.rot = 0;
		}*/
	}

}
