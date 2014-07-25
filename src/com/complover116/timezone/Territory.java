package com.complover116.timezone;

import java.util.ArrayList;

import com.complover116.timezone.blocks.Border;
import com.complover116.timezone.blocks.Ground;
import com.complover116.timezone.blocks.Test;
import com.complover116.timezone.entities.BaseVehicle;
import com.complover116.timezone.entities.Preview;
import com.complover116.timezone.entities.Sentry;

public class Territory {
	public Block[][] terrain = new Block[100][100];
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public EntityControllable controlledEnt;
	public Preview preview;
	public int owner;
	public Territory(int own) {
		owner = own;
		System.out.println("Territory of team "+owner+" is now preparing");
		fill();
		System.out.println("Territory of team "+owner+" is ready");
	}
	public void fill() {
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				terrain[i][j] = new Ground();
			}
		}
		for(int i = 0; i < 100; i ++) {
				terrain[i][0] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[0][i] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[i][99] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[99][i] = new Border();
		}
		Sentry sentry2 = new Sentry(this.owner);
		sentry2.model.x = 256;
		sentry2.model.y = 256;
		entities.add(sentry2);
		byte tm;
		if(this.owner == 0) {
			tm = 1;
		} else {
			tm = 0;
		}
		BaseVehicle bv = new BaseVehicle(tm);
		bv.model.x = 20;
		bv.model.y = 130;
		entities.add(bv);
		controlledEnt = bv;
		Preview pv = new Preview();
		pv.model.x = 16;
		pv.model.y = 16;
		//entities.add(pv);
		preview = pv;
		preview.model.img = TeamData.getTeamImage("cursor", this.owner);
	}
	public void regEntity(Entity entity) {
		entities.add(entity);
	}
	public void delEnt(Entity entity) {
		entities.remove(entity);
		entity = null;
	}
}
