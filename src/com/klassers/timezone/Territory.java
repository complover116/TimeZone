package com.klassers.timezone;

import java.util.ArrayList;

import com.klassers.timezone.blocks.*;
import com.klassers.timezone.entities.Sentry;

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
		sentry.model.x = 50;
		sentry.model.y = 50;
		entities.add(sentry);
	}
}
