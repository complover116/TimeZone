package com.klassers.timezone;

public class Render {
	public static void render() {
		//RENDER THE TERRAIN
		System.out.println("Rendering started");
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				if(CurGame.terra.terrain[i][j] != null){
					CurGame.terra.terrain[i][j].drawthing.x = i*16;
					CurGame.terra.terrain[i][j].drawthing.y = j*16;
				} else {
					CurGame.terra.terrain[i][j].drawthing.x = i*16;
				}
				MainScreen.objects.add(CurGame.terra.terrain[i][j].drawthing);
				System.out.println("Object "+i+" "+j+" added");
				
			}
		}
		
		//CALL THE REDRAW
		GUI.redraw();
	}
}
