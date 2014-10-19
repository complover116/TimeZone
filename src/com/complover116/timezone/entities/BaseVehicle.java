package com.complover116.timezone.entities;
import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityControllable;
import com.complover116.timezone.TeamData;
public class BaseVehicle extends EntityControllable {

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
		// TODO Auto-generated method stub
	}
	public BaseVehicle() {
		
	}
	public BaseVehicle(byte team) {
		this.team = team;
		this.model.img = TeamData.getTeamImage("smallpauka", this.team);
		this.anim = new AnimationSet("smallpauka", this.team);
		this.health = 0;
		this.mmaxhealth = 40;
	}
	@Override
	public void construct() {
		this.buildinghealth = 10;
		this.tph = 1;
		this.costPerHealth = 18;
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 2;
		this.collideY = 2;
		this.collideX2 = 12;
		this.collideY2 = 12;
		this.health = 100;
		this.readName = "Basic Vehicle";
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.c.terra.regEntity(bul);
		/*SentryVehicle1 bu = new SentryVehicle1(this.team);
		bu.setPos(new Pos(64,64));
		CurGame.c.terra.regEntity(bu);
		CurGame.c.terra.controlledEnt = bu;
		int tm;
		if(this.team == 0) {
			tm = 1;
		}else {
			tm = 0;
		}
		CurGame.c.teams[tm].metal += 250;*/
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
		this.model.img = TeamData.getTeamImage("smallpauka", this.team);
	}
}
