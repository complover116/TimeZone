package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.Entity;
import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.Path;
import com.complover116.timezone.Pathfinding;
import com.complover116.timezone.Pos;
import com.complover116.timezone.TeamData;

public class MainFrame extends EntityHurtable {
	public boolean shouldDie = false;
	private int dedtimer = 470;
	private Pathfinding pf;
	public Path p2;
	private int time = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1220164233391105886L;
	@Override
	public void takeDamage(Entity attacker, int damage) {
		this.health = this.health - damage;
		displayHealth = 400;
		if(this.health < 0){
			this.onDeath();
			this.health = 1;
		}
	}
	@Override
	public void onDeath() {
		EpicBoom ex = new EpicBoom();
		ex.setPos(this.getPos().sub(new Pos(10,10)));
		CurGame.c.terra.regEntity(ex);
		this.shouldDie = true;
	}
	public MainFrame() {
		
	}
	public MainFrame(int tm) {
		this.team = (byte) tm;
	}
	@Override
	public void construct() {
		this.team = (byte) team;
		this.model.img = TeamData.getTeamImage("mainframe", team);
		this.model.rotX = 16;
		this.model.rotY = 16;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 32;
		this.collideY2 = 32;
		this.mmaxhealth = 10;
		this.health = 10;
		this.readName = "MainFrame";
		pf = new Pathfinding(2,2, 64,64);
	}

	@Override
	public void onTick() {
		time++;
		if(this.time == 100){
			this.time = 0;
			if(p2 == null || !p2.isStillValid()) {
				p2 = null;
			}
		}
		if(p2==null){
		Path p = pf.tick();
		if(p != null) {
			pf = new Pathfinding(2,2, 64,64);
			if(!p.valid) {
				this.takeDamage(null, 99999);
			} else {
				p2 = p;
			}
		}
		}
		if(this.shouldDie){
			this.displayHealth = 0;
			this.dedtimer --;
			if(this.dedtimer == 0) {
				this.remove();
			}
		}
		//anim.animTick();
		//this.model.setModel(anim.getFrame());
	}
	@Override
	public void renderShapyStuff(){
		if(p2 != null) p2.render();
		this.pf.render();
	}
}
