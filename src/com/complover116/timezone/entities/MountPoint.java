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
		this.collideX = 7;
		this.collideY = 7;
		this.collideX2 = 1;
		this.collideY2 = 1;
		this.model.setModel("railmount_blue");
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
		if(this.mountedEnt != null){
		this.mountedEnt.onUnMount();
		}
	}

}
