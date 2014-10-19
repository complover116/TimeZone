package com.complover116.timezone.entities;

import java.util.ArrayList;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.EntityObject;
import com.complover116.timezone.Pos;
import com.complover116.timezone.Tool;

public class Preview extends EntityObject {
	public int entSwitch = 0;
	public int entsAvail = 0;
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
		ArrayList<EntityHurtable> ent2 = new ArrayList<EntityHurtable>();
		for(Entity ent:CurGame.c.terra.entities) {
			if(ent instanceof EntityHurtable) {
				if(((EntityHurtable)ent).team == CurGame.c.controllingTeam&&((EntityHurtable)ent).checkCollision(CurGame.c.terra.preview)) {
					ent2.add(((EntityHurtable)ent));
				}
			}
		}
		entsAvail = ent2.size();
		if(entSwitch>entsAvail - 1) {
			entSwitch = entsAvail - 1;
		}
		if(entSwitch<0) {
			entSwitch = 0;
		}
		if(entsAvail > 0){
		return ent2.get(entSwitch);
		} else {
			return null;
		}
	}
	public void updateEnt() {
		if(!entLocked){
			this.selent = getEnt();
			if(this.selent != null) this.selent.displayHealth = 500;
		}
	}
	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}
	@Override
	public void setPos(Pos pos) {
		super.setPos(pos);
		if(tool!= null)tool.setPos(pos);
	}
}
