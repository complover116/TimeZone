package com.complover116.timezone.entities;

import com.complover116.timezone.EntityObject;

public class MountPoint extends EntityObject {
	public Sentry mountedEnt;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1911488480536795349L;

	@Override
	public void construct() {
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.model.setModel("nil");
	}

	@Override
	public void onTick() {
		
	}
	public void mountedTick() {
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}

}
