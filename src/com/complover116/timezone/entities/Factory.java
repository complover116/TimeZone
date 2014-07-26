package com.complover116.timezone.entities;

import java.util.ArrayList;

import com.complover116.timezone.AnimationSet;
import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.FactoryJob;
import com.complover116.timezone.Order;
import com.complover116.timezone.TeamData;

public class Factory extends EntityBuildable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3882989112683309616L;
	public ArrayList<FactoryJob> jobs = new ArrayList<FactoryJob>();
	@Override
	public void onConstructed() {
		// TODO Auto-generated method stub
		this.model.img = TeamData.getTeamImage("factory", team);
	}
	public Factory() {
		
	}
	public Factory(int tm) {
		this.team = (byte)tm;
		this.buildinghealth = 10;
		this.tph = 2;
		this.costPerHealth = 25;
	}
	@Override
	public void Think() {
		// TODO Auto-generated method stub
		if(this.orders.size()>0) {
			this.addJob(new Sentry(this.team), this.orders.get(0).addorders);
			this.orders.remove(0);
		}
		if(this.jobs.size() > 0) {
			this.jobs.get(0).ent.onTick();
			if(this.jobs.get(0).ent.constructed) {
				CurGame.c.terra.regEntity(this.jobs.get(0).ent);
				this.jobs.get(0).ent.orders = this.jobs.get(0).orders;
				this.jobs.remove(0);
			}
		}
	}
	public void addJob(EntityBuildable ent, ArrayList<Order> orders) {
		FactoryJob j = new FactoryJob();
		j.ent = ent;
		ent.setPos(this.getPos());
		j.orders = orders;
		jobs.add(j);
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}

	@Override
	public void construct() {
		this.team = (byte) team;
		this.model.img = TeamData.getTeamImage("factory", team);
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 32;
		this.collideY2 = 32;
		this.mmaxhealth = 50;
		this.readName = "Turret Factory";
		anim = new AnimationSet("railmount", team);
	}

}
