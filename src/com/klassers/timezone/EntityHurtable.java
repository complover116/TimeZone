package com.klassers.timezone;

public abstract class EntityHurtable extends EntityObject {
	public int health;
	public String readName;
	public int displayHealth;
	public void takeDamage(Entity attacker, int damage) {
		this.health = this.health - damage;
		
		if(this.health < 0){
			this.onDeath();
			this.remove();
		}
	}
	public void renderInfo2() {
		
	}
	public abstract void onDeath();
}
