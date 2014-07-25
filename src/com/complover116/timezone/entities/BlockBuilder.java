package com.complover116.timezone.entities;
import com.complover116.timezone.Block;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityHurtable;
public class BlockBuilder extends EntityHurtable {
	public Block btc;
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
		this.readName = "Wall (In construction)";
	}
	public BlockBuilder(Block bttc, byte team, int mh, String unbuiltimg, int cst){
		btc = bttc;
		owner = team;
		this.team = (byte) owner;
		cost = cst;
		this.model.setModel(unbuiltimg);
		this.maxhealth = mh;
		this.unbuiltim = unbuiltimg;
		
	}
	public BlockBuilder copy() {
		BlockBuilder build = null;
		try {
			build = new BlockBuilder(btc.getClass().newInstance(), (byte) owner, maxhealth, unbuiltim, cost);
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
				CurGame.terra.terrain[(int) (this.getPos().x/16)]
				[(int) (this.getPos().y/16)] = btc;
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
