package com.klassers.timezone.entities;
import com.klassers.timezone.CurGame;
import com.klassers.timezone.EntityHurtable;
import com.klassers.timezone.Pos;
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
	public Constructor(EntityHurtable bttc, byte team, int cst){
		btc = bttc;
		owner = team;
		cost = cst;
		this.model.setModel(btc.anim.getFrame("unbuilt", 0));
		this.maxhealth = btc.maxhealth;
		//this.unbuiltim = ubm;
		
	}
	public Constructor copy() {
		Constructor build = null;
		try {
			EntityHurtable soos = btc.getClass().newInstance();
			soos.instantiate((byte)owner);
			build = new Constructor(soos, (byte) owner, cost);
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
			Spark1 spark = new Spark1();
			spark.setPos(this.getPos().add(new Pos(Math.random()*10-5,Math.random()*10-5)));
			CurGame.terra.regEntity(spark);
			CurGame.teams[owner].metal-= cost;
			if(this.health==this.maxhealth) {
				this.remove();
				btc.setPos(this.getPos());
				btc.model.rot= this.model.rot;
				CurGame.terra.regEntity(btc);
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
