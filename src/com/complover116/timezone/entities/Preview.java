package com.complover116.timezone.entities;

import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityObject;
import com.complover116.timezone.Tool;

public class Preview extends EntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8015537893821177822L;
	public Tool tool;
	@Override
	public void construct() {
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 6;
		this.collideY = 6;
		this.collideX2 = 2;
		this.collideY2 = 2;
	}

	@Override
	public void onTick() {
		
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}

}
