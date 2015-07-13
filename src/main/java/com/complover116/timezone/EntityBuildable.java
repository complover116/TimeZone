package com.complover116.timezone;

import com.complover116.timezone.entities.Spark1;

public abstract class EntityBuildable extends EntityHurtable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3258498086326269581L;
	public int costPerHealth;
	public int buildinghealth;
	public int tph;
	public int timePassed = 0;
	public int metalUsed = 0;
	public boolean constructed = false;
	public abstract void onConstructed();
	public abstract void Think();
	@Override
	public void onTick() {
		if(constructed) {
			Think();
		} else {
			this.displayHealth = 300;
			this.maxhealth = this.buildinghealth;
			this.model.setModel(this.anim.getFrame("unbuilt", 0));
			timePassed++;
			if(timePassed >= tph&&CurGame.c.teams[this.team].metal>0) {
				CurGame.c.teams[this.team].metal--;
				metalUsed++;
				if(Math.random()*10 > 8){
				Spark1 spark = new Spark1();
				spark.setPos(this.getPos().add(new Pos(Math.random()*16 - 16,Math.random()*16 - 16)));
				CurGame.c.terra.regEntity(spark);
				}
				if(metalUsed==costPerHealth) {
					health++;
					
					if(health==buildinghealth){
						this.constructed = true;
						this.health = this.mmaxhealth;
						this.maxhealth = this.mmaxhealth;
						this.onConstructed();
					}
					metalUsed = 0;
				}
				timePassed = 0;
			}
		}
	}
}
