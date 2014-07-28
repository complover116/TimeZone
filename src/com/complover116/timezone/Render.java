package com.complover116.timezone;


public class Render {
	public static void render() {
		
		//RENDER THE TERRAIN
		synchronized(MainScreen.objects){
		MainScreen.objects.clear();
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				if(CurGame.c.terra.terrain[i][j] != null){
					CurGame.c.terra.terrain[i][j].drawthing.x = i*16;
					CurGame.c.terra.terrain[i][j].drawthing.y = j*16;
				} else {
					CurGame.c.terra.terrain[i][j].drawthing.x = i*16;
				}
				MainScreen.objects.add(CurGame.c.terra.terrain[i][j].drawthing);				
			}
		}
		//RENDER THE ENTITIES
		for(int i = 0;i < CurGame.c.terra.entities.size(); i++) {
			if(CurGame.c.terra.entities.get(i)!= null){
			if(!CurGame.c.terra.entities.get(i).isDead) {
			MainScreen.objects.add(CurGame.c.terra.entities.get(i).model);
			}
			}
		}
		synchronized(MainScreen.shapes) {
		MainScreen.shapes.clear();
		for(int i = 0;i < CurGame.c.terra.entities.size(); i++) {
			if(CurGame.c.terra.entities.get(i)!= null){
			if(!CurGame.c.terra.entities.get(i).isDead) {
			CurGame.c.terra.entities.get(i).renderStuff();
			}
			}
		}
		}
		if(CurGame.c.status > 0)
		MainScreen.objects.add(CurGame.c.terra.preview.model);
		//RENDER THE CURRENT TEAM
		
		DrawThing logo = new DrawThing();
		logo.img = CurGame.c.teams[CurGame.c.controllingTeam].dataname+"_logo";
		logo.x = MainScreen.width - 64;
		logo.y = 0;
		logo.independent = true;
		MainScreen.objects.add(logo);
		//CALL THE REDRAW
		GUI.redraw();
		
		}
	}
}
