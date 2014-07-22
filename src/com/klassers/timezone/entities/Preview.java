package com.klassers.timezone.entities;

import com.klassers.timezone.Entity;

public class Preview extends Entity {

	@Override
	public void construct() {
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
	}

	@Override
	public void onTick() {
		
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}

}
