package com.complover116.timezone.entities;

import com.complover116.timezone.EntityHurtable;
import com.complover116.timezone.TeamData;

public class MainFrame extends EntityHurtable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1220164233391105886L;

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

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
		this.model.rotX = 32;
		this.model.rotY = 32;
		this.collideX = 16;
		this.collideY = 16;
		this.collideX2 = 32;
		this.collideY2 = 32;
		this.mmaxhealth = 100;
		this.health = 100;
		this.readName = "MainFrame";
	}

	@Override
	public void onTick() {
		//anim.animTick();
		//this.model.setModel(anim.getFrame());
	}

}
