package com.complover116.timezone.entities;

import com.complover116.timezone.Animation;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.Block;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.Path;
import com.complover116.timezone.Pathfinding;
import com.complover116.timezone.SoundHandler;

public class Sentry extends EntityBuildable {
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
	Path p2;
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
		this.model.onTop = true;
		this.collideX = 0;
		this.collideY = 8;
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
		if(this.mountedTo == null) {
			if(!(CurGame.c.terra.terrain[(int)this.getPos().x/16][(int)this.getPos().y/16] == Block.RAIL)) {
				if(Math.random()*10 > 9) {
				this.takeDamage(null, 1);
				}
			}
		} else {
			this.setPos(this.mountedTo.getPos());
		}
		time++;
		anim.animTick();
		this.model.setModel(anim.getFrame());
		if(this.p2!=null&&this.p2.path.size()>0) {
			
			
			if(this.anim.curAnim == 0) {
					this.setPos(this.getPos().add2((this.p2.path.get(0).sub(this.getPos())).normal()));
					if(this.getPos().distance(this.p2.path.get(0)) < 2) {
						this.setPos(this.p2.path.get(0));
						this.p2.path.remove(0);
						//System.out.println("Order executed successfully");
						for(Entity ent:CurGame.c.terra.entities) {
							if(ent instanceof MountPoint) {
								//System.out.println("MP EXISTS");
								if(((MountPoint)ent).checkCollision(this)) {
									//System.out.println("MP COLLIDES");
									((MountPoint)ent).mountedEnt = this;
									this.setPos(((MountPoint)ent).getPos());
									this.onMount((MountPoint)ent);
								}
							}
						}
					}
		} else {
			if(this.anim.curAnim > 2) {
				this.onUnMount();
				if(this.mountedTo != null) {
					this.mountedTo.mountedEnt = null;
				}
			}
		}
		} else {
			if(this.orders.size()>0){
			//PATHFINDING GOES HERE
			Pathfinding pf = new Pathfinding((int)this.getPos().x/16,(int)this.getPos().y/16, (int)this.orders.get(0).pos.x/16,(int)this.orders.get(0).pos.y/16,true);
			Path p = null;
			while(p == null) {
				p = pf.tick();
			}
			if(p.valid){
				p2 = p;
			} else {
				System.out.println("Pathfinding failed!");
			}
			this.orders.remove(0);
			}
		}
		if(this.anim.curAnim < 3){
			
		}else {
		checkForTargets();
		if (target == null) {
			if (turningright) {
				this.model.rot += 1;
				if (this.model.rot > 180+this.initrot) {
					this.turningright = false;
					SoundHandler.playSound("sentry/seek_1");
				}
			} else {
				this.model.rot -= 1;
				if (this.model.rot < this.initrot) {
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
						
						this.model.rot += 0.5;
					} else {
					this.model.rot -= 0.5;
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
					anim.setAnim(5);
					//this.model.img = ImageContainer.images
						//	.get("sentry_1_light3");

				}
			} else {
				if (this.model.rot < deg) {
					if(deg - this.model.rot > 180) {
						this.model.rot -= 3;
					}else{
					this.model.rot += 3;
					}
				}
				if (this.model.rot > deg) {
					if(this.model.rot - deg > 180) {
						
						this.model.rot += 3;
					} else {
					this.model.rot -= 3;
					}
				}
				if (time >= 21) {
					//this.model.img = ImageContainer.images.get("sentry_1");
					time = 0;
				}
				if (time == 20) {
					anim.setAnim(4);
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
					&& ((EntityHurtable) CurGame.c.terra.entities.get(i)).team != this.team) {
				if (CurGame.c.terra.entities.get(i).getPos()
						.distance(this.getPos()) < 500&&CurGame.c.terra.entities.get(i).getPos().LOS(this.getPos())) {
					target = CurGame.c.terra.entities.get(i);
					flag = true;
					break;
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
	public void renderShapyStuff(){
		if(this.p2!= null){
			this.p2.render();
		}
	}
	public void onMount(MountPoint mp) {
		this.anim.setAnim(1);
		mp.mountedEnt = this;
		this.mountedTo = mp;
	}
	public void onUnMount() {
		this.anim.setAnim(2);

		this.mountedTo = null;
	}

}
