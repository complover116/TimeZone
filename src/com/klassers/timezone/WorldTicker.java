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
			if(CurGame.TPS > 0){
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
			if(CurGame.TPS <100) {
				SoundHandler.playSound("effects/timetick");
			}
			if(CurGame.gamego == false) {
					CurGame.TPS = CurGame.TPS - 1;
			} else {
				if(CurGame.TPS <100) {
					CurGame.TPS = CurGame.TPS + 1;
				}
			}
			if(CurGame.TPS > 0){
			try {
				Thread.sleep(1000/CurGame.TPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} else {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(CurGame.gamego) CurGame.TPS = 1; 
		}
		}
	}
}
