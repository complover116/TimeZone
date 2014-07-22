package com.klassers.timezone.entities;
import com.klassers.timezone.Block;
import com.klassers.timezone.CurGame;
import com.klassers.timezone.EntityHurtable;
public class Constructor extends EntityHurtable {
	public EntityHurtable btc;
	public int owner;
	public int wt = 0;
	public String unbuiltim;
	public int cost = 1;
	@Override
	public void construct() {
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.health = 1;
		this.readName = "Object (In construction)";
	}
	public Constructor(EntityHurtable bttc, byte team, String ubm, int cst){
		btc = bttc;
		owner = team;
		cost = cst;
		this.model.setModel(ubm);
		this.maxhealth = btc.maxhealth;
		this.unbuiltim = ubm;
		
	}
	public Constructor copy() {
		Constructor build = null;
		try {
			build = new Constructor(btc.getClass().newInstance(), (byte) owner, unbuiltim, cost);
			build.setPos(this.getPos());
			return build;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build;
		
	}
	public boolean place() {
		CurGame.terra.regEntity(copy());
		return true;
	}
	@Override
	public void onTick() {
		this.displayHealth = 300;
		if(CurGame.teams[owner].metal>0){
		
		wt++;
		if(wt == 100){
			wt = 0;
			this.health++;
			CurGame.teams[owner].metal-= cost;
			if(this.health==this.maxhealth) {
				this.remove();
			}
		}
		}
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
