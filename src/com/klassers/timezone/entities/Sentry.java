package com.klassers.timezone.entities;
import com.klassers.timezone.*;
public class Sentry extends EntityHurtable{
	boolean turningright;
	int time;
	Entity target;
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.health = 20;
		this.readName = "Sentry gun";
	}

	@Override
	public void onTick() {
		time ++;
		
		if (target == null){
			if(time == 100) {
				this.model.img = ImageContainer.images.get("sentry_1");
				time = 0;
			}
			if(time == 50) {
				this.model.img = ImageContainer.images.get("sentry_1_light1");
				checkForTargets();
			}
		if(turningright) {
			this.model.rot += 0.5;
			if(this.model.rot > 180) {
				this.turningright = false;
				SoundHandler.playSound("sentry/seek_1");
			}
		} else {
			this.model.rot -= 0.5;
			if(this.model.rot < 0) {
				this.turningright = true;
				SoundHandler.playSound("sentry/seek_1");
			}
		}
	} else {
			double deltaX = this.target.getPos().x - this.getPos().x;
			double deltaY = this.target.getPos().y - this.getPos().y;
			double deg = Math.atan2(deltaY,deltaX);
			deg = Math.toDegrees(deg);
			deg += 90;
			if(this.model.rot < deg) {
				this.model.rot += 2;
			}
			if(this.model.rot > deg) {
				this.model.rot -= 2;
			}
			if(this.model.rot < deg + 3&&this.model.rot > deg - 3) {
				if(time >= 10) {
					this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if(time == 5) {
					Bullet bul = new Bullet();
					bul.attacker = this;
					bul.setPos(this.getPos());
					bul.direction = this.model.rot;
					CurGame.terra.regEntity(bul);
					this.model.img = ImageContainer.images.get("sentry_1_light3");
					
				}
			} else {
				if(time >= 20) {
					this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if(time == 10) {
					this.model.img = ImageContainer.images.get("sentry_1_light2");
					SoundHandler.playSound("sentry/alert_1");
				}
			}
			if(this.target.isDead) {
				this.target = null;
			}
	}
	}

	private void checkForTargets() {
		boolean flag = false;
		for(int i = 0; i < CurGame.terra.entities.size(); i ++) {
			if(CurGame.terra.entities.get(i).getClass() == BaseVehicle.class) {
				target = CurGame.terra.entities.get(i);
				flag = true;
			}
		}
		if(flag) {;
		}
	}

	@Override
	public void onDeath() {
		SmallExplosion1 bul = new SmallExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}
	
}
