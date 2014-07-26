package com.complover116.timezone;



public class WorldTicker {
	public static int waitedTicks = 0;
	public static boolean randomflag = false;
	public static void tickWorld() {
		CurGame.c.teams[CurGame.c.terra.owner].metaltick++;
		if(CurGame.c.teams[CurGame.c.terra.owner].metaltick > 35) {
			CurGame.c.teams[CurGame.c.terra.owner].metaltick = 0;
			CurGame.c.teams[CurGame.c.terra.owner].metal++;
		}
		CurGame.c.attackTime -= 0.01;
		//TICKING ENTITIES
		MainScreen.shapes.clear();
		for(int i = 0; i < CurGame.c.terra.entities.size(); i++) {
			if(CurGame.c.terra.entities.get(i).isDead) {
				CurGame.c.terra.entities.remove(i);
			} else {
			CurGame.c.terra.entities.get(i).onTick();
			
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
			if(CurGame.overstat > 0){
			if(CurGame.c.status == 10){
				if(CurGame.c.attackTime > 0) {
				if(CurGame.c.timespeed < 5000) {
					CurGame.c.timespeed +=50;
					if(CurGame.c.timespeed == 500)SoundHandler.playSound("effects/attackhsgo");
					if(CurGame.c.timespeed < 500)
					SoundHandler.playSound("effects/timetick");
				}
				for(int i = 0; i < CurGame.c.timespeed/100; i++) {
					tickWorld();
				}
				} else {
					if(!randomflag){
						randomflag = true;
						SoundHandler.playSound("effects/attackhsend");
					}
					if(CurGame.c.timespeed > 0) {
					if(CurGame.c.timespeed > 100) {
						CurGame.c.timespeed -=50;
					} else {
						CurGame.c.timespeed -=1;
					}
						if(CurGame.c.timespeed < 500)
						SoundHandler.playSound("effects/timetick");
					} else {
						if(CurGame.c.terra.owner == 0){
						CurGame.c.teamtoload = 1;
						} else {
							CurGame.c.teamtoload = 0;
						}
						CurGame.c.status = -10;
					}
				}
				
				
			}
			if(CurGame.c.status == 0){
			
			if((CurGame.c.terra.controlledEnt.isDead||CurGame.c.attackTime < 0.01||CurGame.c.terra.firstround)&&CurGame.c.timespeed==100){
				CurGame.c.status = 10;
				CurGame.c.terra.firstround = false;
				randomflag = false;
			}
			if(CurGame.c.gamego == false && CurGame.c.timespeed > 0){
				CurGame.c.timespeed --;
				if(CurGame.c.timespeed == 99)SoundHandler.playSound("effects/attackend");
				
				if(CurGame.c.timespeed < 50)
				SoundHandler.playSound("effects/timetick");
			}
			if(CurGame.c.gamego == true && CurGame.c.timespeed < 100) {
				CurGame.c.timespeed ++;
				if(CurGame.c.timespeed == 1)SoundHandler.playSound("effects/attackgo");
				if(CurGame.c.timespeed < 50)
				SoundHandler.playSound("effects/timetick");
			}
			if(waitedTicks == CurGame.c.TPS){
				waitedTicks = 0;
				tickWorld();
				// TODO - TEMP
				
					if(CurGame.c.timespeed < 50) {
						if(CurGame.c.timespeed < 25) {
							if(CurGame.c.timespeed < 13) {
								if(CurGame.c.timespeed < 10) {
									if(CurGame.c.timespeed < 5) {
										CurGame.c.TPS = 20;
										if(CurGame.c.timespeed < 1) {
											CurGame.c.status = 1;
										}
									} else {
										CurGame.c.TPS = 10;
									}
								} else {
									CurGame.c.TPS = 3;
								}
							} else {
								CurGame.c.TPS = 2;
							}
						} else {
							CurGame.c.TPS = 1;
						}
					} else {
						CurGame.c.TPS = 0;
					}
			} else {
				waitedTicks++;
			}
			
			CurGame.c.scrollX = CurGame.c.terra.controlledEnt.getPos().x - MainScreen.width/2;
			CurGame.c.scrollY = CurGame.c.terra.controlledEnt.getPos().y - MainScreen.height/2;
			}
			if(CurGame.c.status > 0&&CurGame.c.status < 5) {
				CurGame.c.scrollX += CurGame.c.scrollingX;
				CurGame.c.scrollY += CurGame.c.scrollingY;
			}
			if(CurGame.c.status == 2){
				CurGame.c.controllingTeam = (byte) CurGame.c.terra.owner;
				if(CurGame.c.terra.preview.tool != null){
				CurGame.c.terra.preview.tool.setPos(CurGame.c.terra.preview.getPos());
				}
			}
			if(CurGame.c.teams[CurGame.c.terra.owner].dismetal < CurGame.c.teams[CurGame.c.terra.owner].metal) {
				CurGame.c.teams[CurGame.c.terra.owner].dismetal += Math.ceil(((double)CurGame.c.teams[CurGame.c.terra.owner].metal - (double)CurGame.c.teams[CurGame.c.terra.owner].dismetal)/100);
				SoundHandler.playSound("effects/timetick");
			}
			if(CurGame.c.teams[CurGame.c.terra.owner].dismetal > CurGame.c.teams[CurGame.c.terra.owner].metal) {
				CurGame.c.teams[CurGame.c.terra.owner].dismetal -= Math.ceil(((double)CurGame.c.teams[CurGame.c.terra.owner].dismetal - (double)CurGame.c.teams[CurGame.c.terra.owner].metal)/100);
				SoundHandler.playSound("effects/timetick");
			}
			if(CurGame.c.status == -11) {
				if(waitedTicks == 0){
				}
				if(waitedTicks == 1) {
					CurGame.c.terra = CurGame.c.teams[CurGame.c.teamtoload].zone;
					CurGame.c.attackTime = Config.attacklength;
					CurGame.c.status = 2;
					CurGame.c.timespeed=0;
					waitedTicks = -1;
				}
				waitedTicks ++;
			}
			if(CurGame.c.status == -10) {
				if(waitedTicks == 0){
				}
				if(waitedTicks == 1) {
					CurGame.c.status = -11;
					waitedTicks = -1;
				}
				waitedTicks ++;
			}
			
			Render.render();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
