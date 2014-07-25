package com.complover116.timezone.entities;

import com.complover116.timezone.Animation;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.ImageContainer;
import com.complover116.timezone.SoundHandler;

public class Sentry extends EntityBuildable {
	boolean turningright;
	boolean enabled;
	int initrot = 0;
	int time;
	Entity target;
	public static String UnbuiltImage = "sentry_1_unbuilt";
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		
	}
	public Sentry(int team) {
		this.buildinghealth = 20;
		this.tph = 1;
		this.costPerHealth = 100;
		this.team = (byte) team;
		anim = new AnimationSet("sentry_1", team);
		anim.animations.add(new Animation("off", 1, 1, -1));
		anim.animations.add(new Animation("toggle", 6, 8, 2));
		anim.animations.add(new Animation("on", 1, 1, -1));
		anim.animations.add(new Animation("alert", 1, 10, 2));
		anim.animations.add(new Animation("shoot", 3, 8, 2));
		anim.animations.add(new Animation("unbuilt", 3, 8, 2));
		this.model.setModel(anim.getFrame());
		this.model.rotX = 7.5;
		this.model.rotY = 15.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.mmaxhealth = 20;
		this.readName = "Sentry gun";
	}
	public Sentry() {
		this.team = 0;
		
	}
	@Override
	public void Think() {
		time++;
		if(this.anim.curAnim == 5){
			
		}else {
		anim.animTick();
		this.model.setModel(anim.getFrame());
		
		checkForTargets();
		if (target == null) {
			if (turningright) {
				this.model.rot += 0.5;
				if (this.model.rot > 180+this.initrot) {
					this.turningright = false;
					SoundHandler.playSound("sentry/seek_1");
				}
			} else {
				this.model.rot -= 0.5;
				if (this.model.rot < 0+this.initrot) {
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
				if(deg - this.model.rot > 180) {
					this.model.rot -= 2;
				}else{
				this.model.rot += 2;
				}
			}
			if (this.model.rot > deg) {
				if(this.model.rot - deg > 180) {
					
					this.model.rot += 2;
				} else {
				this.model.rot -= 2;
				}
			}
			if(this.model.rot > 270) {
				this.model.rot -= 360;
			}
			if(this.model.rot < -90) {
				this.model.rot += 360;
			}
			if (this.model.rot < deg + 3 && this.model.rot > deg - 3) {
				if (time >= 26) {
					//this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 25) {
					Bullet bul = new Bullet(this.team);
					bul.attacker = this;
					bul.setPos(this.getPos());
					bul.direction = this.model.rot;
					CurGame.terra.regEntity(bul);
					anim.setAnim(4);
					//this.model.img = ImageContainer.images
						//	.get("sentry_1_light3");

				}
			} else {
				if (time >= 21) {
					//this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 20) {
					anim.setAnim(3);
					//this.model.img = ImageContainer.images
					//		.get("sentry_1_light2");
					SoundHandler.playSound("sentry/alert_1");
				}
			}
			if (this.target.isDead||this.target.getPos().distance(this.getPos())>800||!this.target.getPos().LOS(this.getPos())) {
				this.target = null;
			}
		}
		}
	}

	private void checkForTargets() {
		boolean flag = false;
		for (int i = 0; i < CurGame.terra.entities.size(); i++) {
			if (CurGame.terra.entities.get(i) instanceof EntityHurtable
					&& CurGame.terra.entities.get(i).getPos()
							.distance(this.getPos()) < 500) {
				if (((EntityHurtable) CurGame.terra.entities.get(i)).team != this.team&&CurGame.terra.entities.get(i).getPos().LOS(this.getPos())) {
					target = CurGame.terra.entities.get(i);
					flag = true;
				}
			}
		}
		if (flag) {
			
		}
	}

	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}
	@Override
	public void onConstructed() {
		// TODO Auto-generated method stub
		
	}

}
