package com.klassers.timezone;

public abstract class EntityHurtable extends EntityObject {
	public int health;
	public void takeDamage(Entity attacker, int damage) {
		this.health = this.health - damage;
		if(this.health < 0){
			this.remove();
		}
	}
}
