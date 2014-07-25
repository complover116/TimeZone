package com.complover116.timezone.entities;

import java.util.ArrayList;

import com.complover116.timezone.Animation;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.Mountable;
import com.complover116.timezone.Order;
import com.complover116.timezone.SoundHandler;

public class Sentry extends EntityBuildable implements Mountable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2064129235871367738L;
	boolean turningright;
	boolean enabled;
	int initrot = 0;
	int time;
	Entity target;
	MountPoint mountedTo = null;
	@Override
	public void construct() {
		this.model.img = "sentry_1";
		
	}
	public Sentry(int team) {
		this.buildinghealth = 10;
		this.tph = 8;
		this.costPerHealth = 25;
		this.team = (byte) team;
		anim = new AnimationSet("sentry_1", team);
		anim.animations.add(new Animation("off", 1, 1, -1));
		anim.animations.add(new Animation("open", 6, 8, 3));
		anim.animations.add(new Animation("close", 6, 8, 0));
		anim.animations.add(new Animation("on", 1, 1, -1));
		anim.animations.add(new Animation("alert", 1, 10, 3));
		anim.animations.add(new Animation("shoot", 3, 8, 3));
		anim.animations.add(new Animation("unbuilt", 3, 8, -1));
		this.model.setModel(anim.getFrame());
		this.model.rotX = 7.5;
		this.model.rotY = 15.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.mmaxhealth = 20;
		this.readName = "Basic Sentry Gun";
	}
	public Sentry() {
		this.team = 0;
		
	}
	@Override
	public void Think() {
		time++;
		anim.animTick();
		this.model.setModel(anim.getFrame());
		if(this.orders.size() > 0) {
			if(this.anim.curAnim == 0) {
			Order curorder = this.orders.get(0);
			switch(curorder.type) {
			case 1:
				if(this.getPos().SentryLOM(curorder.pos)) {
					this.setPos(this.getPos().add2((curorder.pos.sub(this.getPos())).normal()));
					if(this.getPos().distance(curorder.pos) < 2) {
						this.setPos(curorder.pos);
						this.orders.remove(0);
						System.out.println("Order executed successfully");
						for(Entity ent:CurGame.c.terra.entities) {
							if(ent instanceof MountPoint) {
								if(((MountPoint)ent).checkCollision(this)) {
									((MountPoint)ent).mountedEnt = this;
									this.setPos(((MountPoint)ent).getPos());
									this.onMount();
									this.mountedTo = ((MountPoint)ent);
								}
							}
						}
					}
				} else {
					this.orders.remove(0);
					System.out.println("Impossible order");
				}
			break;
			default:
				this.orders.remove(0);
				System.out.println("Invalid order");
			break;
			}
		} else {
			if(this.anim.curAnim > 2) {
				this.onUnMount();
				this.mountedTo.mountedEnt = null;
			}
		}
		}
		if(this.anim.curAnim < 3){
			
		}else {
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
			
			
			if(this.model.rot > 270) {
				this.model.rot -= 360;
			}
			if(this.model.rot < -90) {
				this.model.rot += 360;
			}
			if (this.model.rot < deg + 3 && this.model.rot > deg - 3) {
				if (this.model.rot < deg) {
					if(deg - this.model.rot > 180) {
						this.model.rot --;
					}else{
					this.model.rot ++;
					}
				}
				if (this.model.rot > deg) {
					if(this.model.rot - deg > 180) {
						
						this.model.rot ++;
					} else {
					this.model.rot --;
					}
				}
				if (time >= 26) {
					//this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 25) {
					Bullet bul = new Bullet(this.team);
					bul.attacker = this;
					bul.setPos(this.getPos());
					bul.direction = this.model.rot;
					CurGame.c.terra.regEntity(bul);
					anim.setAnim(4);
					//this.model.img = ImageContainer.images
						//	.get("sentry_1_light3");

				}
			} else {
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
		for (int i = 0; i < CurGame.c.terra.entities.size(); i++) {
			if (CurGame.c.terra.entities.get(i) instanceof EntityHurtable
					&& CurGame.c.terra.entities.get(i).getPos()
							.distance(this.getPos()) < 500) {
				if (((EntityHurtable) CurGame.c.terra.entities.get(i)).team != this.team&&CurGame.c.terra.entities.get(i).getPos().LOS(this.getPos())) {
					target = CurGame.c.terra.entities.get(i);
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
		CurGame.c.terra.regEntity(bul);
	}
	@Override
	public void onConstructed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMount() {
		this.anim.setAnim(1);
	}
	@Override
	public void onUnMount() {
		this.anim.setAnim(2);
	}

}
