package com.complover116.timezone;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class TeamData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2183684954277704426L;
	public int uranium = 100;
	public int metal = 2000;
	public int dismetal = 0;
	public int disuranium = 0;
	public int metaltick = 0;
	public Territory zone;
	public String dataname = "neutral";
	
	public static BufferedImage getTeamImage(String name, int team) {
		return ImageContainer.images.get(name+"_"+CurGame.c.teams[team].dataname);
	}
}
