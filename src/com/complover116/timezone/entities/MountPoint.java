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
		this.collideX = 5;
		this.collideY = 5;
		this.collideX2 = 6;
		this.collideY2 = 6;
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

	public void destroy() {
		this.mountedEnt.onUnMount();
	}

}
