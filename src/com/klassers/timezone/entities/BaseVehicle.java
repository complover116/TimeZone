package com.klassers.timezone.entities;
import com.klassers.timezone.*;
public class BaseVehicle extends EntityControllable {

	@Override
	public void think() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.health = 20;
		this.readName = "Sentry vehicle";
	}

}
