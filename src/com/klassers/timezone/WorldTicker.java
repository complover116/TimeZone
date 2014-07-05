package com.klassers.timezone;

public class WorldTicker {
	public static void run() {
		while(true) {
			//TICKING ENTITIES
			for(int i = 0; i < CurGame.terra.entities.size(); i++) {
				CurGame.terra.entities.get(i).onTick();
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
