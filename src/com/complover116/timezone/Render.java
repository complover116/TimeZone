package com.complover116.timezone;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Render {
	public static void render() {
		MainScreen.shapes.clear();
		//RENDER THE TERRAIN
		synchronized(MainScreen.objects){
		MainScreen.objects.clear();
		MainScreen.indepobjects.clear();
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
			CurGame.c.terra.entities.get(i).renderStuff();
			}
			}
		}
		//RENDER THE INFO BOXES
		for(int i = 0;i < CurGame.c.terra.entities.size(); i++) {
			if(CurGame.c.terra.entities.get(i)!= null){
			if(CurGame.c.terra.entities.get(i).drawInfo) {
				MainScreen.shapes.add(new ShapeModel(new Rectangle2D.Double(CurGame.c.terra.entities.get(i).model.x,CurGame.c.terra.entities.get(i).model.y,16,16), new Color(255,0,0), false));
				CurGame.c.terra.entities.get(i).renderInfo();
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
		MainScreen.indepobjects.add(logo);
		//CALL THE REDRAW
		GUI.redraw();
		
		}
	}
}
