package com.klassers.timezone;

import java.util.ArrayList;

import com.klassers.timezone.blocks.Ground;
import com.klassers.timezone.blocks.Test;
import com.klassers.timezone.entities.BaseVehicle;
import com.klassers.timezone.entities.Sentry;

public class Territory {
	public Block[][] terrain = new Block[100][100];
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public EntityControllable controlledEnt;
	public void fill() {
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				terrain[i][j] = new Ground();
			}
		}
		for(int i = 0; i < 100; i ++) {
				terrain[i][0] = new Test();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[0][i] = new Test();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[i][99] = new Test();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[99][i] = new Test();
		}
		Sentry sentry = new Sentry();
		sentry.model.x = 70;
		sentry.model.y = 60;
		entities.add(sentry);
		Sentry sentry2 = new Sentry();
		sentry2.model.x = 300;
		sentry2.model.y = 400;
		sentry2.team = 1;
		entities.add(sentry2);
		BaseVehicle bv = new BaseVehicle();
		bv.model.x = 20;
		bv.model.y = 130;
		entities.add(bv);
		controlledEnt = bv;
	}
	public void regEntity(Entity entity) {
		entities.add(entity);
	}
	public void delEnt(Entity entity) {
		entities.remove(entity);
		entity = null;
	}
}
