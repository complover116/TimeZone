package com.klassers.timezone;

import java.awt.Rectangle;

public class WorldTicker {
	public static void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			//TICKING ENTITIES
			MainScreen.shapes.clear();
			MainScreen.shapes.add(new Rectangle(1,1,10,10));
			for(int i = 0; i < CurGame.terra.entities.size(); i++) {
				if(CurGame.terra.entities.get(i).isDead) {
					CurGame.terra.entities.remove(i);
				} else {
				CurGame.terra.entities.get(i).onTick();
				}
			}
			Render.render();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
