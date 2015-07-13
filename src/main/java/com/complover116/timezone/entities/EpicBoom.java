package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.Pos;

public class EpicBoom extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -457560272903255732L;
	int timepassed = 0;
	int height;
	int width;
	int time;
	int speed;
	@Override
	public void construct() {
		this.model.setModel("nil");
	}
	public EpicBoom() {
		this.model.rotX = 0;
		this.model.rotY = 0;
	}

	@Override
	public void onTick() {
		timepassed ++;
		if(timepassed%50==0) {
			MultiExplosion ex = new MultiExplosion();
			ex.setPos(this.getPos().add(new Pos(Math.random()*40 - 24,Math.random()*40 - 24)));
			CurGame.c.terra.regEntity(ex);
		}
		if(timepassed>398) {
			this.remove();
			BigExplosion ex = new BigExplosion();
			ex.setPos(this.getPos());
			CurGame.c.terra.regEntity(ex);
		}
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub
		
	}

}
