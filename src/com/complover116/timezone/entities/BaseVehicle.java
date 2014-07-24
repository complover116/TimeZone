package com.complover116.timezone.entities;
import com.complover116.timezone.*;
public class BaseVehicle extends EntityControllable {

	@Override
	public void think() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 2;
		this.collideY = 2;
		this.collideX2 = 14;
		this.collideY2 = 14;
		this.health = 100;
		this.readName = "Sentry vehicle";
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}
}
