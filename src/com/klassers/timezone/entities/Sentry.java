package com.klassers.timezone.entities;

import com.klassers.timezone.CurGame;
import com.klassers.timezone.Entity;
import com.klassers.timezone.EntityHurtable;
import com.klassers.timezone.ImageContainer;
import com.klassers.timezone.SoundHandler;

public class Sentry extends EntityHurtable {
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
		time++;

		if (target == null) {
			if (time == 100) {
				this.model.img = ImageContainer.images.get("sentry_1");
				time = 0;
			}
			if (time == 50) {
				this.model.img = ImageContainer.images.get("sentry_1_light1");
				checkForTargets();
			}
			if (turningright) {
				this.model.rot += 0.5;
				if (this.model.rot > 180) {
					this.turningright = false;
					SoundHandler.playSound("sentry/seek_1");
				}
			} else {
				this.model.rot -= 0.5;
				if (this.model.rot < 0) {
					this.turningright = true;
					SoundHandler.playSound("sentry/seek_1");
				}
			}
		} else {
			double deltaX = this.target.getPos().x - this.getPos().x;
			double deltaY = this.target.getPos().y - this.getPos().y;
			double deg = Math.atan2(deltaY, deltaX);
			deg = Math.toDegrees(deg);
			deg += 90;
			if (this.model.rot < deg) {
				this.model.rot += 2;
			}
			if (this.model.rot > deg) {
				this.model.rot -= 2;
			}
			if (this.model.rot < deg + 3 && this.model.rot > deg - 3) {
				if (time >= 10) {
					this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 5) {
					Bullet bul = new Bullet();
					bul.attacker = this;
					bul.setPos(this.getPos());
					bul.direction = this.model.rot;
					CurGame.terra.regEntity(bul);
					this.model.img = ImageContainer.images
							.get("sentry_1_light3");

				}
			} else {
				if (time >= 20) {
					this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 10) {
					this.model.img = ImageContainer.images
							.get("sentry_1_light2");
					SoundHandler.playSound("sentry/alert_1");
				}
			}
			if (this.target.isDead||this.target.getPos().distance(this.getPos())>800) {
				this.target = null;
			}
		}
	}

	private void checkForTargets() {
		boolean flag = false;
		for (int i = 0; i < CurGame.terra.entities.size(); i++) {
			if (CurGame.terra.entities.get(i) instanceof EntityHurtable
					&& CurGame.terra.entities.get(i).getPos()
							.distance(this.getPos()) < 500) {
				if (((EntityHurtable) CurGame.terra.entities.get(i)).team != this.team) {
					target = CurGame.terra.entities.get(i);
					flag = true;
				}
			}
		}
		if (flag) {
			;
		}
	}

	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}

}
