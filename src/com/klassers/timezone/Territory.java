package com.klassers.timezone;

import java.util.ArrayList;

import com.klassers.timezone.blocks.Ground;
import com.klassers.timezone.blocks.Test;
import com.klassers.timezone.entities.Sentry;
import com.klassers.timezone.entities.TestTarget;

public class Territory {
	public Block[][] terrain = new Block[100][100];
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public void fill() {
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				terrain[i][j] = new Ground();
			}
		}
		for(int i = 0; i < 100; i ++) {
				terrain[i][0] = new Test();
		}
		Sentry sentry = new Sentry();
		sentry.model.x = 70;
		sentry.model.y = 60;
		entities.add(sentry);
		Sentry sentry2 = new Sentry();
		sentry2.model.x = 300;
		sentry2.model.y = 400;
		entities.add(sentry2);
		TestTarget ttarget = new TestTarget();
		ttarget.model.x = 20;
		ttarget.model.y = 130;
		entities.add(ttarget);
		TestTarget ttarget2 = new TestTarget();
		ttarget2.model.x = 60;
		ttarget2.model.y = 130;
		entities.add(ttarget2);
		TestTarget ttarget3 = new TestTarget();
		ttarget3.model.x = 90;
		ttarget3.model.y = 130;
		entities.add(ttarget3);
	}
	public void regEntity(Entity entity) {
		entities.add(entity);
	}
	public void delEnt(Entity entity) {
		entities.remove(entity);
		entity = null;
	}
}
