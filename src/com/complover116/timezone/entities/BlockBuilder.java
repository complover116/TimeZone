package com.complover116.timezone.entities;
import com.complover116.timezone.Block;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityHurtable;
public class BlockBuilder extends EntityHurtable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4390913300812350489L;
	public Block btc;
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
		this.health = 0;
		this.readName = "Wall (In construction)";
	}
	public BlockBuilder() {
		
	}
	public BlockBuilder(Block bttc, byte team, int mh, String unbuiltimg, int cst){
		btc = bttc;
		this.team = team;
		cost = cst;
		this.model.setModel(unbuiltimg);
		this.maxhealth = mh;
		this.unbuiltim = unbuiltimg;
		
	}
	public BlockBuilder copy() {
		BlockBuilder build = null;
			build = new BlockBuilder(btc, (byte) team, maxhealth, unbuiltim, cost);
			build.setPos(this.getPos());
			return build;
		
	}
	public boolean place() {
		CurGame.c.terra.regEntity(copy());
		return true;
	}
	@Override
	public void onTick() {
		this.displayHealth = 300;
		if(CurGame.c.teams[team].metal>0){
		
		wt++;
		if(wt == 200){
			wt = 0;
			this.health++;
			CurGame.c.teams[team].metal -= cost;
			if(this.health==this.maxhealth) {
				CurGame.c.terra.terrain[(int) (this.getPos().x/16)]
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
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.c.terra.regEntity(bul);
	}

}
