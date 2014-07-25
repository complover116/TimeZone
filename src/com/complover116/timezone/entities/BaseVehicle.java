package com.complover116.timezone.entities;
import com.complover116.timezone.*;
public class BaseVehicle extends EntityControllable {

	private int firedelay;

	@Override
	public void think() {
		if(firedelay>0) {
			firedelay--;
		}
		// TODO Auto-generated method stub
	}

	public BaseVehicle(byte team) {
		this.team = team;
		this.model.img = TeamData.getTeamImage("smallpauka", this.team);
	}
	@Override
	public void construct() {
		this.model.rotX = 8.5;
		this.model.rotY = 11;
		this.collideX = 2;
		this.collideY = 2;
		this.collideX2 = 14;
		this.collideY2 = 14;
		this.health = 100;
		this.readName = "Sentry vehicle";
	}
	@Override
	public void onDeath() {
		MedExplosion1 bul = new MedExplosion1();
		bul.setPos(this.getPos());
		CurGame.terra.regEntity(bul);
	}

	@Override
	public void fire1() {
		if(this.firedelay < 1) {
		Bullet bul = new Bullet(this.team);
		bul.setPos(this.getPos());
		bul.direction = this.model.rot;
		CurGame.terra.regEntity(bul);
		this.firedelay += 20;
		}
	}
}