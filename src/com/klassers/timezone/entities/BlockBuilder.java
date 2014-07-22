package com.klassers.timezone.entities;
import com.klassers.timezone.Block;
import com.klassers.timezone.CurGame;
import com.klassers.timezone.EntityHurtable;
public class BlockBuilder extends EntityHurtable {
	public Block btc;
	public int owner;
	public int wt = 0;
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
	public BlockBuilder(Block bttc, byte team, int mh, String unbuiltimg){
		btc = bttc;
		owner = team;
		this.model.setModel(unbuiltimg);
		this.maxhealth = mh;
		
	}
	@Override
	public void onTick() {
		this.displayHealth = 300;
		if(CurGame.teams[owner].metal>0){
		CurGame.teams[owner].metal--;
		wt++;
		if(wt == 10){
			wt = 0;
			this.health++;
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
