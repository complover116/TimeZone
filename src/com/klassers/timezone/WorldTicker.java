package com.klassers.timezone;


public class WorldTicker {
	public static int waitedTicks = 0;
	public static void tickWorld() {
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
	}
	public static void run() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			if(CurGame.status == 0){
			if(waitedTicks == CurGame.TPS){
				waitedTicks = 0;
				tickWorld();
				if(CurGame.gamego == false && CurGame.timespeed > 0){
					CurGame.timespeed --;
					SoundHandler.playSound("effects/timetick");
				}
				if(CurGame.gamego == true && CurGame.timespeed < 100) {
					CurGame.timespeed ++;
					SoundHandler.playSound("effects/timetick");
				}
					if(CurGame.timespeed < 50) {
						if(CurGame.timespeed < 25) {
							if(CurGame.timespeed < 13) {
								if(CurGame.timespeed < 10) {
									if(CurGame.timespeed < 5) {
										CurGame.TPS = 20;
										if(CurGame.timespeed < 1) {
											CurGame.status = 1;
										}
									} else {
										CurGame.TPS = 10;
									}
								} else {
									CurGame.TPS = 3;
								}
							} else {
								CurGame.TPS = 2;
							}
						} else {
							CurGame.TPS = 1;
						}
					} else {
						CurGame.TPS = 0;
					}
			} else {
				waitedTicks++;
			}
			
			CurGame.scrollX = CurGame.terra.controlledEnt.getPos().x - 250;
			CurGame.scrollY = CurGame.terra.controlledEnt.getPos().y - 250;
			}
			if(CurGame.status == 1) {
				CurGame.scrollX += CurGame.scrollingX;
				CurGame.scrollY += CurGame.scrollingY;
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
