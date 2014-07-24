package com.complover116.timezone;

public abstract class EntityBuildable extends EntityHurtable {
	public int costPerHealth;
	public int buildinghealth;
	public int tph;
	public int timePassed;
	public int metalUsed;
	public boolean constructed;
	public abstract void onConstructed();
	public abstract void Think();
	@Override
	public void onTick() {
		if(constructed) {
			Think();
		} else {
			timePassed++;
			if(timePassed > tph&&CurGame.teams[this.team].metal>0) {
				metalUsed++;
				if(metalUsed>costPerHealth) {
					health++;
					metalUsed = 0;
				}
				timePassed = 0;
			}
		}
	}
}
