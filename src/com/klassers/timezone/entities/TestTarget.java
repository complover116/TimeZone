package com.klassers.timezone.entities;

import com.klassers.timezone.CurGame;
import com.klassers.timezone.EntityHurtable;
import com.klassers.timezone.ImageContainer;
import com.klassers.timezone.Pos;

public class TestTarget extends EntityHurtable{
	int time = 0;
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
		time ++;
		if(time > 50) {
		this.setPos(new Pos(Math.random()*500,Math.random()*500));
		time = 0;
		}
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}
}
