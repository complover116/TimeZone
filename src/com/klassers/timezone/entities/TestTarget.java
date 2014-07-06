package com.klassers.timezone.entities;

import com.klassers.timezone.Entity;
import com.klassers.timezone.ImageContainer;

public class TestTarget extends Entity{

	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("testtarget");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
	}

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

}
