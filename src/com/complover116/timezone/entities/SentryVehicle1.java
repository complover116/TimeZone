package com.complover116.timezone.entities;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityControllable;
import com.complover116.timezone.TeamData;
public class SentryVehicle1 extends EntityControllable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3465697662407826746L;
	private int firedelay;
	@Override
	public void Think2() {
		if(firedelay>0) {
			firedelay--;
		}
		this.mountpoints.get(0).setPos(this.getPos());
		// TODO Auto-generated method stub
	}
	public SentryVehicle1() {
		
	}
	public SentryVehicle1(byte team) {
		this.team = team;
		this.model.img = TeamData.getTeamImage("medpauka", this.team);
		this.mountpoints.add(new MountPoint());
		CurGame.c.terra.regEntity((mountpoints.get(0)));
		this.health = 0;
		this.mmaxhealth = 40;
		this.anim = new AnimationSet("medpauka", this.team);
	}
	@Override
	public void construct() {
		this.buildinghealth = 10;
		this.tph = 8;
		this.costPerHealth = 40;
		this.model.rotX = 15.5;
		this.model.rotY = 15.5;
		this.collideX = 5;
		this.collideY = 5;
		this.collideX2 = 27;
		this.collideY2 = 27;
		this.health = 100;
		this.readName = "Sentry-mounted Vehicle";
		
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.c.terra.regEntity(bul);
		this.mountpoints.get(0).destroy();
	}

	@Override
	public void fire1() {
		if(this.firedelay < 1) {
		Bullet bul = new Bullet(this.team);
		bul.setPos(this.getPos());
		bul.direction = this.model.rot;
		CurGame.c.terra.regEntity(bul);
		this.firedelay += 20;
		}
	}
	@Override
	public void onConstructed() {
		// TODO Auto-generated method stub
		this.model.img = TeamData.getTeamImage("medpauka", this.team);
	}
}
