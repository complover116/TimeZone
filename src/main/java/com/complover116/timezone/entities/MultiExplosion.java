package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.Pos;

public class MultiExplosion extends Entity {
	public int tp = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4214809282238660318L;

	public MultiExplosion() {
		
	}

	@Override
	public void construct() {
		// TODO Auto-generated method stub
		this.model.setModel("nil");
	}

	@Override
	public void onTick() {
		if(tp == 0 ){
			MedExplosion1 bul = new MedExplosion1();
			bul.setPos(this.getPos());
			CurGame.c.terra.regEntity(bul);
		}
		tp ++;
		if(tp%5 == 0) {
			SmallExplosion1 bul = new SmallExplosion1();
			bul.setPos(this.getPos().add(new Pos(Math.random()*32 - 16,Math.random()*32 - 16)));
			CurGame.c.terra.regEntity(bul);
		}
		if(tp > 100) {
			this.remove();
		}
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub
		
	}
}
