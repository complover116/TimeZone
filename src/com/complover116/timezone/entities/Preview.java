package com.complover116.timezone.entities;

import com.complover116.timezone.Entity;
import com.complover116.timezone.Tool;

public class Preview extends Entity {
	public Tool tool;
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
