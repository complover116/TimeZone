package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.EntityObject;
import com.complover116.timezone.Tool;

public class Preview extends EntityObject {
	public int entSwitch = 0;
	public EntityHurtable selent;
	public boolean entLocked = false;
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
	public EntityHurtable getEnt() {
		EntityHurtable ent2 = null;
		double bestdist = 300;
		for(Entity ent:CurGame.c.terra.entities) {
			if(ent instanceof EntityHurtable) {
				if(((EntityHurtable)ent).team == CurGame.c.controllingTeam&&((EntityHurtable)ent).checkCollision(CurGame.c.terra.preview)) {
					if(ent.getPos().distance(this.getPos())<bestdist) {
						bestdist = ent.getPos().distance(this.getPos());
						ent2 = (EntityHurtable) ent;
					}
				}
			}
		}
		return ent2;
	}
	public void updateEnt() {
		if(!entLocked){
			this.selent = getEnt();
		}
	}
	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}

}
