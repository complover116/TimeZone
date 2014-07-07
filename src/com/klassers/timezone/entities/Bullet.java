package com.klassers.timezone.entities;
import com.klassers.timezone.*;
public class Bullet extends EntityObject{
	int time;
	double direction;
	double speed = 4;
	Entity attacker;
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1_bullet");
		this.model.rotX = 3.5;
		this.model.rotY = 3.5;
		this.collideX2 = 7;
		this.collideY2 = 7;
		direction = 0;
		this.model.rot = direction;
	}

	@Override
	public void onTick() {
		double newX = this.model.x + Math.cos(Math.toRadians(direction-90))*speed;
		double newY = this.model.y + Math.sin(Math.toRadians(direction-90))*speed;
		this.model.x = newX;
		this.model.y = newY;
		if(this.model.x > 500||this.model.y > 500||this.model.x < 0||this.model.y < 0) {
			this.remove();
		}
		for(int i = 0; i < CurGame.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.terra.entities.get(i))) {
				if(CurGame.terra.entities.get(i)!=attacker){
					if(CurGame.terra.entities.get(i)!=this){
						if(((EntityObject)CurGame.terra.entities.get(i)).checkCollision(this)){
							this.remove();
							((EntityHurtable) CurGame.terra.entities.get(i)).takeDamage(attacker, 10);
							SmallExplosion1 bul = new SmallExplosion1();
							bul.setPos(this.getPos());
							CurGame.terra.regEntity(bul);
						}
					}
				}
			}
		}
	}
	
}
