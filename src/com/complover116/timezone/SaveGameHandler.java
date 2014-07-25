package com.complover116.timezone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;

import org.yaml.snakeyaml.Yaml;

public class SaveGameHandler {
	public static boolean savegame() {
		String path = GUI.getSavePath();
		/*HashMap hash = new HashMap();
		System.out.println("HashMapping current game data...");
		//GLOBAL VARS
		hash.put("attackTime", CurGame.c.attackTime);
		hash.put("controllingTeam", CurGame.c.controllingTeam);
		hash.put("gamego", CurGame.c.gamego);
		hash.put("status", CurGame.c.status);
		hash.put("team1metal", CurGame.c.teams[0].metal);
		hash.put("team1uranium", CurGame.c.teams[0].uranium);
		hash.put("team2metal", CurGame.c.teams[1].metal);
		hash.put("team2uranium", CurGame.c.teams[2].uranium);
		hash.put("cterra", CurGame.c.terra.owner);
		//TERRITORIES
		for(int tm = 0; tm < 2; tm ++){
		System.out.println("Hashmapping zone of team "+tm);
		for(int x = 0; x < 100; x ++) {
			for(int y = 0; y < 100; y ++) {
				CurGame.c.teams[tm].zone.terrain[x][y].getClass()
			}
		}
		}*/
		
		System.out.println("Saving to "+path);
		try {
			FileOutputStream fos = new FileOutputStream(path);
			java.io.ObjectOutputStream s = new java.io.ObjectOutputStream(fos);
			s.writeObject(CurGame.c);
			s.close();
			System.out.println("Game saved!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GUI.infoDialog("Failure", "Game could not be saved to "+path+", the file could not be opened.");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GUI.infoDialog("Success", "Game successfully saved to "+path);
		return true;
	}
	public static boolean loadGame() {
		System.out.println("Loading sequence initiated");
		String path = GUI.getLoadPath();
		if(path == null) return false;
		System.out.println("Releasing current game data from the memory");
		CurGame.overstat = -5;
		GUI.redraw();
		CurGame.c = null;
		//GO GO GARBAGE COLLECTOR
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Loading from "+path);
		try {
			FileInputStream fis = new FileInputStream(path);
			Yaml yml = new Yaml();
			CurGame.c = (CurGame) yml.load(fis);
		} catch (FileNotFoundException e) {
			GUI.infoDialog("Failure", "Game could not be loaded from "+path+", the file could not be found.");
			e.printStackTrace();
		}
		GUI.infoDialog("Success", "Game successfully loaded from "+path);
		return true;
	}
}
