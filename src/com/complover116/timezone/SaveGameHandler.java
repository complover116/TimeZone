package com.complover116.timezone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

public class SaveGameHandler {
	public static boolean savegame() throws InterruptedException {
		String path = GUI.getSavePath();
		System.out.println("Saving to "+path);
		GUI.infoDialog("Saving",  "Saving game to "+path+", please, wait");
		try {
			FileOutputStream fos = new FileOutputStream(path);
			Yaml yaml = new Yaml();
			yaml.dump(CurGame.c, new PrintWriter(fos));
			System.out.println("Game saved!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GUI.hideDialogs();
			Thread.sleep(1000);
			GUI.infoDialog("Failure", "Game could not be saved to "+path+", the file could not be opened.");
			return false;
		}
		GUI.hideDialogs();
		Thread.sleep(500);
		GUI.infoDialog("Success", "Game successfully saved to "+path);
		return true;
	}
	public static boolean loadGame() throws InterruptedException {
		System.out.println("Loading sequence initiated");
		String path = GUI.getLoadPath();
		if(path == null) return false;
		GUI.infoDialog("Loading",  "Loading game from "+path+", please, wait");
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
			GUI.hideDialogs();
			Thread.sleep(1000);
			GUI.infoDialog("Failure", "Game could not be loaded from "+path+", the file could not be found.");
			e.printStackTrace();
		}catch (YAMLException e) {
			GUI.hideDialogs();
			Thread.sleep(1000);
			GUI.infoDialog("Failure", "Game could not be loaded from "+path+", the save file is corrupted.");
			e.printStackTrace();
		}
		GUI.hideDialogs();
		Thread.sleep(500);
		GUI.infoDialog("Success", "Game successfully loaded from "+path);
		CurGame.overstat = 1;
		return true;
	}
}
