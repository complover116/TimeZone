package com.klassers.timezone;


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
			for(int i = 0; i < CurGame.terra.entities.size(); i++) {
				if(CurGame.terra.entities.get(i).isDead) {
					CurGame.terra.entities.remove(i);
				} else {
				CurGame.terra.entities.get(i).onTick();
				CurGame.terra.entities.get(i).renderStuff();
				}
			}
			CurGame.scrollX = CurGame.terra.controlledEnt.getPos().x - 250;
			CurGame.scrollY = CurGame.terra.controlledEnt.getPos().y - 250;
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
