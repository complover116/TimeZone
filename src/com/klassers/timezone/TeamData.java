package com.klassers.timezone;

import java.awt.image.BufferedImage;

public class TeamData {
	public int uranium = 100;
	public int metal = 20000;
	public Territory zone;
	public String dataname = "neutral";
	
	public static BufferedImage getTeamImage(String name, int team) {
		System.out.println(team +":"+name+"_"+CurGame.teams[team].dataname);
		return ImageContainer.images.get(name+"_"+CurGame.teams[team].dataname);
	}
}
