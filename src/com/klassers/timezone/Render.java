package com.klassers.timezone;

import java.awt.geom.Rectangle2D;

public class Render {
	public static void render() {
		//RENDER THE TERRAIN
		MainScreen.objects.clear();
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				if(CurGame.terra.terrain[i][j] != null){
					CurGame.terra.terrain[i][j].drawthing.x = i*16;
					CurGame.terra.terrain[i][j].drawthing.y = j*16;
				} else {
					CurGame.terra.terrain[i][j].drawthing.x = i*16;
				}
				MainScreen.objects.add(CurGame.terra.terrain[i][j].drawthing);				
			}
		}
		//RENDER THE ENTITIES
		for(int i = 0;i < CurGame.terra.entities.size(); i++) {
			MainScreen.objects.add(CurGame.terra.entities.get(i).model);
		}
		//RENDER THE INFO BOXES
		for(int i = 0;i < CurGame.terra.entities.size(); i++) {
			if(CurGame.terra.entities.get(i).drawInfo) {
				MainScreen.shapes.add(new Rectangle2D.Double(CurGame.terra.entities.get(i).model.x,CurGame.terra.entities.get(i).model.y,16,16));
				CurGame.terra.entities.get(i).renderInfo();
			}
		}
		//CALL THE REDRAW
		GUI.redraw();
	}
}
