package com.complover116.timezone;



public class WorldTicker {
	public static int waitedTicks = 0;
	public static boolean randomflag = false;
	public static void tickWorld() {
		CurGame.attackTime -= 0.01;
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
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			if(CurGame.status == 10){
				if(CurGame.attackTime > 0) {
				if(CurGame.timespeed < 5000) {
					CurGame.timespeed +=50;
					if(CurGame.timespeed == 500)SoundHandler.playSound("effects/attackhsgo");
					if(CurGame.timespeed < 500)
					SoundHandler.playSound("effects/timetick");
				}
				for(int i = 0; i < CurGame.timespeed/100; i++) {
					tickWorld();
				}
				} else {
					if(!randomflag){
						randomflag = true;
						SoundHandler.playSound("effects/attackhsend");
					}
					if(CurGame.timespeed > 0) {
					if(CurGame.timespeed > 100) {
						CurGame.timespeed -=50;
					} else {
						CurGame.timespeed -=1;
					}
						if(CurGame.timespeed < 500)
						SoundHandler.playSound("effects/timetick");
					} else {
						if(CurGame.terra.owner == 0){
						CurGame.teamtoload = 1;
						} else {
							CurGame.teamtoload = 0;
						}
						CurGame.status = -10;
					}
				}
				
				
			}
			if(CurGame.status == 0){
			if((CurGame.terra.controlledEnt.isDead||CurGame.attackTime < 0.01)&&CurGame.timespeed==100){
				CurGame.status = 10;
				randomflag = false;
			}
			if(CurGame.gamego == false && CurGame.timespeed > 0){
				CurGame.timespeed --;
				if(CurGame.timespeed == 99)SoundHandler.playSound("effects/attackend");
				
				if(CurGame.timespeed < 50)
				SoundHandler.playSound("effects/timetick");
			}
			if(CurGame.gamego == true && CurGame.timespeed < 100) {
				CurGame.timespeed ++;
				if(CurGame.timespeed == 25)SoundHandler.playSound("effects/attackgo");
				if(CurGame.timespeed < 50)
				SoundHandler.playSound("effects/timetick");
			}
			if(waitedTicks == CurGame.TPS){
				waitedTicks = 0;
				tickWorld();
				
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
			
			CurGame.scrollX = CurGame.terra.controlledEnt.getPos().x - MainScreen.width/2;
			CurGame.scrollY = CurGame.terra.controlledEnt.getPos().y - MainScreen.height/2;
			}
			if(CurGame.status > 0&&CurGame.status < 5) {
				CurGame.scrollX += CurGame.scrollingX;
				CurGame.scrollY += CurGame.scrollingY;
			}
			if(CurGame.status == 2){
				CurGame.controllingTeam = (byte) CurGame.terra.owner;
				if(CurGame.terra.preview.tool != null){
				CurGame.terra.preview.tool.setPos(CurGame.terra.preview.getPos());
				}
			}
			if(CurGame.status == -11) {
				if(waitedTicks == 0){
				}
				if(waitedTicks == 1) {
					CurGame.terra = CurGame.teams[CurGame.teamtoload].zone;
					CurGame.attackTime = Config.attacklength;
					CurGame.status = 2;
					CurGame.timespeed=0;
					waitedTicks = -1;
				}
				waitedTicks ++;
			}
			if(CurGame.status == -10) {
				if(waitedTicks == 0){
				}
				if(waitedTicks == 1) {
					CurGame.status = -11;
					waitedTicks = -1;
				}
				waitedTicks ++;
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
