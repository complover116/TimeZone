package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.EntityObject;
import com.complover116.timezone.Pos;

public class Teleporter extends EntityObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4965174732435002669L;

	@Override
	public void construct() {
		this.model.img = "teleporter";
		this.model.rotX = 16;
		this.model.rotY = 16;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 32;
		this.collideY2 = 32;
	}

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		for(int i = 0; i < CurGame.c.terra.entities.size();i++) {
			if(CurGame.c.terra.entities.get(i) instanceof EntityHurtable){
				EntityHurtable e = (EntityHurtable)CurGame.c.terra.entities.get(i);
				if(e.checkCollision(this)) {
					byte tm;
					if(CurGame.c.terra.owner == 0) {
						tm = 1;
					} else {
						tm = 0;
					}
					for(int j = 0; j < 10; j ++) {
					Spark1 spark = new Spark1();
					spark.setPos(e.getPos().add(new Pos(Math.random()*16 - 8,Math.random()*16 - 8)));
					CurGame.c.terra.regEntity(spark);
					}
					e.setPos(new Pos(64+Math.random()*32,64+Math.random()*32));
					e.model.rot += 180;
					if(e.model.rot > 360) {
						e.model.rot -= 360;
					}
					e.orders.clear();
					CurGame.c.teams[tm].zone.regEntity(e);
					CurGame.c.terra.delEnt(e);
					for(int j = 0; j < 10; j ++) {
						Spark1 spark = new Spark1();
						spark.setPos(e.getPos().add(new Pos(Math.random()*16 - 8,Math.random()*16 - 8)));
						CurGame.c.teams[tm].zone.regEntity(spark);
					}
					
				}
			}
		}
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}

}
