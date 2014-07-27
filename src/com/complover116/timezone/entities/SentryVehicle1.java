package com.complover116.timezone.entities;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityControllable;
import com.complover116.timezone.Pos;
import com.complover116.timezone.TeamData;
public class SentryVehicle1 extends EntityControllable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3465697662407826746L;
	private int firedelay;
	public MountPoint mountpoint= new MountPoint();
	@Override
	public void Think2() {
		if(firedelay>0) {
			firedelay--;
		}
		this.mountpoint.setPos(this.getPos());
		// TODO Auto-generated method stub
	}
	public SentryVehicle1() {
		
	}
	public SentryVehicle1(byte team) {
		this.team = team;
		this.model.img = TeamData.getTeamImage("medpauka", this.team);
		CurGame.c.terra.regEntity((mountpoint));
		this.health = 0;
		this.mmaxhealth = 40;
		this.anim = new AnimationSet("smallpauka", this.team);
	}
	@Override
	public void construct() {
		this.buildinghealth = 10;
		this.tph = 2;
		this.costPerHealth = 25;
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 2;
		this.collideY = 2;
		this.collideX2 = 14;
		this.collideY2 = 14;
		this.health = 100;
		this.readName = "Sentry-mounted Vehicle";
		
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.c.terra.regEntity(bul);
		SentryVehicle1 bu = new SentryVehicle1(this.team);
		bu.setPos(new Pos(64,64));
		CurGame.c.terra.regEntity(bu);
		CurGame.c.terra.controlledEnt = bu;
		int tm;
		if(this.team == 0) {
			tm = 1;
		}else {
			tm = 0;
		}
		CurGame.c.teams[tm].metal += 250;
		this.mountpoint.destroy();
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
	@Override
	public void Think() {
		// TODO Auto-generated method stub
		
	}
}
