package com.complover116.timezone.entities;

import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.TeamData;

public class RailMount extends EntityBuildable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2785640073928359759L;
	public MountPoint mountpoint= new MountPoint();
	boolean flag = false;
	@Override
	public void onConstructed() {
		this.model.setModel(TeamData.getTeamImage("railmount", team));
	}
	public RailMount() {
		
	}
	public RailMount(int team) {
		this.buildinghealth = 20;
		this.tph = 8;
		this.costPerHealth = 2;
		this.team = (byte) team;
		this.model.setModel("railmount");
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.mmaxhealth = 20;
		this.readName = "Static mount point";
		anim = new AnimationSet("railmount", team);
		CurGame.c.terra.regEntity((mountpoint));
	}
	@Override
	public void Think() {
		
		// TODO Auto-generated method stub
		mountpoint.setPos(this.getPos());
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		this.mountpoint.destroy();
	}

	@Override
	public void construct() {
		// TODO Auto-generated method stub

	}

}
