package com.complover116.timezone;

import java.io.Serializable;

public class TeamData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2183684954277704426L;
	public int uranium = 100;
	public int metal = 5000;
	public int dismetal = 0;
	public int disuranium = 0;
	public int metaltick = 0;
	public Territory zone;
	public String dataname = "neutral";
	
	public static String getTeamImage(String name, int team) {
		if(team == 0){
		return name+"_blue";
		} else {
			return name+"_red";
		}
	}
}
