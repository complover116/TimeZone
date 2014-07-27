package com.complover116.timezone.entities;

import com.complover116.timezone.CurGame;
import com.complover116.timezone.EntityBuildable;
import com.complover116.timezone.TeamData;

@SuppressWarnings("serial")
public class UnitFactory extends Factory {
	public UnitFactory(byte controllingTeam) {
		this.team = controllingTeam;
		this.buildinghealth = 50;
		this.tph = 1;
		this.costPerHealth = 10;
	}
	@Override
	public void onConstructed() {
		// TODO Auto-generated method stub
		this.model.img = TeamData.getTeamImage("unitfactory", team);
		
	}
	@Override
	public void Think() {
		// TODO Auto-generated method stub
		if(this.orders.size()>0) {
			System.out.println("Order Received");
			EntityBuildable etb = new BaseVehicle(this.team);
			this.addJob(etb, this.orders.get(0).addorders);
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
}
